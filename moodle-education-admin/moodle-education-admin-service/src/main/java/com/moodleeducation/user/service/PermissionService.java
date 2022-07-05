package com.moodleeducation.user.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.moodleeducation.commoncore.base.BaseException;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Permission;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.user.Mapper.PermissionMapper;
import com.moodleeducation.user.entity.TreeNode;
import com.moodleeducation.user.entity.dto.PermissionDTO;
import com.moodleeducation.user.entity.dto.SavePermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 获取管理员权限，生成TreeNode列表返回前端。
     * @param adminId
     * @return
     */
    public Result<PermissionDTO> getPermissionTreeNodeById(Integer adminId){

         List<Permission> permissionById = permissionMapper.getPermissionById(adminId);
         if(CollectionUtil.isEmpty(permissionById)){
             return Result.error("查询不到信息");
         }
         List<TreeNode> parentNodeList = new ArrayList<>();
         for(Permission parentPermission : permissionById){
             //父节点
            if (parentPermission.getParentId()==null){
                TreeNode parent = new TreeNode(parentPermission.getPermissionId(),parentPermission.getPermissionName(),null);
                Map<String,Boolean> parentMap = new HashMap<>();
                //设置选中
                parentMap.put("checked",true);
                parent.setState(parentMap);
                List<TreeNode> sonNodeList = new ArrayList<>();
                for(Permission son : permissionById){
                    //选取子节点
                    if( son.getParentId() !=null && !son.getPermissionId().equals(parentPermission.getPermissionId()) && son.getParentId().equals(parentPermission.getPermissionId())){
                        TreeNode sonNode = new TreeNode(son.getPermissionId(), son.getPermissionName(), null);
                        Map<String,Boolean> sonMap = new HashMap<>();
                        sonMap.put("checked",true);
                        sonNode.setState(sonMap);
                        sonNodeList.add(sonNode);
                    }
                }
                //添加到父节点中的子节点列表
                parent.setNodes(sonNodeList);
                //添加到父节点列表
                parentNodeList.add(parent);
            }
         }
            if (CollectionUtil.isNotEmpty(parentNodeList)){
                PermissionDTO permissionDTO = new PermissionDTO();
                permissionDTO.setPermissionTreeNodeList(parentNodeList);
                return Result.success(permissionDTO);
            }
            return Result.error("找不到信息");
    }

    public Result<Integer> save(SavePermissionDTO permissionDTO){
        if(ObjectUtil.isEmpty(permissionDTO)){
            return Result.error("入参有误");
        }
        permissionMapper.delete(permissionDTO.getAdminId());
         int save = permissionMapper.save(permissionDTO);
        if(save<=0){
            return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
        }
        return Result.success(save);
    }
}
