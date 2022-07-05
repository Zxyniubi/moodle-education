package com.moodleeducation.user.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.moodle.education.user.feign.interfaces.Bo.StudentSearchBo;
import com.moodle.education.user.feign.interfaces.IFeignStudent;
import com.moodleeducation.commoncore.base.BaseException;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Student;
import com.moodleeducation.commoncore.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private IFeignStudent iFeignStudent;

    public Result<PageInfo<Student>> getStudentByPage(Integer pageNum,Integer pageSize){
        if(pageNum<=0 || pageSize <=0){
            throw new BaseException(999,"参数有误");
        }
         PageInfo<Student> allStudent = iFeignStudent.getAllStudent(pageNum, pageSize);
        if (ObjectUtil.isNull(allStudent)) {
            throw new BaseException("查询失败");
        }
        return Result.success(allStudent);
    }

    public Result<Integer> updateStudent(Student student){
        if(student.getStudentId() == null){
            return Result.error("studentId不能为空");
        }
        int i = iFeignStudent.updateById(student);
        if(i>0){
            return Result.success(i);
        }
        return Result.error(ResultEnum.USER_UPDATE_FAIL);
    }
    public Result<Integer> save(Student student){
        if(student.getStudentId() == null){
            return Result.error("学号不能为空");
        }
        int i = iFeignStudent.save(student);
        if(i>0){
            return Result.success(i);
        }
        return Result.error(ResultEnum.USER_SAVE_FAIL);
    }
    public Result<Integer> delete(Integer id){
        if(id==null){
            return Result.error("id不能为空");
        }

        return Result.success(iFeignStudent.deleteById(id));
    }
    public Result<Student> getById(String id){
        if(id==null){
            return Result.error("学号不能为空");
        }
         Student student = iFeignStudent.getById(id);
        if(ObjectUtil.isNull(student)){
            throw new BaseException("查询失败");
        }
        return Result.success(student);
    }

    public Result<PageUtils<Student>> queryStudent(StudentSearchBo studentSearchBo){
        return iFeignStudent.query(studentSearchBo);
    }
}
