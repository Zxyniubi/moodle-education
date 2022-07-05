package com.moodleeducation.user.controller;

import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Teacher;
import com.moodleeducation.user.service.TeacherService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public Result<PageInfo<Teacher>> pageTeacher(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        return teacherService.getTeacherByPage(pageNum,pageSize);
    }
    @PostMapping("/delete/{id}")
    public Result<Integer> deleteTeacher(@PathVariable("id") Integer id){
        return teacherService.delete(id);
    }
    @PostMapping("/update")
    public Result<Integer> updateTeacher(@RequestBody Teacher Teacher){
        return teacherService.updateTeacher(Teacher);
    }
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Teacher Teacher){
        return  teacherService.save(Teacher);
    }
    @GetMapping("/getById/{id}")
    public Result<Teacher> getById(@PathVariable("id") String  id){
        return teacherService.getById(id);
    }
}
