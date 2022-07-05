package com.moodleeducation.commoncore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseEnum {

    PASS(1,"审核通过"),
    FAIL(2,"审核未通过"),
    UNCHECK(0,"待审核");
    private Integer code;
    private String desc;

}
