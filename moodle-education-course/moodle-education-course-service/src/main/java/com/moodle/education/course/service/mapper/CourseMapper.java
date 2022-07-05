package com.moodle.education.course.service.mapper;

import com.moodle.education.course.feign.interfaces.Qo.VideoAuditQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoQo;
import com.moodle.education.course.feign.interfaces.Vo.VideoVo;
import com.moodle.education.course.feign.interfaces.entity.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CourseMapper {
    List<VideoVo> listForPage(VideoQo videoQo);
    int audit(VideoAuditQo videoAuditQo);
    Video getById(Integer videoId);
    int uploadVideo(Video video);

    int update(Video video);
}
