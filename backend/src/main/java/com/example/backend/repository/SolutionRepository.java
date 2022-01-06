package com.example.backend.repository;

import com.example.backend.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution,Long> {

    List<Solution> findSolutionByQuestionId(Long questionId);
}
