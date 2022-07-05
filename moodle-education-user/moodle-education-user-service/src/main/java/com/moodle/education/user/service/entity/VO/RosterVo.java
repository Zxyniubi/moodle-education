package com.moodle.education.user.service.entity.VO;

import lombok.Data;

import java.io.Serializable;
@Data
public class RosterVo implements Serializable {
    private String classesName;
    private String gradeName;
    private String teacherName;
    private String subjectName;
}
