package com.example.onlinetoolsservice.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "chat")
@Data
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    private Long userId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate chatTime;
    private boolean isRobot;
    private Long sortId;

}
