package com.stranger.easyfindboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/testmethod")
    public String test(){
        return "hello4,1testmethod";
    }
}
