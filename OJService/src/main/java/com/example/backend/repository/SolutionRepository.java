package com.example.backend.repository;

import com.example.backend.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution,Long> {

    List<Solution> findSolutionByQuestionId(Long questionId);

    @Query(value = "select count(*) from solution where question_id = :questionId",nativeQuery = true)
    Integer findSolutionCount(@Param("questionId") Long questionId);

}
