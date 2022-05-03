package com.example.backend.codingCommit.service;

import com.example.backend.result.Result;
import com.example.backend.codingCommit.entity.Question;
import com.example.backend.codingCommit.entity.Sample;

import java.util.List;

public interface QuestionService {

    List<Sample> listTestSamples(Long questionId);

    Question findQuestion(Long questionId);

    List<Question> listQuestions();

    Result check(String code, Long questionId);

    String submitTestCase(String code, String input, Long questionId);

    String compile(String code);

}
