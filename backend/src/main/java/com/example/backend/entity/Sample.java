package com.example.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sample")
@Data
public class Sample {

    @Id
    @GeneratedValue
    private Long sampleId;
    private Long questionId;
    private String input;
    private String output;
    private Long isTest;

}
