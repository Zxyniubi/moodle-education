package com.moodle.education.user.service.Mapper;

import com.moodle.education.user.service.entity.Choice;
import com.moodle.education.user.service.entity.Judge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuizQuestionMapper {
    List<Choice> listChoiceByQuizId(@Param("quizId") Integer id);
    List<Judge>  listJudgeByQuizId(@Param("quizId") Integer id);

    int updateChoice(@Param("id") Integer id);
    int updateJudge(@Param("id") Integer id);

    int deleteChoice(@Param("id") Integer id);
    int deleteJudge(@Param("id") Integer id);

    int saveChoice(@Param("choiceList") List<Choice> choiceList);
    int saveJudge(@Param("judgeList") List<Judge> judgeList);


}
