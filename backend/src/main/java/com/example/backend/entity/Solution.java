package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "solution")
@Data
public class Solution {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long solutionId;
  private String content;
  private Long userId;
  private Long questionId;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate createdTime;
  private String language;
  private String title;
  private String code;
  private Long likes;

}
