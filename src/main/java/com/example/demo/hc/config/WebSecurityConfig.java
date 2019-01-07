//package com.example.demo.hc.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import javax.sql.DataSource;
//
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("classpath:/static/**").permitAll()
////                .antMatchers("/User").hasRole("admin")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").permitAll().usernameParameter("username").passwordParameter("password").successForwardUrl("/user")
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
//                .and()
//                .rememberMe().key("rem")
//                .and()
//                .csrf()//默认csrf开启  如果开启，logout必须post访问
//                .and();
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////      auth.inMemoryAuthentication().withUser("admin").password("123").roles("admin");
//
////      auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("User").password("123").roles("User");
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,enabled from user where username=?")
//                .authoritiesByUsernameQuery("select username,role from user where username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());//进行密码匹配
//
//    }
//}