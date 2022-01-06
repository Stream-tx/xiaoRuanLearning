package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.compile.ExecuteStringSourceService;
import com.example.backend.entity.Question;
import com.example.backend.entity.Sample;
import com.example.backend.service.QuestionService;
import com.example.backend.service.SampleService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class QuestionController {

    private final ExecuteStringSourceService executeStringSourceService;
    private final QuestionService questionService;

    public QuestionController(ExecuteStringSourceService executeStringSourceService, QuestionService questionService) {
        this.executeStringSourceService = executeStringSourceService;
        this.questionService = questionService;
    }

    @PostMapping("/submit")
    public Result submit(@RequestBody Map<String, String> data) {
        String runResult = executeStringSourceService.execute(data.get("code"), data.get("input"));
        return Result.success(runResult);
    }

    @PostMapping("/check")
    public Result check(@RequestBody Map<String, String> data) {
        String code = data.get("code");
        Long questionId = Long.parseLong(data.get("questionId"));
//        Question question = questionService.findQuestion(questionId);
        List<Sample> samples = questionService.listTestSamples(questionId);
        for(Sample sample : samples) {
            String runResult = executeStringSourceService.execute(code, sample.getInput());
            if(!runResult.equals(sample.getOutput()))
                return Result.success(false);
        }
        return Result.success(true);
    }
}
