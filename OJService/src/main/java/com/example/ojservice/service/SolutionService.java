package com.example.ojservice.service;

import com.example.ojservice.entity.Solution;

import java.util.List;

public interface SolutionService {

    List<Solution> listSolutions(Long questionId);

    void likesIncrement(Long solutionId);

    Solution findBySolutionId(Long solutionId);

    void addSolution(Solution solution);

    List<Integer> findSolutionCount();

    void deleteSolution(Long solutionId);
}
