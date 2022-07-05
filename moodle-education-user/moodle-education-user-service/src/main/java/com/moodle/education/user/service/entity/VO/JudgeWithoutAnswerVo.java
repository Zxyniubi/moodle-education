package com.moodle.education.user.service.entity.VO;

import lombok.Data;

import java.io.Serializable;
@Data
public class JudgeWithoutAnswerVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer judgeId;
    private Integer quizId;
    private String question;
    private Integer score;
}
