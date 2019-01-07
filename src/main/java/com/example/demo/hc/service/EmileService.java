package com.example.demo.hc.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;

//邮件提醒Service
public interface EmileService {
//    //简单邮件
//    void sendSimpleMail(String to, String subject, String content) throws MessagingException;

    //发送Html邮件
    SimpleMailMessage sendHtmlMail(String subject, String content) throws MessagingException;

    JavaMailSenderImpl getJavaMailSender();
//    //带附件的邮件发送
//    void sendFileMail(String to, String subject, String content, String filePath) throws MessagingException, UnsupportedEncodingException;

}
