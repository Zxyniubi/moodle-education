package com.moodleeducation.commoncore.entity;



import com.moodleeducation.commoncore.entity.Permission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    //管理员编号（自增长）

    private Integer adminId;
    //管理员用户名
    private String adminName;
    //真实姓名
    private String name;
    //密码
    private String password;
    //联系方式
    private String phone;
    //状态 1：正常 2：冻结
    private Integer isAvailable;
    //权限
    private List<Permission> permissionList;



    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", isAvailable=" + isAvailable +
                ", permissionList=" + permissionList +
                '}';
    }
}
