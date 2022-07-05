package com.moodle.education.user.service.Serivce;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodle.education.user.service.Mapper.RosterMapper;
import com.moodle.education.user.service.entity.Roster;
import com.moodle.education.user.service.entity.VO.RosterStudentVo;
import com.moodle.education.user.service.entity.VO.RosterVo;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.commoncore.tools.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@Slf4j
public class RosterService {
    @Autowired
    private RosterMapper rosterMapper;

    public Result<PageUtils<RosterVo>> list(String token){
        Integer userNo = null;
        try {
              userNo = JWTUtils.getUserNo(JWTUtils.verify(token));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        if(userNo==null){
            return Result.error(ResultEnum.TOKEN_ERROR);
        }
         List<RosterVo> rosterVoList = rosterMapper.list(userNo);
        PageUtils<RosterVo> pageUtils = new PageUtils<>();
        pageUtils.setList(rosterVoList);

            log.info(String.valueOf(userNo));
        if(ObjectUtil.isEmpty(rosterVoList)){
            return Result.error("查询不到信息");
        }
        return Result.success(pageUtils);
    }
    public Result<Integer> save(Roster roster, BindingResult result){
        List<FieldError> fieldErrorList =  result.getFieldErrors();
        if(!fieldErrorList.isEmpty()){
            return Result.error(fieldErrorList.get(0).getDefaultMessage());
        }
         int save = rosterMapper.save(roster);
        if(save<=0){
            return Result.error(ResultEnum.USER_SAVE_FAIL);
        }
        return Result.success(save);
    }
    public Result<Integer> update(Roster roster){
        final int update = rosterMapper.update(roster);
        if(update<=0){
            return Result.error(ResultEnum.USER_UPDATE_FAIL);
        }
        return Result.success(update);
    }

    public Result<Integer> delete(Integer rid){
        if(rid<=0){
            return Result.error("参数有误");
        }
         int delete = rosterMapper.delete(rid);
        if (delete<=0){
            return Result.error(ResultEnum.USER_DELETE_FAIL);
        }
        return Result.success(delete);
    }

    public Result<PageInfo<RosterStudentVo>> listRosterStudent(Integer pageNum,Integer pageSize,String token){
        Integer userNo = null;
        try {
            userNo = JWTUtils.getUserNo(JWTUtils.verify(token));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        if(userNo==null){
            return Result.error(ResultEnum.TOKEN_ERROR);
        }
        if(userNo<=0){
            return Result.error("参数有误");
        }
         List<RosterStudentVo> rosterStudentVos = rosterMapper.listRosterStudent(userNo);
        if(CollectionUtil.isEmpty(rosterStudentVos)){
            return Result.error("查询失败");
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<RosterStudentVo> rosterStudentVoPageInfo = new PageInfo<>(rosterStudentVos);
        return Result.success(rosterStudentVoPageInfo);
    }
}


