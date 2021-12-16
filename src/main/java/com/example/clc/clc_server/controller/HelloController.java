package com.example.clc.clc_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("/")
    String hello(){
        log.info("this is hello controller!");
        return "hello";
    }

}
