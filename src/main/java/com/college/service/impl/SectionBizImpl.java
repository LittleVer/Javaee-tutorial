package com.college.service.impl;

import com.college.dao.CourseDao;
import com.college.dao.SectionDao;
import com.college.dao.StaffDao;
import com.college.dao.TimetableDao;
import com.college.service.SectionBiz;
import com.entity.Section;
import com.entity.Staff;
import com.entity.Timetable;
import com.entity.custom.SectionCustom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-12.
 */
@Service
public class SectionBizImpl implements SectionBiz {

    @Resource
    private SectionDao sectionDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private StaffDao staffDao;

    @Resource
    private TimetableDao timetableDao;

    @Transactional
    @Override
    public void delete(int secId) {
        sectionDao.delete(secId);
        timetableDao.delete(secId);
    }

    @Override
    public void add(Section section) {
        sectionDao.add(section);

    }

    @Override
    public List<SectionCustom> findAllCustom() {
        return sectionDao.findAllCustom();
    }

    @Override
    public List<String> findAllCourseTitle() {
        return courseDao.findAllCourseTitle();
    }


    @Override
    public List<Staff> findAllStaff() {
        return staffDao.findAll();
    }

    @Override
    public void addTimetable(Timetable timetable) {
        timetableDao.add(timetable);
    }


}
