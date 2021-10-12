package com.example.microservices_userservice.service;

import com.example.microservices_userservice.exception.UserNotFoundException;
import com.example.microservices_userservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User createUser(User user);

    User updateUser(Long id, User user);

    User getUser(Long id) throws UserNotFoundException;

    User getUserByEmail(String email) throws UserNotFoundException;

    void deleteUserById(Long id);

    void deleteUSerByEmail(String email);
}
