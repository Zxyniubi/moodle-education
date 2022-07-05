package com.moodle.education.user.service.Controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.user.service.Serivce.QuizQuestionService;
import com.moodle.education.user.service.Serivce.QuizService;
import com.moodle.education.user.service.entity.Choice;
import com.moodle.education.user.service.entity.DTO.QuizDTO;
import com.moodle.education.user.service.entity.Judge;
import com.moodle.education.user.service.entity.VO.QuizQuestionWithAnswerVo;
import com.moodle.education.user.service.entity.VO.QuizVo;
import com.moodleeducation.commoncore.base.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/teacher/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<PageInfo<QuizVo>> list(@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestHeader("Token")String token){
        return quizService.listForPage(pageNum,pageSize,token);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody QuizDTO quizDTO){
        return quizService.save(quizDTO);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody QuizDTO quizDTO){
        return quizService.update(quizDTO);
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public Result<Integer> delete(@PathVariable("id") Integer id){
        return quizService.delete(id);
    }

    @RequestMapping(value = "/question/list/{quizId}",method = RequestMethod.GET)
    public Result<QuizQuestionWithAnswerVo> list(@PathVariable("quizId") Integer quizId){
        return quizQuestionService.list(quizId);
    }

    @RequestMapping(value = "/question/saveChoice",method = RequestMethod.POST)
    public Result<Integer> saveChoice(@RequestBody List<Choice> choices){
        return quizQuestionService.saveChoice(choices);
    }

    @RequestMapping(value = "/question/saveJudge",method = RequestMethod.POST)
    public Result<Integer> saveJudge(@RequestBody List<Judge> judges){
        return quizQuestionService.saveJudge(judges);
    }

    @RequestMapping(value = "/question/updateChoice",method = RequestMethod.POST)
    public Result<Integer> updateChoice(@RequestBody Choice choice){
        return quizQuestionService.updateChoice(choice);
    }

    @RequestMapping(value = "/question/updateJudge",method = RequestMethod.POST)
    public Result<Integer> updateJudge(@RequestBody Judge judge){
        return quizQuestionService.updateJudge(judge);
    }

    @RequestMapping(value = "/question/deleteChoice/{id}",method = RequestMethod.POST)
    public Result<Integer> deleteChoice(@PathVariable("id") Integer id){
        return quizQuestionService.deleteChoice(id);
    }

    @RequestMapping(value = "/question/deleteJudge/{id}",method = RequestMethod.POST)
    public Result<Integer> deleteJudge(@PathVariable("id") Integer id){
        return quizQuestionService.deleteJudge(id);
    }
}

