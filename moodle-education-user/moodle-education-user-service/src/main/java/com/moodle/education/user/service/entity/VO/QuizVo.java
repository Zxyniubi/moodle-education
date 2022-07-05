package com.moodle.education.user.service.entity.VO;

import lombok.Data;

import java.io.Serializable;
@Data
public class QuizVo implements Serializable {
    private Integer quizId;
    //小测名称
    private String quizName;
    //小测时间
    private Integer quizDuration;

    private String majorName;

    private String subjectName;

    private String gradeName;

    private String teacherName;

    private String quizDate;
}
