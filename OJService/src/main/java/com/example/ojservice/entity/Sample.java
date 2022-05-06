package com.example.ojservice.entity;

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
    private Long isTest;

}
