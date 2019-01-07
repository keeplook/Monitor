package com.example.demo.hc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.hc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
