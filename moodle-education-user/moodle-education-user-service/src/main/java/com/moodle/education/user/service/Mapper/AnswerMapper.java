package com.moodle.education.user.service.Mapper;

import com.moodle.education.user.service.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AnswerMapper {
    int save(@Param("answerList") List<Answer> answerList);
}
