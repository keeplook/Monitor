package com.example.demo.hc.thread;

import com.example.demo.hc.entity.TransferSearchMail;
import com.example.demo.hc.json.EosJson;
import com.example.demo.hc.mapper.MailMapper;
import com.example.demo.hc.service.EmileService;
import com.example.demo.hc.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisThread implements Runnable {
    private MailMapper mailMapper;
    private RedisService redisService;
    private RedisTemplate<String, ?> redisTemplate;
    private EmileService emileService;

    public RedisThread(MailMapper mailMapper, RedisService redisService, RedisTemplate<String, ?> redisTemplate, EmileService emileService) {
        this.mailMapper = mailMapper;
        this.redisService = redisService;
        this.redisTemplate = redisTemplate;
        this.emileService = emileService;
    }

    public List<TransferSearchMail> fist() {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        //查询所有数据库
        List<TransferSearchMail> list = mailMapper.selectList(null);
        for (TransferSearchMail m : list) {
            if(m.getTime()==null){
                m.setTime(10);
            }

            long time = m.getTime() * 60;
            Double money = m.getPrice();
            String username = m.getAccount();
            EosJson eosJson = new EosJson();
            Double yue = eosJson.getJson(username);

            if(money==null){
                money=10.0;
            }
            String emile1 = "chennail@starteos.io";
            String emile2 = "sunweil@starteos.io";
            String emile3 = "yangyang@starteos.io";
            //在redis查找用户余额
            String blance = redisService.get(username);
            if (blance == null) {
                //如果没有当前用户增加当前用户
                redisService.set(username, yue + ",1");
            } else {
                String[] blan = blance.split(",");
                double price = Double.parseDouble(blan[0]);
                double score = Double.parseDouble(blan[1]);
                if (yue < price) {
                    //如果金额直接超过发送邮件  并且删除记录
                    double use = price - yue;
                    Set set = redisService.zRangeByScore(username + "b");
                    if (use >= money) {
                        redisService.set(username, yue + ",1");
                        redisService.remove(username + "b");
                        Iterator<byte[]> iterable = set.iterator();
                        int sco = 1;
                        while (iterable.hasNext()) {
                            byte[] ele = iterable.next();
                            String id = serializer.deserialize(ele);
                            String value = redisService.get(id);
                            if (value != null) {
                                double count = Double.parseDouble(value);
                                double record = count + use;

                                redisService.set(username, yue + ",1");
                                redisService.remove(username + "b");
                                Thread thread = new Thread(new EmailSender("EOS", "用户：" + username + "近期消费金额较大,累计消费了:" + record, emileService));
                                thread.start();
                                sco++;
                                break;
                            }
                        }
                        if (sco == 1) {
                            redisService.set(username, yue + ",1");
                            redisService.remove(username + "b");
                            Thread thread = new Thread(new EmailSender("EOS", "用户：" + username + "近期消费金额较大,累计消费了:" + use, emileService));
                            thread.start();
                        }


                    } else {
                        if (set.size() == 0) {
                            //可能因为过期时间删除了  更新用户金额和score
                            redisService.set(username, yue + ",1");
                            blance = redisService.get(username);
                            blan = blance.split(",");
                            score = Double.parseDouble(blan[1]);
                            //添加zset用于查找消费记录
                            redisService.zset(username + "b", score, username + "b" + score);
                            //添加一条消费记录
                            redisService.set(username + "b" + score, use + "");
                            //设置消费查找金额记录的过期时间
                            redisService.expire(username + "b", time);
                            //设置消费记录的过期时间
                            redisService.expire(username + "b" + score, time);


                        } else {

                            Iterator<byte[]> iterable = set.iterator();
                            double sco = 1;
                            while (iterable.hasNext()) {

                                byte[] ele = iterable.next();
                                String id = serializer.deserialize(ele);
                                String value = redisService.get(id);
                                if (value != null) {
                                    double count = Double.parseDouble(value);
                                    double record = count + use;
                                    if (record >= money) {
                                        redisService.set(username, yue + ",1");
                                        redisService.remove(username + "b");
                                        Thread thread = new Thread(new EmailSender("EOS", "用户：" + username + "近期消费金额较大,累计消费了:" + record, emileService));
                                        thread.start();
                                        break;
                                    } else {
                                        long x = redisService.ttl(id);
                                        redisService.set(id, record + "");
                                        //获取过期时间并且重新赋值

                                        redisService.expire(id, x);
                                        //更新过期时间
                                        redisService.expire(username + "b", time);
                                        //更新记录的过期时间

                                        if (sco == 1) {
                                            sco++;
                                            score = score + 1;
                                            //更新用户信息
                                            redisService.set(username, yue + "," + score);
                                            redisService.zset(username + "b", score, username + "b" + score);
                                            redisService.set(username + "b" + score, use + "");
                                            redisService.expire(username + "b", time);
                                            //设置消费记录的过期时间
                                            redisService.expire(username + "b" + score, time);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    redisService.set(username, yue + "," + score);
                }
            }
        }

        return list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fist();
        }

    }

}
