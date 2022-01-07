package com.example.backend.service.impl;

import com.example.backend.entity.Solution;
import com.example.backend.repository.SolutionRepository;
import com.example.backend.service.SolutionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public void likesIncrement(Long solutionId) {
        Solution solution = findBySolutionId(solutionId);
        Long likes = solution.getLikes() + 1;
        solution.setLikes(likes);
        solutionRepository.save(solution);
    }

    @Override
    public Solution findBySolutionId(Long solutionId) {
        return solutionRepository.getById(solutionId);
    }

    @Override
    public void addSolution(Solution solution) {
        solution.setCreatedTime(LocalDate.now());
        solutionRepository.save(solution);
    }

    @Override
    public List<Integer> findSolutionCount() {
        return solutionRepository.findSolutionCount();
    }
}
