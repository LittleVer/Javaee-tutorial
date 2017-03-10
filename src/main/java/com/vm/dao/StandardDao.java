package com.vm.dao;

import java.util.List;

import com.entity.Standard;

public interface StandardDao {
    public List<Standard> find(Standard standard);
    
    public Standard findById(Long id);

    public void add(Standard standard);

    public void update(Standard standard);

    public void delete(Long id);
}
