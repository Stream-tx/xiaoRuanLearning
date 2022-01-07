package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.Question;
import com.example.backend.service.QuestionService;
import com.example.backend.service.SolutionService;
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
        String questionId = data.get("questionId");
        String result = questionService.submitTestCase(code, input,Long.parseLong(questionId));
        return Result.success(result);
    }

    @PostMapping("/check")
    public Result check(@RequestBody Map<String, String> data) {
        String code = data.get("code");
        Long questionId = Long.parseLong(data.get("questionId"));
        return questionService.check(code, questionId);
    }

    @PostMapping("listQuestions")
    public Result listQuestions(){
        List<Question> questions = questionService.listQuestions();
        return Result.success(questions);
    }

    @PostMapping("getQuestion")
    public Result getQuestion(@RequestParam Long questionId) {
        Question question = questionService.findQuestion(questionId);
        return Result.success(question);
    }

    @PostMapping("/compile")
    public Result compile(@RequestBody Map<String, String> data) {
        String code = data.get("code");
        return Result.success(questionService.compile(code));
    }
}
