package com.moodleeducation.user.Feign;

import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Classes;
import com.moodleeducation.user.feign.interfaces.IFeignClasses;
import com.moodleeducation.user.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IFeignClassesController implements IFeignClasses {

    @Autowired
    private ClassesService classesService;
    @Override
    public Result<PageInfo<Classes>> getAllClasses(Integer pageNum, Integer pageSize) {
        return classesService.getAllClasses(pageNum,pageSize);
    }
}
