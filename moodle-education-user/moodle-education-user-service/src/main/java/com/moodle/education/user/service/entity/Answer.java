package com.moodle.education.user.service.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Answer implements Serializable {
    private static final long serialVersionUUID=1L;
    private Integer answerId;
    private Integer studentId;
    private Integer quizId;
    private Integer question;
    private Integer questionType;
    private String answer;
    private Integer score;
    private String goodAnswer;
}
