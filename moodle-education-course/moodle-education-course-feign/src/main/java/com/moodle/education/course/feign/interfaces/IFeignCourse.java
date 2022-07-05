package com.moodle.education.course.feign.interfaces;

import com.github.pagehelper.PageInfo;
import com.moodle.education.course.feign.interfaces.Config.FeignSupportConfig;
import com.moodle.education.course.feign.interfaces.Qo.VideoAuditQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoEsQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoQo;
import com.moodle.education.course.feign.interfaces.Vo.VideoVo;
import com.moodle.education.course.feign.interfaces.entity.Video;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "moodle-education-course-service",configuration = FeignSupportConfig.class)
public interface IFeignCourse {

    @RequestMapping(value = "/feign/course/list",method = RequestMethod.POST)
    PageInfo<VideoVo> listForPage(@RequestBody VideoQo videoQo);

    @RequestMapping(value = "/feign/admin/course/audit",method = RequestMethod.POST)
    Result<Integer> audit(@RequestBody VideoAuditQo videoAuditQo);

    @RequestMapping(value = "/feign/teacher/course/delete/{id}",method = RequestMethod.DELETE)
    int delete(@PathVariable("id") Integer id);

    @RequestMapping(value = "/feign/teacher/course/save",method = RequestMethod.POST)
    int save(@RequestBody Video video);

    @RequestMapping(value = "/feign/teacher/course/uploadVideo",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    Result<String> uploadVideo(@RequestPart(value = "videoFile") MultipartFile videoFile);

    @RequestMapping(value = "/feign/teacher/course/submit",method = RequestMethod.POST)
    Result<Integer> submit(@RequestBody Video video);

    @RequestMapping(value = "/feign/teacher/course/uploadImg",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<Integer> uploadImg(@RequestPart(value = "imgFile") MultipartFile imgFile,@RequestParam(value = "videoId")Long videoId);

    @RequestMapping(value = "/feign/course/query",method = RequestMethod.POST)
    Result<PageUtils<VideoVo>> queryCourse(@RequestBody VideoEsQo videoEsQo);
    }
