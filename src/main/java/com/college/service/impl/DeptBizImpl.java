package com.college.service.impl;

import com.college.dao.DeptDao;
import com.college.service.DeptBiz;
import com.entity.Dept;
import com.entity.custom.DeptAndSpec;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-11.
 */

@Service
public class DeptBizImpl implements DeptBiz {
    @Resource
    private DeptDao deptDao;

    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    public void add(String deptName) {
        deptDao.add(deptName);
    }

    public void update(Dept dept) {
        deptDao.update(dept);
    }

    public void delete(int deptId) {
        deptDao.delete(deptId);
    }

}
