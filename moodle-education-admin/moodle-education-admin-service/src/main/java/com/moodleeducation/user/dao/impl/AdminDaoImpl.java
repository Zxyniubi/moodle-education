package com.moodleeducation.user.dao.impl;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Admin;
import com.moodleeducation.commoncore.entity.Permission;
import com.moodleeducation.user.Mapper.AdminMapper;
import com.moodleeducation.user.Mapper.PermissionMapper;
import com.moodleeducation.user.dao.AdminDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Admin getByUserName(String userName) {
        return adminMapper.getByUserName(userName) ;
    }

    @Override
    public List<Permission> getPermissionById(Integer id) {
        return permissionMapper.getPermissionById(id);
    }

    @Override
    public Page<Admin> getAllAdmin() {
        return adminMapper.getAllAdmin();
    }

    @Override
    public int delete(Integer id) {
        return adminMapper.delete(id);
    }

    @Override
    public int save(Admin admin) {
        return adminMapper.save(admin);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }
}
