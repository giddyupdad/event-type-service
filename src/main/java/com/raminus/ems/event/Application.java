package com.raminus.ems.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.raminus.ems.event")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
