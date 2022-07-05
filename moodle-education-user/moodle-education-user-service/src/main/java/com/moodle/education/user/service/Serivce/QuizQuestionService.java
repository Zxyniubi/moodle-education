package com.moodle.education.user.service.Serivce;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.moodle.education.user.service.Mapper.AnswerMapper;
import com.moodle.education.user.service.Mapper.QuizQuestionMapper;
import com.moodle.education.user.service.Mapper.ScoreMapper;
import com.moodle.education.user.service.entity.Answer;
import com.moodle.education.user.service.entity.Choice;
import com.moodle.education.user.service.entity.DTO.QuizQuestionAnswer;
import com.moodle.education.user.service.entity.DTO.QuizQuestionAnswerList;
import com.moodle.education.user.service.entity.Judge;
import com.moodle.education.user.service.entity.Score;
import com.moodle.education.user.service.entity.VO.ChoiceWithoutAnswerVo;
import com.moodle.education.user.service.entity.VO.JudgeWithoutAnswerVo;
import com.moodle.education.user.service.entity.VO.QuizQuestionStudentVo;
import com.moodle.education.user.service.entity.VO.QuizQuestionWithAnswerVo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.commoncore.tools.JWTUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizQuestionService {
    @Autowired
    private QuizQuestionMapper mapper;
    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private ScoreMapper scoreMapper;
    public Result<QuizQuestionWithAnswerVo> list(Integer quizId){

         List<Judge> judgeList = mapper.listJudgeByQuizId(quizId);
         List<Choice> choiceList = mapper.listChoiceByQuizId(quizId);
         if (CollectionUtil.isEmpty(judgeList) && CollectionUtil.isEmpty(choiceList)){
             Result.error("试题为空");
         }
         QuizQuestionWithAnswerVo question = new QuizQuestionWithAnswerVo();
         question.setChoiceList(choiceList).setJudgeList(judgeList).setQuizId(quizId);
         return Result.success(question);
    }
    public Result<Integer> saveChoice(List<Choice> choices){
        if(CollectionUtil.isEmpty(choices)){
            return Result.error("查询不到信息");
        }
         int i = mapper.saveChoice(choices);
        if(i<=0){
            return Result.error(ResultEnum.USER_SAVE_FAIL);
        }
        return Result.success(i);
    }
    public Result<Integer> saveJudge(List<Judge> judges){
        if(CollectionUtil.isEmpty(judges)){
            return Result.error("参数有误");
        }
        int i = mapper.saveJudge(judges);
        if(i<=0){
            return Result.error(ResultEnum.USER_SAVE_FAIL);
        }
        return Result.success(i);
    }

    public Result<Integer> updateChoice(Choice choice){
        if(ObjectUtil.isEmpty(choice)){
            return Result.error("参数有误");
        }
         int i = mapper.updateChoice(choice.getChoiceId());
        if(i<=0){
            return Result.error(ResultEnum.USER_UPDATE_FAIL);
        }
        return Result.success(i);
    }
    public Result<Integer> updateJudge(Judge judge){
        if(ObjectUtil.isEmpty(judge)){
            return Result.error("参数有误");
        }
         int i = mapper.updateJudge(judge.getJudgeId());
        if(i<=0){
            return Result.error(ResultEnum.USER_UPDATE_FAIL);
        }
        return Result.success(i);
    }

    public Result<Integer> deleteChoice(Integer id){
        if(id<=0){
            return Result.error("参数有误");
        }
         int i = mapper.deleteChoice(id);
        if(i<=0){
            return Result.error(ResultEnum.USER_DELETE_FAIL);
        }
        return Result.success(i);
    }

    public Result<Integer> deleteJudge(Integer id){
        if(id<=0){
            return Result.error("参数有误");
        }
        int i=mapper.deleteJudge(id);
        if(i<=0){
            return Result.error(ResultEnum.USER_DELETE_FAIL);
        }
        return Result.success(i);
    }

    /**
     * 学生获取试题题目
     * @param quizId
     * @return
     */
    public Result<QuizQuestionStudentVo> quizQuestionStudent(Integer quizId){
        if(quizId<=0){
            return Result.error("参数有误");
        }
         List<Judge> judgeList = mapper.listJudgeByQuizId(quizId);
         List<Choice> choiceList = mapper.listChoiceByQuizId(quizId);
         if(CollectionUtil.isEmpty(judgeList) && CollectionUtil.isEmpty(choiceList)){
             return Result.error("查询不到信息");
         }
        List<JudgeWithoutAnswerVo> judgeWithoutAnswerVoList = new ArrayList<>();
         List<ChoiceWithoutAnswerVo> choiceWithoutAnswerVoList = new ArrayList<>();
         for(Judge judge:judgeList){
             judgeWithoutAnswerVoList.add(BeanUtil.copyProperties(judge,JudgeWithoutAnswerVo.class));
         }
         for(Choice choice:choiceList){
             choiceWithoutAnswerVoList.add(BeanUtil.copyProperties(choice,ChoiceWithoutAnswerVo.class));
         }
         QuizQuestionStudentVo vo = new QuizQuestionStudentVo();
         vo.setJudgeWithoutAnswerVoList(judgeWithoutAnswerVoList);
         vo.setChoiceWithoutAnswerVoList(choiceWithoutAnswerVoList);
         return Result.success(vo);
    }

    public Result<Integer> submitQuiz(QuizQuestionAnswerList answerList,String token){
        if(CollectionUtil.isEmpty(answerList.getAnswerList())){
            return Result.error("参数有误");
        }
        Integer sid = null;
        try {
            sid = JWTUtils.getUserNo(JWTUtils.verify(token));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        if(ObjectUtil.isEmpty(sid)){
            return Result.error("token有误");
        }
        Integer quizId = answerList.getAnswerList().get(0).getQuizId();
         List<Choice> choiceList = mapper.listChoiceByQuizId(quizId);
         List<Judge> judgeList = mapper.listJudgeByQuizId(quizId);
         if(CollectionUtil.isEmpty(choiceList)&&CollectionUtil.isEmpty(judgeList)){
             return Result.error("查询不到试卷信息");
         }
         List<Answer> studentAnswerList = new ArrayList<>();
         List<QuizQuestionAnswer> choiceAnswerList = answerList.getAnswerList().stream().filter(answer->answer.getQuestionType()==1).collect(Collectors.toList());
         List<QuizQuestionAnswer> judgeAnswerList =  answerList.getAnswerList().stream().filter(answer->answer.getQuestionType()==2).collect(Collectors.toList());

         Integer choiceScore=0;
         Integer judgeScore =0;
         if(CollectionUtil.isNotEmpty(choiceList)) {
             for (Choice choice : choiceList) {
                 for (QuizQuestionAnswer studentAnswer : choiceAnswerList) {
                     if (choice.getChoiceId().equals(studentAnswer.getQuestion())) {
                         Answer answer = BeanUtil.copyProperties(studentAnswer, Answer.class);
                         if (studentAnswer.getAnswer().equals(choice.getAnswer())) {
                             answer.setScore(choice.getScore());
                             answer.setStudentId(sid);
                             answer.setGoodAnswer(choice.getAnswer());
                             studentAnswerList.add(answer);
                             choiceScore += choice.getScore();
                         }
                         studentAnswerList.add(answer);
                     }
                 }
             }
         }
         if(CollectionUtil.isNotEmpty(judgeList)){
         for(Judge judge:judgeList){
            for(QuizQuestionAnswer studentAnswer:judgeAnswerList){
                if (judge.getJudgeId().equals(studentAnswer.getQuestion())){
                    Answer answer  = BeanUtil.copyProperties(studentAnswer,Answer.class);
                    if(studentAnswer.getAnswer().equals(judge.getAnswer()) ){
                        answer.setScore(judge.getScore());
                        answer.setStudentId(sid);
                        answer.setGoodAnswer(judge.getAnswer());
                        judgeScore += judge.getScore();
                    }
                    studentAnswerList.add(answer);
                }
            }
        }
     }
         int save = answerMapper.save(studentAnswerList);
         if(save<=0){
             return Result.error(ResultEnum.USER_SAVE_FAIL);
         }

        Score score = new Score();
        score.setSid(sid).setQuizId(quizId).setChoiceScore(choiceScore).setJudgeScore(judgeScore).setAllScore(choiceScore+judgeScore);
         int scoreSave = scoreMapper.save(score);
        if(scoreSave<=0) {
            return Result.error(ResultEnum.USER_SAVE_FAIL);
        }
        return Result.success(save);
    }
}
