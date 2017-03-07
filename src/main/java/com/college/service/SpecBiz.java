package com.college.service;

import com.entity.Spec;
import com.entity.custom.DeptAndSpec;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-11.
 */
public interface SpecBiz {

    public void update(String specName, String newSpecName);

    public void add(Spec spec);

    public void delete(String specName);

    public List<DeptAndSpec> findDeptAndSpec();

    public List<String> findDpet();
}
