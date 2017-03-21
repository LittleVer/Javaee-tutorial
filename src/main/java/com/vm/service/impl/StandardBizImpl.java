package com.vm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Standard;
import com.vm.dao.StandardDao;
import com.vm.service.StandardBiz;

@Service
public class StandardBizImpl implements StandardBiz {
    @Resource
    private StandardDao standardDao;

    public List<Standard> find(Standard standard) {
        return standardDao.find(standard);
    }

    @Override
	public Standard findById(Long id) {
		return standardDao.findById(id);
	}

	public void add(Standard standard) {
        standardDao.add(standard);
    }

    public void update(Standard standard) {
        standardDao.update(standard);
    }

    public void delete(Long standardId) {
        standardDao.delete(standardId);
    }
}
