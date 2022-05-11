package com.example.backend.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "code")
@Data
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeId;
    private Long userId;
    private Long questionId;
    private String content;
    private Long state;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp submitTime;
    private String language;

}
