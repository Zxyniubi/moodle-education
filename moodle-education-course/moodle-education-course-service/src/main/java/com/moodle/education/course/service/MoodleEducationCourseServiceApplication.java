package com.moodle.education.course.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class MoodleEducationCourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoodleEducationCourseServiceApplication.class, args);
    }

}
