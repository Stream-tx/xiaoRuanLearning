package com.example.onlinetoolsservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "question", schema = "miniSoft", catalog = "")
public class QuestionEntity {
    private long questionId;
    private String argsType;
    private String description;
    private String difficulty;
    private String labels;
    private String name;
    private Long pass;
    private String returnType;
    private Long submission;

    @Id
    @Column(name = "question_id")
    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "args_type")
    public String getArgsType() {
        return argsType;
    }

    public void setArgsType(String argsType) {
        this.argsType = argsType;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "difficulty")
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Basic
    @Column(name = "labels")
    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pass")
    public Long getPass() {
        return pass;
    }

    public void setPass(Long pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "return_type")
    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Basic
    @Column(name = "submission")
    public Long getSubmission() {
        return submission;
    }

    public void setSubmission(Long submission) {
        this.submission = submission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionEntity that = (QuestionEntity) o;
        return questionId == that.questionId && Objects.equals(argsType, that.argsType) && Objects.equals(description, that.description) && Objects.equals(difficulty, that.difficulty) && Objects.equals(labels, that.labels) && Objects.equals(name, that.name) && Objects.equals(pass, that.pass) && Objects.equals(returnType, that.returnType) && Objects.equals(submission, that.submission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, argsType, description, difficulty, labels, name, pass, returnType, submission);
    }
}
