package com.moodleeducation.commoncore.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Classes implements Serializable {
    private static final long serialVersionUID = 1L;

    //班级编号（自增长）
    private Integer classesId;
    //年级
    private Grade grade;
    //几班
    private Integer classes;

    private Major major;





    @Override
    public String toString() {
        return "Classes{" +
                "classesId=" + classesId +
                ", grade=" + grade +
                ", classes=" + classes +
                '}';
    }
}

