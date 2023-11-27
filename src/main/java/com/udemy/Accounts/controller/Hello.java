package com.udemy.Accounts.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @Bean
    @GetMapping
    public String hello(){
        return "Hello World";
    }
}
