package com.moodleeducation.commoncore.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;

    //年级编号（自增长）
    private Integer gradeId;
    //几年级
    private String gradeName;


    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
