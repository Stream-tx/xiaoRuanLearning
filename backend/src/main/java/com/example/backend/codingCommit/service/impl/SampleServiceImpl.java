package com.example.backend.codingCommit.service.impl;

import com.example.backend.codingCommit.entity.Sample;
import com.example.backend.codingCommit.repository.SampleRepository;
import com.example.backend.codingCommit.service.SampleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;

    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public List<Sample> listSamples(Long questionId) {
        return sampleRepository.findAllByQuestionIdAndIsTest(questionId,0L);
    }
}
