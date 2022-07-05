package com.moodle.education.user.feign.interfaces.Bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentSearchBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private Integer isHighLight;
    private Integer pageCurrent =1;

    private Integer pageSize=20 ;

}
