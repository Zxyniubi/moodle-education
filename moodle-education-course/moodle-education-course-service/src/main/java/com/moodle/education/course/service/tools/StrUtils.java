package com.moodle.education.course.service.tools;

import cn.hutool.core.util.IdUtil;

public final class StrUtils {

    private StrUtils() {
    }

    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getPrefix(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }



    public static String get32UUID() {
        return IdUtil.simpleUUID();
    }
}
