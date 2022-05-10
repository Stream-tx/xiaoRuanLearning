package com.example.onlinetoolsservice.repository;

import com.example.onlinetoolsservice.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {

    @Query(value = "select * from question order by question_id",nativeQuery = true)
    List<QuestionEntity> listQuestion();
}
