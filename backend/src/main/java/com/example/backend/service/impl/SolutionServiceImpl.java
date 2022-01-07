package com.example.backend.service.impl;

import com.example.backend.entity.Question;
import com.example.backend.entity.Solution;
import com.example.backend.repository.QuestionRepository;
import com.example.backend.repository.SolutionRepository;
import com.example.backend.service.SolutionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {
    private final SolutionRepository solutionRepository;
    private final QuestionRepository questionRepository;

    public SolutionServiceImpl(SolutionRepository solutionRepository, QuestionRepository questionRepository) {
        this.solutionRepository = solutionRepository;
        this.questionRepository = questionRepository;
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
        List<Integer> counts = new ArrayList<>();
        List<Question> all = questionRepository.listQuestion();
        for(Question question : all){
            Long questionId = question.getQuestionId();
            Integer solutionCount = solutionRepository.findSolutionCount(questionId);
            counts.add(solutionCount);
        }
        return counts;
    }

    @Override
    public void deleteSolution(Long solutionId) {
        solutionRepository.deleteById(solutionId);
    }
}
