package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.dto.RegisterDto;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public Result register(@RequestBody RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String email = registerDto.getEmail();
        String password = registerDto.getPassword();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            userService.saveUser(new User(username, password, email));
            return Result.success(null);
        }
        else
            return Result.fail("The username already exists!");
    }
}
