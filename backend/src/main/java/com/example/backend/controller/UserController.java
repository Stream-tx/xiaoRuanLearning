package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.dto.LoginDto;
import com.example.backend.dto.RegisterDto;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.util.MD5Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("account")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public Result register(@Validated @RequestBody RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String email = registerDto.getEmail();
        String password = MD5Utils.code(registerDto.getPassword());
        User user = userService.findUserByUsername(username);
        if (user == null) {
            userService.saveUser(new User(username, password, email));
            return Result.success(null);
        } else
            return Result.fail("The username already exists!");
    }

    @PostMapping("login")
    public Result login(@Validated @RequestBody LoginDto loginDto) {
        User user = userService.checkUser(loginDto.getUsername(), loginDto.getPassword());
        if (user == null) {
            return Result.fail("The password is not correct!");
        } else {
            return Result.success(null);
        }
    }
}
