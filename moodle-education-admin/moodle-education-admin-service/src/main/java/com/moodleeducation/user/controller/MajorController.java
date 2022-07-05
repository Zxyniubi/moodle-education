package com.moodleeducation.user.controller;

import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Major;
import com.moodleeducation.user.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/major")
public class MajorController {
    @Autowired

    private MajorService majorService;
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public Result<PageInfo<Major>> listForPage(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        return majorService.listForPage(pageNum,pageSize);
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody Major major){
        return majorService.save(major);
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Result<Integer>delete(@PathVariable("id") Integer id){
        return majorService.delete(id);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody Major major){
        return majorService.update(major);
    }
    @RequestMapping(value = "/getById/{id}" ,method = RequestMethod.GET)
    public Result<Major> getById(@PathVariable("id") Integer id){
        return majorService.getById(id);
    }
}
