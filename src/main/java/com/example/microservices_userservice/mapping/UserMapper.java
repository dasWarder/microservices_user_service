package com.example.microservices_userservice.mapping;

import com.example.microservices_userservice.dto.CreateUserRequest;
import com.example.microservices_userservice.dto.GetUserResponse;
import com.example.microservices_userservice.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserMapper {

//  private final PasswordEncoder passwordEncoder;

  public final User createUserRequestToUser(CreateUserRequest userRequest) {

    log.info("Mapping CreateUserRequest to User");

    User user =
        User.builder()
            .firstName(userRequest.getFirstName())
            .lastName(userRequest.getLastName())
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .build();

    return user;
  }

  public final GetUserResponse userToGetUserResponse(User user) {

    log.info("Mapping User to GetUserResponse");

    GetUserResponse response =
        GetUserResponse.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .build();

    return response;
  }
}
