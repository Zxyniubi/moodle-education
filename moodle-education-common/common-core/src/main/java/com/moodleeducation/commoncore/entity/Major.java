package com.moodleeducation.commoncore.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Major implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer majorId;
    private  String majorName;

    @Override
    public String toString() {
        return "Major{" +
                "majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
