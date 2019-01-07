package com.example.demo.hc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    //登录
    @RequestMapping("login")

    public String login() {

        return "login";


    }

    //注销
    @RequestMapping("logout")
    public String logout() {
        return "login";
    }
}