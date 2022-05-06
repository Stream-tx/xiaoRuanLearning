package com.example.accountservice.repository;

import com.example.accountservice.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample,Long> {

    List<Sample> findAllByQuestionIdAndIsTest(Long questionId, Long isTest);
}
