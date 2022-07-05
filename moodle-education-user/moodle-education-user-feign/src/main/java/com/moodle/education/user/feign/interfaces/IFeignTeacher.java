package com.moodle.education.user.feign.interfaces;

import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.entity.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "moodle-education-user-service")
public interface IFeignTeacher {

    @RequestMapping(value = "/feign/user/teacher/getAll/{pageNum}/{pageSize}",method = RequestMethod.GET)
    PageInfo<Teacher> getAllTeacher(@PathVariable(value = "pageNum")Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize);

    @RequestMapping(value = "/feign/user/teacher/delete/{id}",method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") int id);

    @RequestMapping(value = "/feign/user/teacher/update",method = RequestMethod.PUT)
    int updateById(@RequestBody Teacher teacher);

    @RequestMapping(value = "/feign/user/teacher/save",method  = RequestMethod.POST)
    int save(@RequestBody Teacher teacher);
    @RequestMapping(value = "/feign/user/teacher/{id}",method = RequestMethod.GET)
    Teacher getById(@PathVariable(value = "id") String id);

}
