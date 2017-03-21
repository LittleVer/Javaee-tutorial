package com.vm.service;

import java.util.List;

import com.entity.Car;
import com.entity.Procurement;

public interface ProcurementBiz {
	public List<Procurement> find(Procurement procurement);

    public void add(Procurement procurement);

    public void update(Procurement procurement);

    public void delete(Long id);

	public Procurement findById(Long id);


}
