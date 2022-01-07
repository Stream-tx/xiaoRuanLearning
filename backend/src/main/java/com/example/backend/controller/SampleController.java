package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.Question;
import com.example.backend.entity.Sample;
import com.example.backend.service.SampleService;
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
