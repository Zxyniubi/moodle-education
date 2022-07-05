package com.moodle.education.user.service.Mapper;

import com.github.pagehelper.Page;
import com.moodle.education.user.service.entity.DTO.QuizDTO;
import com.moodle.education.user.service.entity.VO.QuizVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuizMapper {
    Page<QuizVo> listForPage(@Param("id") Integer teacherId);
    int save(QuizDTO quizDTO);
    int delete(Integer id);
    int update(QuizDTO quizDTO);

    Page<QuizVo> incompleteQuiz(@Param("classesId") Integer classesId,@Param("sid")Integer sid);
    Page<QuizVo> completeQuiz(@Param("classesId") Integer classesId,@Param("sid")Integer sid);
}
