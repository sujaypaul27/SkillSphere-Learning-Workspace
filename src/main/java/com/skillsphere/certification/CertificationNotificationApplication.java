package com.skillsphere.certification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@EnableScheduling
public class CertificationNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CertificationNotificationApplication.class, args);
    }
}
