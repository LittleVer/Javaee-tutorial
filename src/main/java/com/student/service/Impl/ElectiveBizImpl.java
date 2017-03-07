package com.student.service.Impl;

import com.college.dao.SectionDao;
import com.college.dao.TakesDao;
import com.entity.Section;
import com.entity.custom.SectionCustom;
import com.student.service.ElectiveBiz;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-16.
 */
@Service
public class ElectiveBizImpl implements ElectiveBiz {

    @Resource
    private TakesDao takesDao;

    @Resource
    private SectionDao sectionDao;

    public void add(int secId, String stdId) {
        takesDao.add(secId, stdId);
    }

    @Override
    public List<SectionCustom> findAllSection() {
        return sectionDao.findAllCustom();
    }

    @Override
    public void delete(int secId, String stdId) {
        takesDao.delete(secId,stdId);
    }
}
