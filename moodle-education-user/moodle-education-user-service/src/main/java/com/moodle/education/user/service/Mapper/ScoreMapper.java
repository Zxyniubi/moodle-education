package com.moodle.education.user.service.Mapper;

import com.moodle.education.user.service.entity.Score;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreMapper {
    int save(Score score);
}
