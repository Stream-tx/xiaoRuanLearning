package com.example.backend.service;


import com.example.backend.entity.User;

import java.util.Map;

public interface UserService {
    User checkUser(String username, String password);

    User findUserByUsername(String username);

    void saveUser(User user);

    Map<Object, Object> getUserQuestions(User user);

    void updateUser(Long id, User user);

    User findUserById(Long id);
}
