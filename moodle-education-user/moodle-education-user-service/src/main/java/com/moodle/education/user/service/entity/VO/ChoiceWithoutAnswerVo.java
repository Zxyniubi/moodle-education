package com.moodle.education.user.service.entity.VO;

import lombok.Data;

import java.io.Serializable;
@Data
public class ChoiceWithoutAnswerVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer choiceId;
    private Integer quizId;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer score;
}
