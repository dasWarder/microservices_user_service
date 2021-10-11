package com.example.microservices_userservice.exception.handler.violation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Violation {

    private String fieldName;

    private String message;
}
