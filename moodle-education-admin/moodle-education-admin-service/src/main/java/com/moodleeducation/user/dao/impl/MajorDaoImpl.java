package com.moodleeducation.user.dao.impl;

import com.github.pagehelper.Page;
import com.moodleeducation.commoncore.entity.Major;
import com.moodleeducation.user.Mapper.MajorMapper;
import com.moodleeducation.user.dao.MajorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MajorDaoImpl implements MajorDao {
    @Autowired
    private MajorMapper majorMapper;
    @Override
    public Page<Major> listForPage() {
        return majorMapper.listForPage();
    }

    @Override
    public Major getById(Integer id) {
        return majorMapper.getById(id);
    }

    @Override
    public int delete(Integer id) {
        return majorMapper.delete(id);
    }

    @Override
    public int save(Major major) {
        return majorMapper.save(major);
    }

    @Override
    public int update(Major major) {
        return majorMapper.update(major);
    }
}
