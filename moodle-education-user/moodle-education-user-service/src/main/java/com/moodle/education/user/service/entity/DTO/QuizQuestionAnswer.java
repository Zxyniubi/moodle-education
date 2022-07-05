package com.moodle.education.user.service.entity.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuizQuestionAnswer implements Serializable {
    private static final long serialVersionUUID=1L;
    private Integer sid;
    private Integer quizId;
    private Integer question;
    private Integer questionType;
    private String answer;
}
