package com.example.ojservice.repository;

import com.example.ojservice.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample,Long> {

    List<Sample> findAllByQuestionIdAndIsTest(Long questionId,Long isTest);
}
