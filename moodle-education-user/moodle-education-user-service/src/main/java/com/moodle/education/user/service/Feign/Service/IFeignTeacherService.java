package com.moodle.education.user.service.Feign.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodle.education.user.service.Mapper.TeacherMapper;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Teacher;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.commoncore.tools.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class IFeignTeacherService {
    private final static Logger log  = LoggerFactory.getLogger(IFeignTeacherService.class);
    @Autowired
    private TeacherMapper TeacherMapper;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public PageInfo<Teacher> getAllTeacher(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<Teacher> allTeacher = TeacherMapper.getAllTeacher();
        return new PageInfo<Teacher>(allTeacher);
    }
    public int insertTeacher(Teacher teacher){
         String password = MD5Util.MD5Utils(teacher.getPassword(), teacher.getTeacherId(), 2);
         teacher.setPassword(password);
        int i = TeacherMapper.insertTeacher(teacher);
        IndexQuery query = new IndexQueryBuilder().withObject(teacher).build();
        elasticsearchRestTemplate.index(query, IndexCoordinates.of("teacher"));
        return i;
    }
    public int deleteTeacher(Integer TeacherId){
        elasticsearchRestTemplate.delete(TeacherId.toString(),Teacher.class);
        return TeacherMapper.deleteById(TeacherId);
    }
    public int updateTeacher(Teacher Teacher){
        return TeacherMapper.updateTeacher(Teacher);
    }
    public Teacher getById(String TeacherId){
        return TeacherMapper.getById(TeacherId);
    }

}
