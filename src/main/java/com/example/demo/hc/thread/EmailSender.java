package com.example.demo.hc.thread;

import com.example.demo.hc.service.EmileService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;

public class EmailSender implements Runnable {

    private SimpleMailMessage mimeMessage = null;
    private JavaMailSenderImpl mailSender = null;

    public EmailSender(String subject, String content, EmileService emileService) {

        try {
            mimeMessage = emileService.sendHtmlMail(subject, content);
            mailSender = emileService.getJavaMailSender();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void send() {
        //发送
        mailSender.send(mimeMessage);


    }

    @Override
    public void run() {
        send();
    }
}
