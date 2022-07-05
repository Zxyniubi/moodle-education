package com.moodleeducation.user.Mapper;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Classes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassesMapper {
    public Page<Classes> listForPage();
    public Classes getById(Integer id);
    public int delete(Integer id);
    public int save(Classes classes);
    public int update(Classes classes);
    public Classes queryClassByGradeAndClasses(Integer classes,Integer gradeId);
}
