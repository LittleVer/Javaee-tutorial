package com.vm.service;

import java.util.List;

import com.entity.Area;

public interface AreaBiz {
	public List<Area> find(Area area);

    public void add(Area area);

    public void update(Area area);

    public void delete(Long id);

	public Area findById(Long id);
}
