package com.example.backend.codingCommit.controller;

import com.example.backend.result.Result;
import com.example.backend.codingCommit.entity.Sample;
import com.example.backend.codingCommit.service.SampleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sample")
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @PostMapping("listSamples")
    public Result listSamples(@RequestParam Long questionId){
        List<Sample> samples = sampleService.listSamples(questionId);
        return Result.success(samples);
    }
}
