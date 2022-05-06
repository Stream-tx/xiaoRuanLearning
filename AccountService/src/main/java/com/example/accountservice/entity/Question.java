package com.example.accountservice.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
@Proxy(lazy = false)
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
