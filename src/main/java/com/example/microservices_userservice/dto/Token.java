package com.example.microservices_userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    private String token;

    private String subject;

    private Date expiringDate;
}
