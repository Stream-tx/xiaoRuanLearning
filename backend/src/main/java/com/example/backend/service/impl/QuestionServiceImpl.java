package com.example.backend.service.impl;

import com.example.backend.entity.Question;
import com.example.backend.entity.Sample;
import com.example.backend.repository.QuestionRepository;
import com.example.backend.repository.SampleRepository;
import com.example.backend.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SampleRepository sampleRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, SampleRepository sampleRepository) {
        this.questionRepository = questionRepository;
        this.sampleRepository = sampleRepository;
    }

    @Override
    public List<Sample> listTestSamples(Long questionId) {
        return sampleRepository.findByQuestionIdAndAndIsTest(questionId,1L);
    }

    @Override
    public Question findQuestion(Long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }
}
