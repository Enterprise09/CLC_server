package com.example.clc.clc_server.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NullPointerException.class)
    public String custom() { 
        return "this is nullpointer exception";
    }

    
}
