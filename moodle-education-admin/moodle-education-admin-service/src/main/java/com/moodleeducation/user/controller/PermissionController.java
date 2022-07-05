package com.moodleeducation.user.controller;

import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.user.entity.dto.PermissionDTO;
import com.moodleeducation.user.entity.dto.SavePermissionDTO;
import com.moodleeducation.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping(value = "/list/{id}",method = RequestMethod.GET)
    public Result<PermissionDTO> list(@PathVariable("id") Integer id){
        return permissionService.getPermissionTreeNodeById(id);
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody SavePermissionDTO permissionDTO){
        return permissionService.save(permissionDTO);
    }
}
