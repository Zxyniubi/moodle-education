package com.moodleeducation.user.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.moodle.education.user.feign.interfaces.IFeignTeacher;
import com.moodleeducation.commoncore.base.BaseException;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Teacher;
import com.moodleeducation.commoncore.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private IFeignTeacher iFeignTeacher;

    public Result<PageInfo<Teacher>> getTeacherByPage(Integer pageNum, Integer pageSize){
        if(pageNum<=0 || pageSize <=0){
            throw new BaseException(999,"参数有误");
        }
        PageInfo<Teacher> allTeacher = iFeignTeacher.getAllTeacher(pageNum, pageSize);
        if (ObjectUtil.isNull(allTeacher)) {
            throw new BaseException("查询失败");
        }
        return Result.success(allTeacher);
    }

    public Result<Integer> updateTeacher(Teacher Teacher){
        if(Teacher.getTeacherId() == null){
            return Result.error("TeacherId不能为空");
        }
        int i = iFeignTeacher.updateById(Teacher);
        if(i>0){
            return Result.success(i);
        }
        return Result.error(ResultEnum.USER_UPDATE_FAIL);
    }
    public Result<Integer> save(Teacher Teacher){
        if(Teacher.getTeacherId() == null){
            return Result.error("教师号不能为空");
        }
        int i = iFeignTeacher.save(Teacher);
        if(i>0){
            return Result.success(i);
        }
        return Result.error(ResultEnum.USER_SAVE_FAIL);
    }
    public Result<Integer> delete(Integer id){
        if(id==null){
            return Result.error("id不能为空");
        }
         int i = iFeignTeacher.deleteById(id);
        if(i<=0){
            return Result.error(ResultEnum.USER_DELETE_FAIL);
        }
        return Result.success(i);

    }
    public Result<Teacher> getById(String id){
        if(id==null){
            return Result.error("教师号不能为空");
        }
        Teacher Teacher = iFeignTeacher.getById(id);
        if(ObjectUtil.isNull(Teacher)){
            throw new BaseException("查询失败");
        }
        return Result.success(Teacher);
    }
}
