package com.moodle.education.user.service.Controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.course.feign.interfaces.Qo.VideoEsQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoQo;
import com.moodle.education.course.feign.interfaces.Vo.VideoVo;
import com.moodle.education.course.feign.interfaces.entity.Video;
import com.moodle.education.user.service.Serivce.CourseService;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import org.elasticsearch.index.engine.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user/teacher/course")
public class CourseController {
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
    @RequestMapping(value = "/uploadVideo",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> uploadVideo(@RequestParam(value = "videoFile", required = false) MultipartFile videoFile) {
        return courseService.uploadVideo(videoFile);
    }

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<Integer> uploadImg(@RequestParam(value = "imgFile") MultipartFile imgFile,@RequestParam(value = "videoId") Long videoId){
        return courseService.uploadImg(imgFile,videoId);
    }
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result<Integer> submit(@RequestBody Video video,@RequestHeader(value = "Token")String token){
        return courseService.submit(video,token);
    }
}
