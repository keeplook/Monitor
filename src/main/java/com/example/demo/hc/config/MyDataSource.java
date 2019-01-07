//package com.example.demo.hc.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class MyDataSource {
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
////        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setUrl(env.getProperty("user.datasource.url"));
//        dataSource.setUsername(env.getProperty("user.datasource.username"));
//        dataSource.setPassword(env.getProperty("user.datasource.password"));
//        return dataSource;
//    }
//}
