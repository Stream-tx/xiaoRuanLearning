package com.example.ojservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OjServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjServiceApplication.class, args);
    }

}
