package com.example.microservices_userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesUserServiceApplication.class, args);
    }

}
