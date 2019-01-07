package com.example.demo.hc.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.hc.entity.TransferSearchMail;
import com.example.demo.hc.mapper.MailMapper;
import com.example.demo.hc.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    MailMapper mailMapper;

    @Autowired
    IMailService iMailService;

    @RequestMapping("user")
    public ModelAndView getUser(TransferSearchMail mail) {
        ModelAndView modelAndView = new ModelAndView();
        Wrapper<TransferSearchMail> wrapper = new QueryWrapper<>(new TransferSearchMail()).eq("username", mail.getAccount());
        TransferSearchMail mail1 = mailMapper.selectOne(wrapper);
        modelAndView.addObject("mail1", mail1);

        if (mail1 == null) {
            modelAndView.setViewName("/login");
            return modelAndView;
        } else {
            modelAndView.setViewName("update");
            return modelAndView;
        }
    }

    @RequestMapping("update")
    public ModelAndView update(TransferSearchMail mail) {
        ModelAndView modelAndView = new ModelAndView();
        if (mail.getPrice() != null) {
            Double price = mail.getPrice();
            mail.setPrice(null);
            Wrapper<TransferSearchMail> wrapper = new UpdateWrapper<>(new TransferSearchMail()).eq("username", mail.getAccount()).setSql("price=price-"+price);
            mailMapper.update(mail, wrapper);
        } else {
            Wrapper<TransferSearchMail> wrapper = new UpdateWrapper<>(new TransferSearchMail()).eq("username", mail.getAccount());
            mailMapper.update(mail, wrapper);
        }

        Wrapper<TransferSearchMail> wrapper = new QueryWrapper<>(new TransferSearchMail()).eq("username", mail.getAccount());
        TransferSearchMail mail1 = mailMapper.selectOne(wrapper);
        modelAndView.addObject("mail1", mail1);
        modelAndView.setViewName("update");
        return modelAndView;
    }
}
