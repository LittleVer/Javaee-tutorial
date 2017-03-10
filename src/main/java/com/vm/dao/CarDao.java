package com.vm.dao;

import java.util.List;

import com.entity.Car;

public interface CarDao {
    public List<Car> find(Car car);
    
    public Car findById(Long id);

    public void add(Car car);

    public void update(Car car);

    public void delete(Long id);

}
