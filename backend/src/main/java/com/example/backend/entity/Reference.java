package com.example.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reference")
@Data
public class Reference {
    @Id
    @GeneratedValue
    private Long referenceId;
    private String name;
    private String introduction;


}
