package com.example.demo.hc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.hc.entity.User;
import com.example.demo.hc.mapper.UserMapper;
import com.example.demo.hc.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
