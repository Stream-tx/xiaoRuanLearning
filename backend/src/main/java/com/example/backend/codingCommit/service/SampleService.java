package com.example.backend.codingCommit.service;

import com.example.backend.codingCommit.entity.Sample;

import java.util.List;

public interface SampleService {

    List<Sample> listSamples(Long questionId);
}
