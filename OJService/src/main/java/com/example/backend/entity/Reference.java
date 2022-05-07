package com.example.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reference")
@Data
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenceId;
    private String name;
    private String introduction;
    private String url;


}
