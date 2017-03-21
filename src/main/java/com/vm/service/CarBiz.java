package com.vm.service;

import java.util.List;

import com.entity.Car;
import com.entity.enumeration.AgentLevel;

public interface CarBiz {
	public List<Car> find(Car car);

    public void add(Car car);

    public void update(Car car);

    public void delete(Long id);

	public Car findById(Long id);

	public List<Car> findByLevel(AgentLevel level);
}
