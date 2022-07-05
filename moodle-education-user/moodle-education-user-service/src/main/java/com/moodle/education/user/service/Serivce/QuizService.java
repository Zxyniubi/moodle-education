package com.moodle.education.user.service.Serivce;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodle.education.user.service.Mapper.QuizMapper;
import com.moodle.education.user.service.Mapper.StudentMapper;
import com.moodle.education.user.service.entity.DTO.QuizDTO;
import com.moodle.education.user.service.entity.Quiz;
import com.moodle.education.user.service.entity.VO.QuizVo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Student;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.commoncore.tools.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class QuizService {

    @Autowired
    private QuizMapper quizMapper;
    @Autowired
    private StudentMapper studentMapper;
    /**
     * 教师获取试题列表
     * @param pageNum
     * @param pageSize
     * @param token
     * @return
     */
    public Result<PageInfo<QuizVo>> listForPage(Integer pageNum,Integer pageSize,String token)  {
        if(pageNum<=0){
            pageNum=1;
        }
        if(pageSize<=0){
            pageSize=10;
        }
        Integer teacherId;
        try{
             DecodedJWT verify = JWTUtils.verify(token);
              teacherId = JWTUtils.getUserNo(verify);

        }catch (UnsupportedEncodingException unsupportedEncodingException){
            return Result.error(ResultEnum.TOKEN_ERROR);
        }
        if(teacherId == null){
            return Result.error(ResultEnum.ERROR);
        }
        PageHelper.startPage(pageNum,pageSize);
         Page<QuizVo> quizVos = quizMapper.listForPage(teacherId);
         PageInfo<QuizVo> info = new PageInfo<>(quizVos);
         if(ObjectUtil.isEmpty(info)){
             return Result.error("查找不到信息");
         }
         return Result.success(info);
    }

    /**
     * 教师新增试题
     * @param quizDTO
     * @return
     */
    public Result<Integer> save(QuizDTO quizDTO){
        if(ObjectUtil.isEmpty(quizDTO)){
            return Result.error("参数有误");
        }
         int save = quizMapper.save(quizDTO);
        if (save<=0){
            return Result.error(ResultEnum.USER_SAVE_FAIL);
        }
        return Result.success(save);
    }

    /**
     * 教师更新试题信息
     * @param quizDTO
     * @return
     */
    public Result<Integer> update(QuizDTO quizDTO){
        if (ObjectUtil.isEmpty(quizDTO)){
            return Result.error("参数有误");
        }
         int update = quizMapper.update(quizDTO);
        if(update<=0){
            return Result.error(ResultEnum.USER_UPDATE_FAIL);
        }
        return Result.success(update);
    }

    /**
     * 教师删除试题
     * @param id
     * @return
     */
    public Result<Integer> delete(Integer id){
        if(id<=0){
            return Result.error("参数有误");
        }
        int delete = quizMapper.delete(id);
        if(delete<=0){
            return Result.error(ResultEnum.USER_DELETE_FAIL);
        }
        return Result.success(delete);
    }

    /**
     * 学生获取试题
     * @param pageNum
     * @param pageSize
     * @param token
     * @param flag 0为未完成，1为已完成
     * @return
     */

    public Result<PageInfo<QuizVo>> listQuizForPageByStudentId(Integer pageNum,Integer pageSize,String token,Integer flag){
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        if(flag>1){
            flag=1;
        }

        Integer studentId;
        try{
            DecodedJWT verify = JWTUtils.verify(token);
            studentId = JWTUtils.getUserNo(verify);

        }catch (UnsupportedEncodingException unsupportedEncodingException){
            return Result.error(ResultEnum.TOKEN_ERROR);
        }
         Student student = studentMapper.getById(String.valueOf(studentId));
        if(ObjectUtil.isEmpty(student)){
            return Result.error(ResultEnum.TOKEN_ERROR);
        }
        PageHelper.startPage(pageNum,pageSize);
        Page<QuizVo> quizVos ;
        if(flag==0){
            quizVos=quizMapper.incompleteQuiz(student.getClasses().getClassesId(), student.getSid());
        }else{
            quizVos=quizMapper.completeQuiz(student.getClasses().getClassesId(), student.getSid());
        }
        if(CollectionUtil.isEmpty(quizVos)){
            return Result.error("查询不到信息");
        }
        PageInfo<QuizVo> quizVoPageInfo = new PageInfo<>(quizVos);
        return Result.success(quizVoPageInfo);
    }
}
