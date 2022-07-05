package com.moodleeducation.user.controller;

import com.github.pagehelper.PageInfo;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Classes;
import com.moodleeducation.user.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<PageInfo<Classes>> manageClasses(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        return classesService.getAllClasses(pageNum,pageSize);
    }

    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
    public Result<Classes>getById(@PathVariable("id") Integer id){
        return classesService.getById(id);
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public Result<Integer> delete(@PathVariable("id") Integer id){
        return classesService.delete(id);
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody Classes classes){
        return classesService.save(classes);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody Classes classes){
        return classesService.update(classes);
    }


}
