package com.moodleeducation.user.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Classes;
import com.moodleeducation.commoncore.entity.Major;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.user.dao.MajorDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService {
    @Autowired
    private MajorDao majorDao;

    public Result<PageInfo<Major>> listForPage(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<Major> majors = majorDao.listForPage();
        PageInfo<Major> info = new PageInfo<>(majors);
        if(!ObjectUtil.isEmpty(info)){
            return Result.success(info);
        }
        return Result.error(ResultEnum.ERROR);
    }
    public Result<Major> getById(Integer id){
        if(id<=0){
            return Result.error("参数有误");
        }
         Major byId = majorDao.getById(id);
        if(ObjectUtil.isNotEmpty(byId)){
            return Result.success(byId);
        }
        return Result.error(ResultEnum.ERROR);
    }
    public Result<Integer> save(Major major){
        if(ObjectUtil.isEmpty(major)){
            return Result.error("参数有误");
        }
         int save = majorDao.save(major);
        if(save<=0){
            return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
        }
        return Result.success(save);
    }
    public Result<Integer> update(Major major){
        if(ObjectUtil.isEmpty(major) || major.getMajorName().equals("null")){
            return Result.error("参数有误");
        }
         int update = majorDao.update(major);
        if(update<=0){
            return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
        }
        return Result.success(update);
    }
    public Result<Integer> delete(Integer id){
        if(id<=0) {
            return Result.error("参数有误");
        }
         int delete = majorDao.delete(id);
        if(delete<=0){
            return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
        }
        return Result.success(delete);
    }

}
