package com.moodle.education.course.service.controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.course.feign.interfaces.IFeignCourse;
import com.moodle.education.course.feign.interfaces.Qo.VideoAuditQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoEsQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoQo;
import com.moodle.education.course.feign.interfaces.Vo.VideoVo;
import com.moodle.education.course.feign.interfaces.entity.Video;
import com.moodle.education.course.service.service.IFeignCourseService;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class IFeignCourseController implements IFeignCourse {
    @Autowired
    private IFeignCourseService courseService;
    @Override
    public PageInfo<VideoVo> listForPage(VideoQo videoQo) {
        return  courseService.listForPage(videoQo);
    }

    @Override
    public Result<Integer> audit(VideoAuditQo videoAuditQo) {
        return courseService.audit(videoAuditQo);
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public int save(Video video) {
        return 0;
    }

    @Override
    public Result<String> uploadVideo(MultipartFile videoFile) {
        return courseService.uploadVideo(videoFile);
    }

    @Override
    public Result<Integer> submit(Video video) {
        return courseService.submit(video);
    }

    @Override
    public Result<Integer> uploadImg(MultipartFile imgFile,Long videoId) {
        return courseService.uploadImg(imgFile,videoId);
    }

    @Override
    public Result<PageUtils<VideoVo>> queryCourse(VideoEsQo videoEsQo) {
        return courseService.searchList(videoEsQo);
    }
}

