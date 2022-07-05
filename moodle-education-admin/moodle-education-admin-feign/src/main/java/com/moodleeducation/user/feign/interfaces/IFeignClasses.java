package com.moodleeducation.user.feign.interfaces;

import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Classes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("moodle-education-admin-service")
public interface IFeignClasses {

    @RequestMapping(value = "/feign/user/listForPage/{pageNum}/{pageSize}",method = RequestMethod.GET)
    Result<PageInfo<Classes>> getAllClasses(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize);
}
