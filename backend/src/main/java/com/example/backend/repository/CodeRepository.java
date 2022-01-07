package com.example.backend.repository;

import com.example.backend.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code,Long> {

    List<Code> findAllByUserIdAndQuestionId(Long userId, Long questionId);

    Code findCodeByUserIdAndQuestionIdOrderBySubmitTimeAsc(Long userId, Long questionId);

}
