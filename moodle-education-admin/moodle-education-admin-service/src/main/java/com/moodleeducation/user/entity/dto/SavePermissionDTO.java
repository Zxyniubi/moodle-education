package com.moodleeducation.user.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class SavePermissionDTO implements Serializable {
    private final static long serialVersionUUID = 1L;
    private Integer adminId;
    private List<Integer> permissionList;
}
