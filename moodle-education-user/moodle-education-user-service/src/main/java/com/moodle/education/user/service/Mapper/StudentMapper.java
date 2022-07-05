package com.moodle.education.user.service.Mapper;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

    Page<Student> getAllStudent();
    Student getById(@Param("studentId") String studentId);
    int deleteById(@Param("studentId") Integer studentId);
    int insertStudent(Student student);
    int updateStudent(Student student);

}
