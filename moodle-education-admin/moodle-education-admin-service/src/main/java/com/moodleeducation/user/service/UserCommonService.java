package com.moodleeducation.user.service;

import com.moodle.education.user.feign.interfaces.IFeignStudent;
import com.moodle.education.user.feign.interfaces.IFeignTeacher;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Admin;
import com.moodleeducation.commoncore.entity.Student;
import com.moodleeducation.commoncore.entity.Teacher;
import com.moodleeducation.commoncore.enums.RedisPreEnum;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.commoncore.tools.JWTUtils;
import com.moodleeducation.commoncore.tools.MD5Util;
import com.moodleeducation.user.dao.AdminDao;

import com.moodleeducation.user.entity.bo.LoginBo;
import com.moodleeducation.user.entity.bo.UserBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Component

public class UserCommonService {
    private final static Logger log = LoggerFactory.getLogger(UserCommonService.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AdminService adminService;
    @Autowired
    private IFeignTeacher iFeignTeacher;
    @Autowired
    private IFeignStudent iFeignStudent;

    public Result<String> loginPassword(LoginBo loginBo, BindingResult bindingResult){
        List<FieldError> fieldErrorList =  bindingResult.getFieldErrors();
        if(!fieldErrorList.isEmpty()){
            return Result.error(fieldErrorList.get(0).getDefaultMessage());
        }
        String role = loginBo.getRole();
        UserBo userBo = null;
        switch (role){
            case "admin":
                Admin admin = adminService.getByUserName(loginBo.getUsername());
                userBo  = new UserBo(String.valueOf(admin.getAdminId()),admin.getPassword(),admin.getAdminName());
                stringRedisTemplate.opsForValue().set(admin.getAdminName()+":permission",adminService.getPermissionById(admin.getAdminId()).toString(),1, TimeUnit.DAYS);
                break;
            case "teacher":
                Teacher teacher = iFeignTeacher.getById(loginBo.getUsername());
                userBo = new UserBo(teacher.getTeacherId(),teacher.getPassword(),teacher.getTeacherId());
                break;
            case "student":
                Student student = iFeignStudent.getById(loginBo.getUsername());
                userBo = new UserBo(student.getStudentId(),student.getPassword(),student.getStudentId());
        }
        if(userBo==null){
            return Result.error("账号或密码不正确或账号不存在");
        }
        String password = MD5Util.MD5Utils(loginBo.getPassword(),userBo.getSalt(),2);
        if(!password.equals(userBo.getPassword())){
            return Result.error(999,"账号或密码有误");
        }
        String token = JWTUtils.create(Integer.parseInt(userBo.getUsername()),JWTUtils.DATE);
        stringRedisTemplate.opsForValue().set(RedisPreEnum.ADMINI_MENU.getCode().concat(userBo.getUsername()), token, 1, TimeUnit.DAYS);
        return Result.success(token);
    }
    public Result<String> logout(String username){
         Set<String> keys = stringRedisTemplate.keys(username + ":*");
         Long result;
         if(keys!=null &&!keys.isEmpty()){
             result = stringRedisTemplate.delete(keys);
             if(result!=null){
                 return Result.success(null);
             }
         }
         return Result.error(ResultEnum.TOKEN_PAST);
    }

}
