package com.example.demo.hc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.hc.entity.TransferSearchMail;
import com.example.demo.hc.mapper.MailMapper;
import com.example.demo.hc.service.IMailService;
import org.springframework.stereotype.Service;

@Service
public class IMailServiceImpl extends ServiceImpl<MailMapper, TransferSearchMail> implements IMailService {
}
