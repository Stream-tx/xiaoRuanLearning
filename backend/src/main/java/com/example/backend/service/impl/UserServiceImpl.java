package com.example.backend.service.impl;

import cn.hutool.core.map.MapUtil;
import com.example.backend.entity.Code;
import com.example.backend.entity.User;
import com.example.backend.repository.CodeRepository;
import com.example.backend.repository.QuestionRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import com.example.backend.util.MD5Utils;
import com.example.backend.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final CodeRepository codeRepository;

    public UserServiceImpl(UserRepository userRepository, QuestionRepository questionRepository, CodeRepository codeRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.codeRepository = codeRepository;
    }

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Map<Object, Object> getUserQuestions(User user) {
        String submissionList = user.getSubmissionList();
        String[] submissionQuestions = submissionList.split(",");
        List<Code> submissionCode = new ArrayList<>();
        for (String pQuestion : submissionQuestions) {
            long pid = Long.parseLong(pQuestion);
            codeRepository.findById(pid).ifPresent(submissionCode::add);
        }
        return MapUtil.builder()
                .put("submissionCode", submissionCode)
                .map();
    }

    @Override
    public void updateUser(Long id, User user) {
        User u = userRepository.findById(id).orElse(null);
        assert u != null;
        BeanUtils.copyProperties(user,u, MyBeanUtils.getNullPropertyNames(user));
        userRepository.save(u);
    }
}
