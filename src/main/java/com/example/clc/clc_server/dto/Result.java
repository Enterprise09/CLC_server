package com.example.clc.clc_server.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Result<T> {
    // private HttpStatus status;
    private String statusMessage = "";
    private T data;

    // public Result(HttpStatus status, T data) {
    //     this.status = status;
    //     this.data = data;
    // }

    public Result(T data) {
        this.data = data;
    }
}