package com.moodle.education.user.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.moodleeducation.user.feign.*","com.moodle.education.course.feign.*"})
public class MoodleEducationUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoodleEducationUserServiceApplication.class, args);
    }

}
