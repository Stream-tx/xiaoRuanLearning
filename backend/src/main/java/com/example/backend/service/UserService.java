package com.example.backend.service;


import com.example.backend.entity.User;

public interface UserService {
    User checkUser(String username, String password);

    User findUserByUsername(String username);

    User saveUser(User user);
}
