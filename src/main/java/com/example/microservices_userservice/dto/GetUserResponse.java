package com.example.microservices_userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;
}
