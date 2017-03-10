package com.vm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Car;
import com.vm.dao.CarDao;
import com.vm.service.CarBiz;

@Service
public class CarBizImpl implements CarBiz {
    @Resource
    private CarDao carDao;

    public List<Car> find(Car car) {
        return carDao.find(car);
    }

    @Override
	public Car findById(Long id) {
		return carDao.findById(id);
	}

	public void add(Car car) {
        carDao.add(car);
    }

    public void update(Car car) {
        carDao.update(car);
    }

    public void delete(Long carId) {
        carDao.delete(carId);
    }

}
