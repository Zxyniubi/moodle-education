package com.moodleeducation.user.Mapper;

import com.github.pagehelper.Page;

import com.moodleeducation.commoncore.entity.Admin;
import com.moodleeducation.commoncore.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

     Admin getByUserName(String userName);
     Page<Admin> getAllAdmin();
     int delete(Integer id);
     int save(Admin admin);
     int update(Admin admin);
}
