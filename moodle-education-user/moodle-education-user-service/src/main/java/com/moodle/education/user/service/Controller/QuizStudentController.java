package com.moodle.education.user.service.Controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.user.service.Serivce.QuizQuestionService;
import com.moodle.education.user.service.Serivce.QuizService;
import com.moodle.education.user.service.entity.DTO.QuizQuestionAnswer;
import com.moodle.education.user.service.entity.DTO.QuizQuestionAnswerList;
import com.moodle.education.user.service.entity.VO.QuizQuestionStudentVo;
import com.moodle.education.user.service.entity.VO.QuizVo;
import com.moodleeducation.commoncore.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user/student/quiz")
public class QuizStudentController {
    @Autowired
    private QuizQuestionService quizQuestionService;
    @Autowired
    private QuizService quizService;
    @RequestMapping(value = "/listQuizQuestion/{quizId}",method = RequestMethod.GET)
    public Result<QuizQuestionStudentVo> list(@PathVariable("quizId") Integer quizId){
        return quizQuestionService.quizQuestionStudent(quizId);
    }

    @RequestMapping(value = "/list/{pageNum}/{pageSize}/{flag}",method = RequestMethod.GET)
    public Result<PageInfo<QuizVo>> list(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize") Integer pageSize,@PathVariable("flag") Integer flag, @RequestHeader("Token") String token){
        return quizService.listQuizForPageByStudentId(pageNum,pageSize,token,flag);
    }

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result<Integer> submit(@RequestBody QuizQuestionAnswerList quizQuestionAnswerList,@RequestHeader("Token") String token){
        return quizQuestionService.submitQuiz(quizQuestionAnswerList,token);
    }
}
