package com.example.demo.hc.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.hc.entity.ResultJson;
import com.example.demo.hc.entity.TransferSearchMail;
import com.example.demo.hc.mapper.MailMapper;
import com.example.demo.hc.service.EmileService;
import com.example.demo.hc.service.IMailService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmailController {
    @Resource
    IMailService iMailService;
    //邮件服务
    @Autowired
    EmileService emileService;
    @Autowired
    MailMapper mailMapper;

    //    @RequestMapping("/emil")
//    public String emil(Mail mail) {
//        //用户消费金额
//        Wrapper<Mail> wrapper = new UpdateWrapper<>(new Mail())
//                .eq("id", mail.getId())
//                .setSql("price=price-" + mail.getPrice());
//        //判断是否消费成功
//        boolean isSuccess = iMailService.update(new Mail(), wrapper);
//        if (isSuccess) {
//            if (mail.getPrice() > 500) {
//                String content = "<html><body><h1>消费了" + mail.getPrice() + "</h1></body></html>";
//                EmailSender emailSender = new EmailSender(mail.getEmail(), "主题", content, emileService);
//                Thread thread = new Thread(emailSender);
//                thread.start();
//            }
//        }
//        return "index";
//    }
    @RequestMapping("eos")
    public List<Map<String, Object>> reJson(Integer page, Integer row, String username) {
        if (username != null) {
            Wrapper<TransferSearchMail> wrapper3 = new QueryWrapper<>(new TransferSearchMail()).eq("account", username);
            List<Map<String, Object>> map = mailMapper.selectMaps(wrapper3);
            return map;
        }
        if (page == null) {
            page = 1;
            row = 10;
        }
        Page<TransferSearchMail> iPage2 = new Page<>(page, row);
        Wrapper<TransferSearchMail> wrapper3 = new QueryWrapper<>();
        IPage<Map<String, Object>> ceIPage2 = mailMapper.selectMapsPage(iPage2, wrapper3);
        Map<String, String> map = new HashMap<>();
        map.put("count", ceIPage2.getTotal() + "");
        return ceIPage2.getRecords();
    }
}