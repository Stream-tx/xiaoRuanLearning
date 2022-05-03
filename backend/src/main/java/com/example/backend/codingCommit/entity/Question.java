package com.example.backend.codingCommit.entity;

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
  //总提交数
  private Long submission;
  //总通过数
  private Long pass;

  //测试样例返回值类型
  private String returnType;
  //测试样例输入值类型
  private String argsType;


}
