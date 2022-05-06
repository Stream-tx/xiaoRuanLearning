package com.example.ojservice.controller;


import com.example.ojservice.common.Result;
import com.example.ojservice.entity.Solution;
import com.example.ojservice.service.SolutionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("oj/solution")
public class SolutionController {

    private final SolutionService solutionService;

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping("listSolutions")
    public Result listSolutions(@RequestParam Long questionId) {
        List<Solution> solutions = solutionService.listSolutions(questionId);
        return Result.success(solutions);
    }

    @PostMapping("addSolution")
    public Result addSolution(@RequestBody Solution solution) {
        solutionService.addSolution(solution);
        return Result.success(null);
    }


    @PostMapping("getSolutionCount")
    public Result getSolutionCount() {
        return Result.success(solutionService.findSolutionCount());
    }

    @PostMapping("likesIncrement")
    public Result likesIncrement(Long solutionId) {
        solutionService.likesIncrement(solutionId);
        return Result.success(null);
    }

    @PostMapping("deleteSolution")
    public Result deleteSolution(@RequestParam Long solutionId){
        solutionService.deleteSolution(solutionId);
        return Result.success(null);
    }

    @PostMapping("getSolutionById")
    public Result getSolutionById(@RequestParam Long solutionId){
        return Result.success(solutionService.findBySolutionId(solutionId).getUserId());
    }
}
