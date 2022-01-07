package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.service.CodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("code")
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("getCodeByUidAndQid")
    public Result getCodeByUserIdAndQuestionId(@RequestParam Long userId, @RequestParam Long questionId){
        return Result.success(codeService.getCodeByUserIdAndQuestionId(userId,questionId));
    }

    @PostMapping("getTheLatestCode")
    public Result getTheLatestCode(@RequestParam Long userId, @RequestParam Long questionId){
        return Result.success(codeService.getTheLatestCode(userId,questionId));
    }

}
