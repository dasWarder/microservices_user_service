package com.example.microservices_userservice.exception.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExceptionMessage {

    private String className;

    private String message;
}
