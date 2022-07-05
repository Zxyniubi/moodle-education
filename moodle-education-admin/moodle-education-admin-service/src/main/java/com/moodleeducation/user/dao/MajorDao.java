package com.moodleeducation.user.dao;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Major;
import org.apache.ibatis.annotations.Param;

public interface MajorDao {
    public Page<Major> listForPage();
    public Major getById(Integer id);
    public int delete(Integer id);
    public int save(Major major);
    public int update(Major major);
}
