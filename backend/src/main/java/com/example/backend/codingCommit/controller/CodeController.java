package com.example.backend.codingCommit.controller;

import com.example.backend.result.Result;
import com.example.backend.codingCommit.entity.Code;
import com.example.backend.codingCommit.service.CodeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("code")
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("getCodeByUidAndQid")
    public Result getCodeByUserIdAndQuestionId(@RequestParam Long userId, @RequestParam Long questionId) {
        return Result.success(codeService.getCodeByUserIdAndQuestionId(userId, questionId));
    }

    @PostMapping("getTheLatestCode")
    public Result getTheLatestCode(@RequestParam Long userId, @RequestParam Long questionId) {
        return Result.success(codeService.getTheLatestCode(userId, questionId));
    }

    @PostMapping("saveCode")
    public Result saveCode(@RequestBody Code code) {
        Code c = codeService.getTheLatestCode(code.getUserId(), code.getQuestionId());
        if (c != null) {
            c.setContent(code.getContent());
            codeService.updateCode(c);
        }
        else {
            code.setSubmitTime(LocalDate.now());
            code.setLanguage("JAVA");
            codeService.updateCode(code);
        }
        return Result.success(null);
    }

}
