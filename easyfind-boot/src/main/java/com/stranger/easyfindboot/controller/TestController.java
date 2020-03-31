package com.stranger.easyfindboot.controller;

import com.stranger.easyfindboot.entity.SysUser;
import com.stranger.easyfindboot.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private Logger log= LoggerFactory.getLogger(TestController.class);

    @Autowired
    ISysUserService sysUserService;

    @RequestMapping("/test/sayhello")
    public String sayHello(){
        log.info("hello world");
        UserDetails sysUser=sysUserService.loadUserByUsername("test");
        return "hello world!"+sysUser.getUsername();
    }

    @RequestMapping("/login")
    public String login(){
        log.info("hello world");
        UserDetails sysUser=sysUserService.loadUserByUsername("test");
        return "hello world!"+sysUser.getUsername();
    }

}
