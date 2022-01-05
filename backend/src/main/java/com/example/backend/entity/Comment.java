package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue
    private Long commentId;
    private Long userId;
    private Long solutionId;
    private Long content;
    private Long likes;
    @JsonFormat(pattern = "yyyy-M-d")
    private LocalDate commentTime;


}
