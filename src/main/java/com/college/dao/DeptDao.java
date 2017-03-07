package com.college.dao;

import com.entity.Dept;
import com.entity.custom.DeptAndSpec;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-10.
 */
public interface DeptDao {
    public List<Dept> findAll();

    public void add(String deptName);

    public String findIdByName(String deptName);

    public void update(Dept dept);

    public void delete(int deptId);

    public List<String> findAllDeptName();

}
