package com.example.backend.service;

import com.example.backend.entity.Sample;

import java.util.List;

public interface SampleService {

    List<Sample> listSamples(Long questionId);
}
