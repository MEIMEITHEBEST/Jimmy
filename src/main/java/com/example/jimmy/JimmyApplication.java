package com.example.jimmy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.jimmy.mapper")
@SpringBootApplication
public class JimmyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JimmyApplication.class, args);
    }

}
