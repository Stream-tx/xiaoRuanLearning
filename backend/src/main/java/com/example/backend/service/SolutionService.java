package com.example.backend.service;

import com.example.backend.entity.Solution;

import java.util.List;

public interface SolutionService {

    List<Solution> listSolutions(Long questionId);
}
