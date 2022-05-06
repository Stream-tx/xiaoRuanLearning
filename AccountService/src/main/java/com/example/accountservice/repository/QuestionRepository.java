package com.example.accountservice.repository;

import com.example.accountservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value = "select * from question order by question_id",nativeQuery = true)
    List<Question> listQuestion();
}
