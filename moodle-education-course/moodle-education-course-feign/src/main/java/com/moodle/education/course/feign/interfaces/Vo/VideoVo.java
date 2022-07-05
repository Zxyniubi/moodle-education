package com.moodle.education.course.feign.interfaces.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class VideoVo implements Serializable {
    private final static long serialVersionUUID=1L;
    private Integer videoId;
    private String videoName;
    private String videoUrl;
    private String imgUrl;
    private String gradeName;
    private String subjectName;
    private String teacherName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  createTime;
    private Integer videoType;
    private String remark;
    private Date auditTime;
}
