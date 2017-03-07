package com.college.service;

import com.entity.Dept;
import com.entity.custom.DeptAndSpec;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-11.
 */
public interface DeptBiz {
    public List<Dept> findAll();

    public void add(String deptName);

    public void update(Dept dept);

    public void delete(int deptId);

}
