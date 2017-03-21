package com.vm.dao;

import java.util.List;

import com.entity.Area;

public interface AreaDao {
    public List<Area> find(Area area);
    
    public Area findById(Long id);

    public void add(Area area);

    public void update(Area area);

    public void delete(Long id);
}
