package com.moodle.education.user.service.Feign.Controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.user.feign.interfaces.IFeignTeacher;
import com.moodle.education.user.service.Feign.Service.IFeignTeacherService;
import com.moodleeducation.commoncore.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IFeignTeacherController implements IFeignTeacher {
    @Autowired
    private IFeignTeacherService iFeignTeacherService;
    @Override
    public PageInfo<Teacher> getAllTeacher(Integer pageNum, Integer pageSize) {
        return iFeignTeacherService.getAllTeacher(pageNum,pageSize);
    }

    @Override
    public int deleteById(int id) {
        return iFeignTeacherService.deleteTeacher(id);
    }

    @Override
    public int updateById(Teacher teacher) {
        return iFeignTeacherService.updateTeacher(teacher);
    }

    @Override
    public int save(Teacher teacher) {
        return iFeignTeacherService.insertTeacher(teacher);
    }

    @Override
    public Teacher getById(String id) {
        return iFeignTeacherService.getById(id);
    }
}
