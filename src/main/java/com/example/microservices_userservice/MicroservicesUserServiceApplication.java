package com.example.microservices_userservice;

import com.example.microservices_userservice.model.User;
import com.example.microservices_userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesUserServiceApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesUserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user = User.builder()
                .firstName("Alex")
                .lastName("Mayers")
                .email("alex@gmail.com")
                .password("12345")
                .build();

        userService.createUser(user);
    }
}
