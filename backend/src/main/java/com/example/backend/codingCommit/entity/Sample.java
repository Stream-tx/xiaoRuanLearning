package com.example.backend.codingCommit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sample")
@Data
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sampleId;
    private Long questionId;
    private String input;
    private String output;
    //是否是参考数据
    private Long isTest;

}
