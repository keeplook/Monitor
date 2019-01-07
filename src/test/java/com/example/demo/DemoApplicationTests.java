package com.example.demo;

import com.example.demo.hc.entity.TransferSearchMail;
import com.example.demo.hc.mapper.MailMapper;
import com.example.demo.hc.service.EmileService;
import com.example.demo.hc.service.IMailService;
import com.example.demo.hc.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private RedisService redisService;
    @Resource
    private MailMapper mailMapper;
    @Resource
    private RedisTemplate<String, ?> redisTemplate;
    @Autowired
    private EmileService emileService;

    @Autowired
    private IMailService iMailService;
    @Test
    public void contextLoads() {
        TransferSearchMail mail = iMailService.getById(2);
        System.out.println(mail);

    }

}

