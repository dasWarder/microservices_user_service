package com.example.microservices_userservice.controller;

import com.example.microservices_userservice.dto.CreateUserRequest;
import com.example.microservices_userservice.dto.GetUserResponse;
import com.example.microservices_userservice.exception.UserNotFoundException;
import com.example.microservices_userservice.mapping.UserMapper;
import com.example.microservices_userservice.model.User;
import com.example.microservices_userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  @Value("${server.port}")
  private String port;

  @Value("${uri.base}")
  private String baseUri;

  private final UserMapper userMapper;

  private final UserService userService;

  @PostMapping
  public ResponseEntity<GetUserResponse> createUser(@RequestBody @NotNull @Valid CreateUserRequest userRequest) {

    User user = userMapper.createUserRequestToUser(userRequest);
    User createdUser = userService.createUser(user);
    GetUserResponse response = userMapper.userToGetUserResponse(createdUser);

    return ResponseEntity.created(URI.create(String.format("%s:%s/users", baseUri, port))).body(response);
  }

  @PutMapping
  public ResponseEntity<GetUserResponse> updateUser(
          @RequestParam("id") @Min(value = 0) Long id, @RequestBody @NotNull @Valid CreateUserRequest userRequest) {

    User user = userMapper.createUserRequestToUser(userRequest);
    User updatedUser = userService.updateUser(id, user);
    GetUserResponse response = userMapper.userToGetUserResponse(updatedUser);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetUserResponse> getUserById(@PathVariable("id") @NotNull @Min(value = 0) Long id) throws UserNotFoundException {

    User user = userService.getUser(id);
    GetUserResponse response = userMapper.userToGetUserResponse(user);

    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<GetUserResponse> getUserByEmail(@RequestParam("email") @NotNull String email) throws UserNotFoundException {

    User userByEmail = userService.getUserByEmail(email);
    GetUserResponse response = userMapper.userToGetUserResponse(userByEmail);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable("id") @NotNull @Min(value = 0) Long id) {

    userService.deleteUserById(id);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteUserByEmail(@RequestParam("email") @NotNull String email) {

    userService.deleteUSerByEmail(email);

    return ResponseEntity.noContent().build();
  }
}
