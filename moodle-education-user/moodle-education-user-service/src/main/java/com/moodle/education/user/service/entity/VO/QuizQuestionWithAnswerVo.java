package com.moodle.education.user.service.entity.VO;

import com.moodle.education.user.service.entity.Choice;
import com.moodle.education.user.service.entity.Judge;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class QuizQuestionWithAnswerVo  implements Serializable {
    private Integer quizId;
    private List<Choice> choiceList;
    private List<Judge> judgeList;
}
