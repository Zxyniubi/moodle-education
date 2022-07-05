package com.moodle.education.user.service.Feign.Controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.user.feign.interfaces.Bo.StudentSearchBo;
import com.moodle.education.user.feign.interfaces.IFeignStudent;
import com.moodle.education.user.service.Feign.Service.IFeignStudentService;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IFeignStudentController implements IFeignStudent {
    @Autowired
    private IFeignStudentService iFeignStudentService;

    public PageInfo<Student> getAllStudent(Integer pageNum, Integer pageSize) {
        return iFeignStudentService.getAllStudent(pageNum,pageSize);
    }

    public int deleteById(Integer id) {
        return iFeignStudentService.deleteStudent(id);
    }

    public int updateById(Student student) {
        return iFeignStudentService.updateStudent(student);
    }

    public int save(Student student) {
        return iFeignStudentService.insertStudent(student);
    }

    public Student getById(String id) {
        return iFeignStudentService.getById(id);
    }

    public Result<PageUtils<Student>> query(StudentSearchBo studentInfoQo) {
        return iFeignStudentService.searchList(studentInfoQo);
    }
}
