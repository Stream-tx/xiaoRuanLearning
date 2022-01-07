package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.entity.Question;
import com.example.backend.entity.Sample;

import java.util.List;

public interface QuestionService {

    List<Sample> listTestSamples(Long questionId);

    Question findQuestion(Long questionId);

    List<Question> listQuestions();

    Result check(String code, Long questionId);

    String submitTestCase(String code, String input, Long questionId);

    String compile(String code);

}
