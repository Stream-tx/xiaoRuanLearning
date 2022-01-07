package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long userId;
    private Long solutionId;
    private String content;
    private Long likes;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate commentTime;


}
