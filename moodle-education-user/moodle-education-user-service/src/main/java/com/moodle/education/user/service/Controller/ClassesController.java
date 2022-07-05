package com.moodle.education.user.service.Controller;

import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Classes;
import com.moodleeducation.user.feign.interfaces.IFeignClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ClassesController {

    @Autowired
    private IFeignClasses classes;

    @GetMapping("/testFeign")
    Result<PageInfo<Classes>> test(@RequestParam(value = "pageNum") Integer pageNum,@RequestParam(value = "pageSize") Integer pageSize){
        return classes.getAllClasses(pageNum,pageSize);
    }
}

