package com.example.clc.clc_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClcServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClcServerApplication.class, args);
	}

}