package com.moodle.education.user.feign.interfaces;

import com.github.pagehelper.PageInfo;
import com.moodle.education.user.feign.interfaces.Bo.StudentSearchBo;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "moodle-education-user-service")
public interface IFeignStudent {

    @RequestMapping(value = "/feign/user/student/getAll/{pageNum}/{pageSize}",method = RequestMethod.GET)
    PageInfo<Student>getAllStudent(@PathVariable(value = "pageNum") Integer pageNum,@PathVariable(value = "pageSize")Integer pageSize);

    @RequestMapping(value = "/feign/user/student/delete/{id}",method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id")Integer id);

    @RequestMapping(value = "/feign/user/student/update",method = RequestMethod.PUT)
    int updateById(@RequestBody Student student);

    @RequestMapping(value = "/feign/user/student/save",method = RequestMethod.POST)
    int save(@RequestBody Student student);

    @RequestMapping(value = "/feign/user/student/{id}",method = RequestMethod.GET)
    Student getById(@PathVariable(value = "id") String id);
    @RequestMapping(value = "/feign/user/student/query",method = RequestMethod.POST)
    Result<PageUtils<Student>> query(@RequestBody StudentSearchBo studentSearchBo);

}
