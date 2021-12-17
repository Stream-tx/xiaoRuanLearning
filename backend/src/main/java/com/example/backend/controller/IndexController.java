package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.compile.ExecuteStringSourceService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class IndexController {

    private final ExecuteStringSourceService executeStringSourceService;

    public IndexController(ExecuteStringSourceService executeStringSourceService) {
        this.executeStringSourceService = executeStringSourceService;
    }

    @PostMapping("/submit")
    public Result submit(@RequestBody Map<String, String> data) {
        String runResult = executeStringSourceService.execute(data.get("code"), data.get("input"));
        return Result.success(runResult);
    }
}
