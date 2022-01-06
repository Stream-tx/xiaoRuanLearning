package com.example.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
@Data
public class Question {

  @Id
  @GeneratedValue
  private Long questionId;
  private String name;
  private String description;
  private String labels;
  private String difficulty;
  private Long submission;
  private Long pass;


}
