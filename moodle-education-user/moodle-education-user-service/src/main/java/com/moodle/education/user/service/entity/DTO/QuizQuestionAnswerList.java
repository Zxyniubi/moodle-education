package com.moodle.education.user.service.entity.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class QuizQuestionAnswerList implements Serializable {
    private static final long serialVersionUUID=1L;

    private List<QuizQuestionAnswer> answerList;
}
