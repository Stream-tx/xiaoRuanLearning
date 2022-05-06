package com.example.accountservice.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDate;
@Proxy(lazy = false)
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate submitTime;
    private String language;

}
