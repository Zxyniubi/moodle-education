package com.moodleeducation.user.dao.impl;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Classes;
import com.moodleeducation.user.Mapper.ClassesMapper;
import com.moodleeducation.user.dao.ClassesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassesDaoImpl implements ClassesDao {
    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public Page<Classes> listForPage() {
        return classesMapper.listForPage();
    }

    @Override
    public Classes getById(Integer id) {
        return classesMapper.getById(id);
    }

    @Override
    public int delete(Integer id) {
        return classesMapper.delete(id);
    }

    @Override
    public int save(Classes classes) {
        return classesMapper.save(classes);
    }

    @Override
    public int update(Classes classes) {
        return classesMapper.update(classes);
    }

    @Override
    public Classes queryClassByGradeAndClasses(Integer classes, Integer gradeId) {
        return classesMapper.queryClassByGradeAndClasses(classes,gradeId);
    }
}
