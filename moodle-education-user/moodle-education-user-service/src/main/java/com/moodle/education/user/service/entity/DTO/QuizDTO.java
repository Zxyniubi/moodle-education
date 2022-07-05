package com.moodle.education.user.service.entity.DTO;

import lombok.Data;

import java.io.Serializable;
@Data
public class QuizDTO implements Serializable {
    private final static long serialVersionUID=1L;
    private Integer quizId;
    //小测名称
    private String quizName;
    //小测时间
    private Integer quizDuration;

    private String subjectId;

    private String classesId;

    private String teacherId;

    private String quizDate;
}
