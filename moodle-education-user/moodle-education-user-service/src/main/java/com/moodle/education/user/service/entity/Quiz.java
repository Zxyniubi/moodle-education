package com.moodle.education.user.service.entity;

import com.moodleeducation.commoncore.entity.Grade;
import com.moodleeducation.commoncore.entity.Subject;
import com.moodleeducation.commoncore.entity.Teacher;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    //编号(自增长)
    private Integer quizId;
    //小测名称
    private String quizName;
    //小测时间
    private Integer quizDuration;
    //选择题个数
    private Integer choiceNum;
    //判断题个数;
    private Integer judgeNum;
    //选择题得分
    private Integer choiceScore;
    //判断题得分
    private Integer judgeScore;
    //总得分
    private Integer allScore;
    //选择题
    private List<Choice> choices;
    //判断题
    private List<Judge> judges;
    //科目
    private Subject subject;
    //年级
    private Grade grade;
    //出题教师;
    private Teacher teacher;

    private String quizDate;
}
