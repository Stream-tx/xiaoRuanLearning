package com.example.backend.controller;

import cn.hutool.core.map.MapUtil;
import com.example.backend.common.Result;
import com.example.backend.dto.LoginDto;
import com.example.backend.dto.RegisterDto;
import com.example.backend.entity.Question;
import com.example.backend.entity.User;
import com.example.backend.service.QuestionService;
import com.example.backend.service.UserService;
import com.example.backend.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("account")
public class UserController {
    private final UserService userService;
    private final QuestionService questionService;

    public UserController(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    @PostMapping("register")
    public Result register(@Validated @RequestBody RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String email = registerDto.getEmail();
        String password = MD5Utils.code(registerDto.getPassword());
        String university = registerDto.getUniversity();
        String age = registerDto.getAge();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            userService.saveUser(new User(username, password, email, university, Integer.parseInt(age)));
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
            return Result.success(MapUtil.builder()
                    .put("id", user.getUserId())
                    .put("username", user.getUsername())
                    .put("avatar", user.getAvatar())
                    .put("email", user.getEmail())
                    .map());
        }
    }
    @PostMapping("accountInfo")
    public Result accountInfo(@RequestParam Long userId) {
        User user = userService.findUserById(userId);
        Map<Object, Object> map = userService.getUserQuestions(user);
        return Result.success(map);
    }

    @PostMapping("updateInfo")
    public Result update(@RequestBody User user) {
        userService.updateUser(user.getUserId(), user);
        return Result.success(user);
    }

    @PostMapping("getUserInfo")
    public Result getUserInfo(@RequestParam Long userId) {
        User user = userService.findUserById(userId);
        return Result.success(user.getUsername());
    }

}
