package com.vm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Vendor;
import com.vm.dao.VendorDao;
import com.vm.service.VendorBiz;

@Service
public class VendorBizImpl implements VendorBiz {
    @Resource
    private VendorDao vendorDao;

    public List<Vendor> find(Vendor vendor) {
        return vendorDao.find(vendor);
    }

    @Override
	public Vendor findById(Long id) {
		return vendorDao.findById(id);
	}

	public void add(Vendor vendor) {
        vendorDao.add(vendor);
    }

    public void update(Vendor vendor) {
        vendorDao.update(vendor);
    }

    public void delete(Long vendorId) {
        vendorDao.delete(vendorId);
    }
}
