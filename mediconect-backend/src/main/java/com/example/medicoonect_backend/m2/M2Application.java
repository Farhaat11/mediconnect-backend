package com.example.medicoonect_backend.m2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.example.medicoonect_backend.m2")
@PropertySource("classpath:m2/application.properties")
public class M2Application {

    public static void main(String[] args) {
        SpringApplication.run(M2Application.class, args);
    }
}

//done