package com.moodleeducation.user.controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.user.feign.interfaces.Bo.StudentSearchBo;
import com.moodle.education.user.feign.interfaces.IFeignStudent;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Student;
import com.moodleeducation.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

   @GetMapping("/list")
    public Result<PageInfo<Student>> pageStudent(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
    return studentService.getStudentByPage(pageNum,pageSize);
   }
   @PostMapping("/delete/{id}")
    public Result<Integer> deleteStudent(@PathVariable("id") Integer id){
       return studentService.delete(id);
   }
   @PostMapping("/update")
    public Result<Integer> updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
   }
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Student student){
       return  studentService.save(student);
    }
    @GetMapping("/getById/{id}")
    public Result<Student> getById(@PathVariable("id") String id){
       return studentService.getById(id);
    }

    @PostMapping ("/query")
    public Result<PageUtils<Student>> query(@RequestBody StudentSearchBo studentSearchBo){
       return studentService.queryStudent(studentSearchBo);
    }

}
