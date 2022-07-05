package com.moodleeducation.user.controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.course.feign.interfaces.Qo.VideoAuditQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoQo;
import com.moodle.education.course.feign.interfaces.Vo.VideoVo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.user.service.CourseService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/course")
public class CourseController {

    @Autowired
    private CourseService service;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<PageInfo<VideoVo>> list(@RequestBody VideoQo videoQo){
        return service.list(videoQo);
    }
    @RequestMapping(value = "/audit",method = RequestMethod.POST)
    public Result<Integer> audit(@RequestBody VideoAuditQo videoAuditQo){
        return service.audit(videoAuditQo);
    }
}
