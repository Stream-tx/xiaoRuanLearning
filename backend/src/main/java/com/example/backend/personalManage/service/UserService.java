package com.example.backend.personalManage.service;


import com.example.backend.personalManage.entity.User;

import java.util.Map;

public interface UserService {
    //登录时检查用户
    User checkUser(String username, String password);

    //根据用户名查找用户
    User findUserByUsername(String username);

    void saveUser(User user);

    Map<Object, Object> getUserQuestions(User user);

    void updateUser(Long id, User user);

    User findUserById(Long id);

}


