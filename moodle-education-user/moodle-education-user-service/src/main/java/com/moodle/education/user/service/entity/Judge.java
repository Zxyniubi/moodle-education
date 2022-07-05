package com.moodle.education.user.service.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Judge implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer judgeId;
    private Integer quizId;
    private String question;
    private String answer;
    private String analysis;
    private Integer score;
}
