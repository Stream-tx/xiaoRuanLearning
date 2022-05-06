package com.example.ojservice.service;

import com.example.ojservice.common.Result;
import com.example.ojservice.entity.Question;
import com.example.ojservice.entity.Sample;

import java.util.List;

public interface QuestionService {

    List<Sample> listTestSamples(Long questionId);

    Question findQuestion(Long questionId);

    List<Question> listQuestions();

    Result check(String code, Long questionId);

    String submitTestCase(String code, String input, Long questionId);

    String compile(String code);

}
