package com.example.onlinetoolsservice.repository;

import com.example.onlinetoolsservice.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<CodeEntity,Long> {
    List<CodeEntity> findAllByUserIdAndState(Long userId, Integer State);

    CodeEntity findCodeByUserIdAndQuestionIdOrderBySubmitTimeAsc(Long userId, Long questionId);

}
