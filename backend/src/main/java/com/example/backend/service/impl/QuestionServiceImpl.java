package com.example.backend.service.impl;

import com.example.backend.common.Result;
import com.example.backend.compile.ExecuteStringSourceService;
import com.example.backend.entity.Question;
import com.example.backend.entity.Sample;
import com.example.backend.repository.QuestionRepository;
import com.example.backend.repository.SampleRepository;
import com.example.backend.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SampleRepository sampleRepository;
    private ExecuteStringSourceService execute;

    public QuestionServiceImpl(QuestionRepository questionRepository, SampleRepository sampleRepository) {
        this.questionRepository = questionRepository;
        this.sampleRepository = sampleRepository;
    }

    @Override
    public List<Sample> listTestSamples(Long questionId) {
        return sampleRepository.findAllByQuestionIdAndIsTest(questionId,1L);
    }

    @Override
    public Question findQuestion(Long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public List<Question> listQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Boolean check(String code, Long questionId) {
        List<Sample> samples = listTestSamples(questionId);
        for(Sample sample : samples) {
            String runResult = execute.execute(code, sample.getInput());
            if(!runResult.equals(sample.getOutput()))
                return false;
        }
        return true;
    }

    @Override
    public List<String> submitTestCase(String code, String input) {
        String regex = "\n";
        String[] split = input.split(regex);
        List<String> result = new ArrayList<>();
        for(String s:split) {
            String runResult = execute.execute(code,s);
            result.add(runResult);
        }
        return result;
    }
}
