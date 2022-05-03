package com.example.backend.codingCommit.service;

import com.example.backend.codingCommit.entity.Solution;

import java.util.List;

public interface SolutionService {

    List<Solution> listSolutions(Long questionId);

    void likesIncrement(Long solutionId);

    Solution findBySolutionId(Long solutionId);

    void addSolution(Solution solution);

    List<Integer> findSolutionCount();

    void deleteSolution(Long solutionId);
}
