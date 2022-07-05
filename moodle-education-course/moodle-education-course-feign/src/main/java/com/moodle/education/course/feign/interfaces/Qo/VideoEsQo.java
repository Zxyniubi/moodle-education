package com.moodle.education.course.feign.interfaces.Qo;

import lombok.Data;

@Data
public class VideoEsQo {
    private Integer pageNum;
    private Integer pageSize;
    private Integer videoName;
    private Integer isHighLightField;
}
