package com.example.microservices_userservice.dao;

import com.example.microservices_userservice.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getUserByEmail(String user);

    void deleteUserByEmail(String email);
}
