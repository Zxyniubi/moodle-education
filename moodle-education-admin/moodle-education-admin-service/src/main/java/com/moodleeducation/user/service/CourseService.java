package com.moodleeducation.user.service;

import com.github.pagehelper.PageInfo;
import com.moodle.education.course.feign.interfaces.IFeignCourse;
import com.moodle.education.course.feign.interfaces.Qo.VideoAuditQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoQo;
import com.moodle.education.course.feign.interfaces.Vo.VideoVo;
import com.moodleeducation.commoncore.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private IFeignCourse feignCourse;

    public Result<PageInfo<VideoVo>> list(VideoQo videoQo){
        return Result.success(feignCourse.listForPage(videoQo));
    }
    public Result<Integer> audit(VideoAuditQo videoAuditQo){
        return feignCourse.audit(videoAuditQo);
    }
}
