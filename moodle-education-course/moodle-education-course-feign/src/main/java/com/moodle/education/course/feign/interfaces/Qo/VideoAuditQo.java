package com.moodle.education.course.feign.interfaces.Qo;

import lombok.Data;

import java.util.Date;

@Data
public class VideoAuditQo {
    private Integer videoId;
    private Integer videoType;
    private String remark;
    private Date auditTime;
}
