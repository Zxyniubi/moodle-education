package com.moodleeducation.commoncore.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
@Document(indexName = "teacher")
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    //教师编号 （自增长）
    @Id
    private Integer tid;
    //教师号
    @NotNull(message = "教师号不能为空")
    private String teacherId;
    //真实姓名
    @NotNull(message = "教师姓名不能为空")
    private String name;
    //密码
    private String password;
    //性别
    @NotNull(message = "性别不能为空")
    private Integer sex;
    //创建时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //状态 1：正常 2：冻结
    private Integer isAvailable;
    //联系方式
    private String mobile;
    //email
    private String email;
    //头像
    private String picImg;
    //任课科目
    @NotNull(message = "教师科目不能为空")
    private Subject subject;


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", teacherId='" + teacherId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", createTime=" + createTime +
                ", isAvailable=" + isAvailable +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", picImg='" + picImg + '\'' +
                ", subject=" + subject +
                '}';
    }
}
