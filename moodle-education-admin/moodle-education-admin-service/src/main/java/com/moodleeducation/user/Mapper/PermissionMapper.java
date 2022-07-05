package com.moodleeducation.user.Mapper;

import com.moodleeducation.commoncore.entity.Permission;
import com.moodleeducation.user.entity.dto.PermissionDTO;
import com.moodleeducation.user.entity.dto.SavePermissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {
     List<Permission> getPermissionById(@Param("id") Integer userId);
     int save(SavePermissionDTO savePermissionDTO);
     int delete(@Param("id") Integer adminId);
}
