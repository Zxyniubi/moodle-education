package com.moodleeducation.user.Mapper;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Grade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GradeMapper {


    public Grade getById(Integer gradeId);
    public Page<Grade>listForPage();
    public int delete(Integer gradeId);
    public int update(Integer gradeId);
    public int save(Grade grade);

    }
