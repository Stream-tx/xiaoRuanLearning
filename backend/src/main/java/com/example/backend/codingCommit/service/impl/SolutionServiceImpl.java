package com.example.backend.codingCommit.service.impl;

import com.example.backend.codingCommit.entity.Question;
import com.example.backend.codingCommit.entity.Solution;
import com.example.backend.codingCommit.repository.QuestionRepository;
import com.example.backend.codingCommit.repository.SolutionRepository;
import com.example.backend.codingCommit.service.SolutionService;
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

    //通过问题id找出所有题解
    @Override
    public List<Solution> listSolutions(Long questionId) {
        return solutionRepository.findSolutionByQuestionId(questionId);
    }

    //增加解决方案点赞数
    @Override
    public void likesIncrement(Long solutionId) {
        Solution solution = findBySolutionId(solutionId);
        Long likes = solution.getLikes() + 1;
        solution.setLikes(likes);
        solutionRepository.save(solution);
    }

    //通过题解id查找题解
    @Override
    public Solution findBySolutionId(Long solutionId) {
        return solutionRepository.getById(solutionId);
    }

    //保存用户上传题解
    @Override
    public void addSolution(Solution solution) {
        solution.setCreatedTime(LocalDate.now());
        solutionRepository.save(solution);
    }

    //当前题目题解数
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

    //用户删除题解
    @Override
    public void deleteSolution(Long solutionId) {
        solutionRepository.deleteById(solutionId);
    }
}
