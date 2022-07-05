package com.moodleeducation.commoncore.tools;

import java.io.IOException;
import java.util.Properties;

public class SystemUtils {

    private SystemUtils() {
    }

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperty(String keyName) {
        return properties.getProperty(keyName, "").trim();
    }

    public static final String VIDEO_STORAGE_PATH = getProperty("video_storage_path");
    public static final String PIC_STORAGE_PATH = getProperty("pic_storage_path");
}
