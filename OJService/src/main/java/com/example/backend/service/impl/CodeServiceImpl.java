package com.example.backend.service.impl;

import com.example.backend.common.Result;
import com.example.backend.entity.Code;
import com.example.backend.entity.User;
import com.example.backend.repository.CodeRepository;
import com.example.backend.repository.QuestionRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.CodeService;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public CodeServiceImpl(CodeRepository codeRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.codeRepository = codeRepository;
        this.userRepository=userRepository;
        this.questionRepository=questionRepository;
    }

    @Override
    public Code findCodeById(Long codeId) {
        return codeRepository.findById(codeId).orElse(null);
    }

    @Override
    public List<Code> getCodeByUserIdAndQuestionId(Long userId, Long questionId) {
        return codeRepository.findAllByUserIdAndQuestionId(userId,questionId);
    }

    @Override
    public Code getTheLatestCode(Long userId, Long questionId) {
        //List<Code> arr = new List<Code>();
        List<Code> arr = codeRepository.findCodeByUserIdAndQuestionIdOrderByCodeIdDesc(userId,questionId);
        return arr.get(0);
    }

    @Override
    public void updateCode(Code code) {
        codeRepository.saveAndFlush(code);
    }

    @Override
    public Result saveCode(Code code){
        User user=userRepository.findUserByUserId(code.getUserId());
        Date date = new Date();

        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        code.setSubmitTime(new Timestamp(System.currentTimeMillis()));

        code.setLanguage("JAVA");
        updateCode(code);

        Code newCode=getTheLatestCode(code.getUserId(),code.getQuestionId());

        System.out.println(user.getSubmissionList());
        System.out.println('1');
        System.out.println(newCode.getCodeId().toString());

        if(user.getSubmissionList().isEmpty())
            user.setSubmissionList(newCode.getCodeId().toString());
        else
            user.setSubmissionList(user.getSubmissionList()+","+newCode.getCodeId());
        userRepository.saveAndFlush(user);

        return Result.success(null);
    }
}