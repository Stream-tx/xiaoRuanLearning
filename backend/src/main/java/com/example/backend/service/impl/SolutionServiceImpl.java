package com.example.backend.service.impl;

import com.example.backend.entity.Solution;
import com.example.backend.repository.SolutionRepository;
import com.example.backend.service.SolutionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {
    private final SolutionRepository solutionRepository;

    public SolutionServiceImpl(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Override
    public List<Solution> listSolutions(Long questionId) {
        return solutionRepository.findSolutionByQuestionId(questionId);
    }
}
