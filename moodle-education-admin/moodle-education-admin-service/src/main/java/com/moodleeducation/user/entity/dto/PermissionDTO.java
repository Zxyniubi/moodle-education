package com.moodleeducation.user.entity.dto;

import com.moodleeducation.user.entity.TreeNode;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class PermissionDTO implements Serializable {
    private final static long serialVersionUID =1L;
    public List<TreeNode> permissionTreeNodeList =new ArrayList<>();

}
