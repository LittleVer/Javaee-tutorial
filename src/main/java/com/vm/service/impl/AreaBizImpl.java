package com.vm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Area;
import com.vm.dao.AreaDao;
import com.vm.service.AreaBiz;

@Service
public class AreaBizImpl implements AreaBiz {
    @Resource
    private AreaDao areaDao;

    public List<Area> find(Area area) {
        return areaDao.find(area);
    }

    @Override
	public Area findById(Long id) {
		return areaDao.findById(id);
	}

	public void add(Area area) {
        areaDao.add(area);
    }

    public void update(Area area) {
        areaDao.update(area);
    }

    public void delete(Long areaId) {
        areaDao.delete(areaId);
    }
}
