package com.moodle.education.user.service.entity.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class RosterStudentVo implements Serializable {
    private final static long serialVersionUUID=1L;
    private Integer sid;
    private Integer studentId;
    private String  name;
    private String  grade;
    private String  classes;
    private String  sex;
    private String mobile;
    private String email;
    private String parentMobile;
}
