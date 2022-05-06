package com.example.accountservice.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
@Proxy(lazy = false)
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
