package com.example.ojservice.service.impl;

import com.example.ojservice.entity.Sample;
import com.example.ojservice.repository.SampleRepository;
import com.example.ojservice.service.SampleService;
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
