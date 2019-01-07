package com.example.demo.hc.Listener;

import com.example.demo.hc.mapper.MailMapper;
import com.example.demo.hc.service.EmileService;
import com.example.demo.hc.service.RedisService;
import com.example.demo.hc.thread.RedisThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private RedisService redisService;
    @Resource
    private RedisTemplate<String, ?> redisTemplate;
    @Autowired
    private EmileService emileService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(new RedisThread(mailMapper, redisService, redisTemplate, emileService)).start();
    }
}
