package com.example.springframework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}