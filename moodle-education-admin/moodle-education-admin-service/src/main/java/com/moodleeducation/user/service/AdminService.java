package com.moodleeducation.user.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodle.education.user.feign.interfaces.IFeignStudent;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Admin;
import com.moodleeducation.commoncore.entity.Permission;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.user.dao.AdminDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class AdminService {
    private  final  static Logger logger  = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private AdminDao adminDao;

    public Result<PageInfo<Admin>> getAllAdmin(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<Admin> allAdmin = adminDao.getAllAdmin();
        PageInfo<Admin> adminPageInfo = new PageInfo<>(allAdmin);
        if(!ObjectUtil.isEmpty(adminPageInfo)){
            return Result.success(adminPageInfo);
        }

        return Result.error(ResultEnum.ERROR);
    }
    public Result<Integer> save( Admin admin){
        if(ObjectUtil.isEmpty(admin) || admin.getAdminName() == null){
            return Result.error("参数有误");
        }
         int save = adminDao.save(admin);
        if(save<=0){
            return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
        }
        return Result.success(save);
    }
    public Result<Integer> update( Admin admin){
        if (ObjectUtil.isEmpty(admin)){
            return Result.error("参数有误");
        }
         int update = adminDao.update(admin);
        if(update<=0){
            return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
        }
        return Result.success(update);
    }
    public Result<Integer> delete(Integer id){
        if (id<=0) {
            return Result.error("参数有误");
        }
         int delete = adminDao.delete(id);
        if(delete<=0){
            return Result.error(ResultEnum.USER_DELETE_FAIL);
        }
        return Result.success(delete);
    }
    public Admin getByUserName(String userName) {
        return adminDao.getByUserName(userName) ;
    }
    public List<Permission> getPermissionById(Integer id) {
        return adminDao.getPermissionById(id);
    }

}
