package com.moodle.education.user.service.Mapper;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeacherMapper {
    Page<Teacher> getAllTeacher();
    Teacher getById(@Param("id") String tid);
    int deleteById(@Param("teacherId") Integer TeacherId);
    int insertTeacher(Teacher Teacher);
    int updateTeacher(Teacher Teacher);

}
