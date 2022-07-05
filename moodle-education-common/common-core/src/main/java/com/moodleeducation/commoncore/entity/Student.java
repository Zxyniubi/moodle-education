package com.moodleeducation.commoncore.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "student",shards = 5)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    //学生编号 （自增长）
    @Id
    @NotNull(message = "sId不能为空")
    private Integer sid;
    //学号（唯一）
    @NotNull(message = "学号不能为空")
    private String studentId;
    //真实姓名
    @NotNull(message = "姓名不能为空")
    private String name;
    //密码;
    @NotNull(message = "密码不能为空")
    private String password;
    //性别;
    @NotNull(message = "性别不能为空")
    private Integer sex;
    //年龄
//    @Pattern(regexp = "^1[34578]\\d{9}$")
    private Integer age;
    //创建时间
    private Date createTime;
    //父母联系方式
//    @Pattern(regexp = "^1[34578]\\d{9}$")
    private String parentMobile;
    //父母email
//    @Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+")
    private String parentEmail;
    //个人联系方式
    private String mobile;
    //个人email
//    @Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+")
    private String email;
    //学生头像
    private String picImg;
    //对应班级表
    private Classes classes;
    //状态 1：正常 2：冻结
    private Integer isAvailable;

    private Integer lockState;


    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", createTime=" + createTime +
                ", parentMobile='" + parentMobile + '\'' +
                ", parentEmail='" + parentEmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", picImg='" + picImg + '\'' +
                ", classes=" + classes +
                ", isAvailable=" + isAvailable +
                ", lockState=" + lockState +
                '}';
    }
}
