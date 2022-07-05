package com.moodle.education.user.service.Mapper;

import com.moodle.education.user.service.entity.Roster;
import com.moodle.education.user.service.entity.VO.RosterStudentVo;
import com.moodle.education.user.service.entity.VO.RosterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RosterMapper {
    List<RosterVo> list(@Param("id")Integer id);
    int save(Roster roster);
    int delete(Integer rid);
    int update(Roster roster);

    List<RosterStudentVo> listRosterStudent(@Param("id") Integer id);
}
