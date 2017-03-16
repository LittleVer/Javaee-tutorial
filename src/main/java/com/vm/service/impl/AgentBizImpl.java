package com.vm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Agent;
import com.vm.dao.AgentDao;
import com.vm.service.AgentBiz;

@Service
public class AgentBizImpl implements AgentBiz {
    @Resource
    private AgentDao AgentDao;

    public List<Agent> find(Agent agent) {
        return AgentDao.find(agent);
    }

    @Override
	public Agent findById(Long id) {
		return AgentDao.findById(id);
	}

	public void add(Agent agent) {
        AgentDao.add(agent);
    }

    public void update(Agent agent) {
        AgentDao.update(agent);
    }

    public void delete(Long AgentId) {
        AgentDao.delete(AgentId);
    }

}
