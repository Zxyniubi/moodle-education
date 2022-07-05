package com.moodleeducation.user.dao;



import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Admin;
import com.moodleeducation.commoncore.entity.Permission;

import java.util.List;


public interface AdminDao {
    Admin getByUserName(String userName);
    List<Permission>getPermissionById(Integer id);
    Page<Admin> getAllAdmin();
    public int delete(Integer id);
    public int save(Admin admin);
    public int update(Admin admin);
}
