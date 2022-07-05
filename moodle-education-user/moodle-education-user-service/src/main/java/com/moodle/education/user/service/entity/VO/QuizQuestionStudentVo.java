package com.moodle.education.user.service.entity.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class QuizQuestionStudentVo implements Serializable {
    private static final long serialVersionUUID=1L;
    private List<ChoiceWithoutAnswerVo> choiceWithoutAnswerVoList;
    private List<JudgeWithoutAnswerVo> judgeWithoutAnswerVoList;
}
