package com.moodle.education.user.service.Controller;

import com.github.pagehelper.PageInfo;
import com.moodle.education.user.service.Serivce.RosterService;
import com.moodle.education.user.service.entity.Roster;
import com.moodle.education.user.service.entity.VO.RosterStudentVo;
import com.moodle.education.user.service.entity.VO.RosterVo;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/teacher/roster")
public class RosterController {
    @Autowired
    private RosterService rosterService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<PageUtils<RosterVo>> list(@RequestHeader("Token") String token){
        return rosterService.list(token);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody Roster roster){
        return rosterService.update(roster);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody Roster roster,BindingResult bindingResult){
        return rosterService.save(roster,bindingResult);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public Result<Integer> delete(@PathVariable("id") Integer id){
        return rosterService.delete(id);
    }

    @RequestMapping(value = "/listRosterStudent/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public Result<PageInfo<RosterStudentVo>> listRosterStudent(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestHeader("Token")String token){
        return rosterService.listRosterStudent(pageNum,pageSize,token);
    }
}


