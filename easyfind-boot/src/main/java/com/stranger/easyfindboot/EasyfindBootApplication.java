package com.stranger.easyfindboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stranger.easyfindboot.mapper")
public class EasyfindBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyfindBootApplication.class, args);
    }

}
