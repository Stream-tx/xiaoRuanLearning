package com.example.accountservice.service;

import com.example.accountservice.common.Result;
import com.example.accountservice.entity.Question;
import com.example.accountservice.entity.Sample;


import java.util.List;

public interface QuestionService {

    List<Sample> listTestSamples(Long questionId);

    Question findQuestion(Long questionId);

    List<Question> listQuestions();

    Result check(String code, Long questionId);

    String submitTestCase(String code, String input, Long questionId);

    String compile(String code);

}
