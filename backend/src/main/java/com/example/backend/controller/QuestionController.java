package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.Question;
import com.example.backend.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/submitTestCase")
    public Result submitTestCase(@RequestBody Map<String, String> data) {
        String input = data.get("input");
        String code = data.get("code");
        List<String> result = questionService.submitTestCase(code, input);
        return Result.success(result);
    }

    @PostMapping("/check")
    public Result check(@RequestBody Map<String, String> data) {
        String code = data.get("code");
        Long questionId = Long.parseLong(data.get("questionId"));
        Boolean check = questionService.check(code, questionId);
        return Result.success(check);
    }

    @PostMapping("listQuestions")
    public Result listQuestions(){
        List<Question> questions = questionService.listQuestions();
        return Result.success(questions);
    }
}
