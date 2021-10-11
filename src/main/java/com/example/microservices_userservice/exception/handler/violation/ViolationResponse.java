package com.example.microservices_userservice.exception.handler.violation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolationResponse {

    private List<Violation> violations = new ArrayList<>();
}
