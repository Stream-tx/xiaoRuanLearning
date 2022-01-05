package com.example.backend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "code")
@Data
public class Code {

    @Id
    @GeneratedValue
    private Long codeId;
    private Long userId;
    private Long questionId;
    private String content;
    private String result;
    private Long state;
    @JsonFormat(pattern = "yyyy-M-d")
    private LocalDate submitTime;

}
