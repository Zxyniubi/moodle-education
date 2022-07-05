package com.moodleeducation.user.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Classes;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.user.dao.ClassesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesService {
    @Autowired
    private ClassesDao classesDao;

    public Result<PageInfo<Classes>> getAllClasses(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<Classes> classes = classesDao.listForPage();
        PageInfo<Classes> info = new PageInfo<>(classes);
        if(!ObjectUtil.isEmpty(info)){
            return Result.success(info);
        }
        return Result.error(ResultEnum.ERROR);
    }
    public Result<Classes>getById(Integer id){
        if(id<=0){
            return new Result<>();
        }
        Classes byId = classesDao.getById(id);
         if(!ObjectUtil.isEmpty(byId)){
             return Result.success(byId);
         }
         return Result.error("请求失败，数据为空");
    }
    public Result<Integer> update(Classes classes){
        if(ObjectUtil.isEmpty(classes) || ObjectUtil.isEmpty(classes.getGrade()) || ObjectUtil.isEmpty(classes.getMajor())){
            return Result.error("参数有误");
        }
         int update = classesDao.update(classes);
        if(update<=0){
            return Result.error("更新失败");
        }
        return Result.success(update);
    }

    public Result<Integer> save(Classes classes){
        if(ObjectUtil.isEmpty(classes) || ObjectUtil.isEmpty(classes.getGrade()) || ObjectUtil.isEmpty(classes.getMajor())){
            return Result.error("参数有误");
        }
         int save = classesDao.save(classes);
        if(save<=0){
            return Result.error("添加失败");
        }
        return Result.success(save);
    }

    public Result<Integer> delete(Integer id){
        if(id<=0){
            return Result.error("参数有误");
        }
        int delete = classesDao.delete(id);
        if(delete<=0){
            return Result.error("删除失败");
        }
        return Result.success(delete);
    }

}
