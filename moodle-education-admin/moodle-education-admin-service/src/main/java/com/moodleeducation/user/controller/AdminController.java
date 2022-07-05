package com.moodleeducation.user.controller;

import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;

import com.moodleeducation.commoncore.entity.Admin;
import com.moodleeducation.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/superAdmin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    Result<PageInfo<Admin>> getAllAdmin(@RequestParam(value = "pageNum",defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, @RequestParam(value = "value",required = false) String value){
        return adminService.getAllAdmin(currentPage,pageSize);
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    Result<Integer> save(@RequestBody Admin admin){
        return adminService.save(admin);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    Result<Integer> delete(@PathVariable("id") Integer id){
        return adminService.delete(id);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    Result<Integer> update(@RequestBody Admin admin){
        return adminService.update(admin);
    }


}
