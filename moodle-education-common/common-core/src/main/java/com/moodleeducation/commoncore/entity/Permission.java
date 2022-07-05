package com.moodleeducation.commoncore.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;
    //权限编号（自增长）
    private Integer permissionId;
    //权限名称
    private String permissionName;
    //权限字符串
    private String permission;
    //父编号
    private Integer parentId;
    //资源类型;;
    private Integer type;
    //资源路径
    private String url;



    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
