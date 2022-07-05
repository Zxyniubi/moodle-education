package com.moodleeducation.commoncore.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    //科目编号（自增长）
    private Integer subjectId;
    //科目名
    private String subjectName;

    private Major major;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
