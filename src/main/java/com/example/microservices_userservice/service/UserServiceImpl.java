package com.example.microservices_userservice.service;

import com.example.microservices_userservice.dao.UserRepository;
import com.example.microservices_userservice.exception.UserNotFoundException;
import com.example.microservices_userservice.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User createUser(User user) {

    log.info("Create a new user");

    return userRepository.save(user);
  }

  @Override
  public User updateUser(Long id, User user) {

    log.info("Update the user with id = {}", id);
    user.setId(id);

    return userRepository.save(user);
  }

  @Override
  public User getUser(Long id) throws UserNotFoundException {

    log.info("Get the user by id = {}", id);

    return userRepository
        .findById(id)
        .orElseThrow(
            () -> new UserNotFoundException(String.format("The user with id = %d not found", id)));
  }

  @Override
  public User getUserByEmail(String email) throws UserNotFoundException {

    log.info("Get the user by email = {}", email);

    return userRepository
        .getUserByEmail(email)
        .orElseThrow(
            () ->
                new UserNotFoundException(
                    String.format("The user with email = %s not found", email)));
  }

  @Override
  public void deleteUserById(Long id) {

    log.info("Delete the user by id = {}", id);
    userRepository.deleteById(id);
  }

  @Override
  public void deleteUSerByEmail(String email) {

    log.info("Delete the user by email = {}", email);
    userRepository.deleteUserByEmail(email);
  }
}
