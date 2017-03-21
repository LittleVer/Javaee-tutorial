package com.vm.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Agent;
import com.entity.User;
import com.system.dao.UserDao;
import com.vm.dao.AgentDao;
import com.vm.service.AgentBiz;

@Service
public class AgentBizImpl implements AgentBiz {
    @Autowired
    private AgentDao AgentDao;
    @Autowired
    private UserDao userDao;

    public List<Agent> find(Agent agent) {
    	List<Agent> list = AgentDao.find(agent);
    	for(Agent entry : list) {
    		if(StringUtils.isEmpty(entry.getUserIdsStr())) continue;
    		StringBuffer buff = new StringBuffer();
    		for(String uid : entry.getUserIds()) {
    			User user = userDao.findById(uid);
    			buff.append(user.getUsername()).append(',');
    		}
    		if(buff.length()!=0) buff.deleteCharAt(buff.length()-1);
    		entry.setUsernames(buff.toString());
    	}
        return list;
    }

    @Override
	public Agent findById(Long id) {
		return AgentDao.findById(id);
	}

	@Override
	public Agent findByOpenId(String id) {
		return AgentDao.findByOpenId(id);
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
