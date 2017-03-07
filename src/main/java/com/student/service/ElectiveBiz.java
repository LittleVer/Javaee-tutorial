package com.student.service;

import com.entity.Section;
import com.entity.custom.SectionCustom;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-16.
 */
public interface ElectiveBiz {
    public void add(int secId, String stdId);

    public List<SectionCustom> findAllSection();

    public void delete(int secId, String stdId);
}
