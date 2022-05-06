package com.example.accountservice.service;


import com.example.accountservice.entity.User;
import com.example.accountservice.entity.Code;
import com.example.accountservice.entity.User;
import com.example.accountservice.repository.UserRepository;

import java.util.List;
import java.util.Map;

public interface UserService {
    User checkUser(String username, String password);

    User findUserByUsername(String username);

    void saveUser(User user);

    Map<Object, Object> getUserQuestions(User user);

    void updateUser(Long id, User user);

    User findUserById(Long id);

}


