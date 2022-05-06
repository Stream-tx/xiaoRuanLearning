package com.example.ojservice.service.impl;

import cn.hutool.core.map.MapUtil;
import com.example.ojservice.common.Result;
import com.example.ojservice.compile.ExecuteStringSourceService;
import com.example.ojservice.entity.Question;
import com.example.ojservice.entity.Sample;
import com.example.ojservice.repository.QuestionRepository;
import com.example.ojservice.repository.SampleRepository;
import com.example.ojservice.service.QuestionService;
import org.springframework.stereotype.Service;

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
    public Result check(String code, Long questionId) {
        List<Sample> samples = listTestSamples(questionId);
        Question question = findQuestion(questionId);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < samples.size(); i++) {
            Sample sample = samples.get(i);
            String runResult = executeStringSourceService.execute(code, sample.getInput(), question);
            String output = sample.getOutput();
            if (output.charAt(0) == '"')
                output = output.substring(1, output.length() - 1);
            if (!runResult.equals(output)) {
                long endTime = System.currentTimeMillis();
                return Result.fail(400, "compiler error!", MapUtil.builder()
                        .put("input", sample.getInput())
                        .put("errorMessage", runResult)
                        .put("time", endTime - startTime)
                        .put("testCases", i + 1)
                        .put("allCases", samples.size())
                        .map());
            }
        }
        long endTime = System.currentTimeMillis();
        return Result.success(MapUtil.builder()
                .put("time", endTime - startTime)
                .put("allCases", samples.size())
                .map());
    }

    @Override
    public String submitTestCase(String code, String input, Long questionId) {
        String regex = "\n";
        String[] split = input.split(regex);
        Question question = findQuestion(questionId);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            String runResult = executeStringSourceService.execute(code, s, question);
            stringBuilder.append(runResult).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public String compile(String code) {
        return executeStringSourceService.execute(code, "nullabc", null);
    }
}
