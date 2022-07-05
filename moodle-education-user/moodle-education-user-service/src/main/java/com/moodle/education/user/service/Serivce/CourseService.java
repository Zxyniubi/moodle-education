package com.moodle.education.user.service.Serivce;

import com.moodle.education.course.feign.interfaces.IFeignCourse;
import com.moodle.education.course.feign.interfaces.entity.Video;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.tools.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

@Service
public class CourseService {
    @Autowired
    private IFeignCourse iFeignCourse;

    public Result<String> uploadVideo(MultipartFile file){
        return iFeignCourse.uploadVideo(file);
    }
    public Result<Integer> uploadImg(MultipartFile file,Long videoId){
        return iFeignCourse.uploadImg(file,videoId);
    }
    public Result<Integer> submit(Video video,String token){
        try {
            Integer userNo = JWTUtils.getUserNo(JWTUtils.verify(token));
            video.setTeacherId(userNo);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        return iFeignCourse.submit(video);
    }
}
