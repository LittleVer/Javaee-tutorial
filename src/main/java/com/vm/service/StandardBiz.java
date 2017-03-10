package com.vm.service;

import java.util.List;

import com.entity.Standard;

public interface StandardBiz {
	public List<Standard> find(Standard standard);

    public void add(Standard standard);

    public void update(Standard standard);

    public void delete(Long id);

	public Standard findById(Long standard);
}
