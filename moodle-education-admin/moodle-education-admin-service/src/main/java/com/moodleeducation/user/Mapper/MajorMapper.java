package com.moodleeducation.user.Mapper;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MajorMapper {
    public Page<Major> listForPage();
    public Major getById(@Param("majorId") Integer id);
    public int delete(@Param("majorId")Integer id);
    public int save(Major major);
    public int update(Major major);

}
