package com.example.microservices_userservice.service;

import com.example.microservices_userservice.exception.UserNotFoundException;
import com.example.microservices_userservice.model.User;

public interface UserService {

    User createUser(User user);

    User updateUser(Long id, User user);

    User getUser(Long id) throws UserNotFoundException;

    User getUserByEmail(String email) throws UserNotFoundException;

    void deleteUserById(Long id);

    void deleteUSerByEmail(String email);
}
