package com.example.demo.hc.service.impl;

import com.example.demo.hc.service.EmileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmileServiceImpl implements EmileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //    @Autowired
//    private JavaMailSender mailSender;
    //从那个邮箱发送
    @Value("${mail.fromMail.addr}")
    private String from;
    @Value("${spring.mail.username}")
    private String user;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.protocol}")
    private String protocol;

    //Html邮件发送
    @Override
    public SimpleMailMessage sendHtmlMail(String subject, String content) throws MessagingException {

        String users[] = {"1013196355@qq.com", "liguoh@starteos.io"};

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setCc(from);
        message.setTo(users); // 群发
        message.setSubject(subject);
        message.setText(content);
        return message;

    }

    @Override
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();//直接生产一个实例
        mailSender.setHost(host);
        mailSender.setPassword(password);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(user);
        return mailSender;
    }


}