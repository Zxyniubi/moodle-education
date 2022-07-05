package com.moodleeducation.user.dao;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Classes;

public interface ClassesDao {

    public Page<Classes> listForPage();
    public Classes getById(Integer id);
    public int delete(Integer id);
    public int save(Classes classes);
    public int update(Classes classes);
    public Classes queryClassByGradeAndClasses(Integer classes,Integer gradeId);

}
