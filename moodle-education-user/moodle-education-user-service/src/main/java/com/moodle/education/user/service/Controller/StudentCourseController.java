package com.moodle.education.user.service.Controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.course.feign.interfaces.Qo.VideoEsQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoQo;
import com.moodle.education.course.feign.interfaces.Vo.VideoVo;
import com.moodle.education.user.service.Serivce.CourseService;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/student/course")
public class StudentCourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<PageInfo<VideoVo>> list(VideoQo videoQo){
        return courseService.list(videoQo);
    }
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Result<PageUtils<VideoVo>> query(VideoEsQo videoEsQo){
        return courseService.query(videoEsQo);
    }
}
