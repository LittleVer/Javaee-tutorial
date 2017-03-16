package com.vm.service;

import java.util.List;

import com.entity.Agent;

public interface AgentBiz {
	public List<Agent> find(Agent agent);

    public void add(Agent agent);

    public void update(Agent agent);

    public void delete(Long id);

	public Agent findById(Long id);


}
