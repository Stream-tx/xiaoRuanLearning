package com.example.ojservice.controller;

import com.example.ojservice.common.Result;
import com.example.ojservice.entity.Sample;
import com.example.ojservice.service.SampleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("oj/sample")
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
