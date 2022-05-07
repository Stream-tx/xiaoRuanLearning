package com.example.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Data
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long questionId;
  private String name;
  private String description;
  private String labels;
  private String difficulty;
  private Long submission;
  private Long pass;
  private String returnType;
  private String argsType;


}
