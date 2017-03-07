package com.college.service;

import com.entity.Section;
import com.entity.Staff;
import com.entity.Timetable;
import com.entity.custom.SectionCustom;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-12.
 */
public interface SectionBiz {
    public void delete(int sectionId);

    public void add(Section section);

    public List<SectionCustom> findAllCustom();

    public List<String> findAllCourseTitle();

    public List<Staff> findAllStaff();

    public void addTimetable(Timetable timetable);
}
