package com.moodle.education.course.feign.interfaces.Qo;

import lombok.Data;

import java.util.Date;
@Data
public class VideoQo {
    private Integer pageNum;
    private Integer pageSize;
    //视频编号
    private Integer videoId;
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

}
