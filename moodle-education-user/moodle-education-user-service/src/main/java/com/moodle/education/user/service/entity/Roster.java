package com.moodle.education.user.service.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Roster implements Serializable {
    private Integer rid;
    private Integer tid;
    private Integer classesId;
    private Integer subjectId;
}
