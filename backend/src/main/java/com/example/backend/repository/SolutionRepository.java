package com.example.backend.repository;

import com.example.backend.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution,Long> {

    List<Solution> findSolutionByQuestionId(Long questionId);

    @Query(value = "select count(*) from solution group by question_id order by question_id",nativeQuery = true)
    List<Integer> findSolutionCount();
}
