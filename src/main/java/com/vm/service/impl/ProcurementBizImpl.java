package com.vm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Procurement;
import com.vm.dao.ProcurementDao;
import com.vm.service.ProcurementBiz;

@Service
public class ProcurementBizImpl implements ProcurementBiz {
    @Resource
    private ProcurementDao procDao;

    public List<Procurement> find(Procurement procurement) {
        return procDao.find(procurement);
    }

    @Override
	public Procurement findById(Long id) {
		return procDao.findById(id);
	}

	public void add(Procurement procurement) {
		procDao.add(procurement);
    }

    public void update(Procurement procurement) {
    	procDao.update(procurement);
    }

    public void delete(Long carId) {
    	procDao.delete(carId);
    }

}
