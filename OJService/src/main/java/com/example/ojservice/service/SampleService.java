package com.example.ojservice.service;

import com.example.ojservice.entity.Sample;

import java.util.List;

public interface SampleService {

    List<Sample> listSamples(Long questionId);
}
