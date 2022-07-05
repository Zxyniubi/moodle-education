package com.moodle.education.user.service.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class Score implements Serializable {
    private static final long serialVersionUUID=1L;

    private Integer sid;
    private Integer quizId;
    private Integer choiceScore;
    private Integer judgeScore;
    private Integer allScore;
}
