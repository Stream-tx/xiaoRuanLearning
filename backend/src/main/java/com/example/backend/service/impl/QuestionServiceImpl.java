package com.example.backend.service.impl;

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
    private final ExecuteStringSourceService executeStringSourceService;

    public QuestionServiceImpl(QuestionRepository questionRepository, SampleRepository sampleRepository, ExecuteStringSourceService executeStringSourceService) {
        this.questionRepository = questionRepository;
        this.sampleRepository = sampleRepository;
        this.executeStringSourceService = executeStringSourceService;
    }

    @Override
    public List<Sample> listTestSamples(Long questionId) {
        return sampleRepository.findAllByQuestionIdAndIsTest(questionId, 1L);
    }

    @Override
    public Question findQuestion(Long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public List<Question> listQuestions() {
        return questionRepository.listQuestion();
    }

    @Override
    public Boolean check(String code, Long questionId) {
        List<Sample> samples = listTestSamples(questionId);
        for (Sample sample : samples) {
            String runResult = executeStringSourceService.execute(code, sample.getInput());
            String output = sample.getOutput();
            if (output.charAt(0) == '"')
                output = output.substring(1, output.length() - 1);
            if (!runResult.equals(output))
                return false;
        }
        return true;
    }

    @Override
    public List<String> submitTestCase(String code, String input) {
        String regex = "\n";
        String[] split = input.split(regex);
        List<String> result = new ArrayList<>();
        for (String s : split) {
            String runResult = executeStringSourceService.execute(code, s);
            result.add(runResult);
        }
        return result;
    }


}
