package com.moodle.education.course.feign.interfaces.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data

public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    //视频编号
    private Long videoId;
    //视频名称
    private String videoName;
    //视频路径
    private String videoUrl;
    //封面路径
    private String imgUrl;
    //上传教师
    private Integer teacherId;
    //科目
    private Integer subjectId;
    //年级
    private Integer gradeId;
    //创建时间
    private Date createTime;
    //视频状态：审核状态，删除状态
    private Integer videoType;
    private Date auditTime;
    private String remark;

}
