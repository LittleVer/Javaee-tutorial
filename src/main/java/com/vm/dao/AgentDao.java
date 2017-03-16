package com.vm.dao;

import java.util.List;

import com.entity.Agent;

public interface AgentDao {
    public List<Agent> find(Agent agent);
    
    public Agent findById(Long id);

    public void add(Agent Agent);

    public void update(Agent agent);

    public void delete(Long id);

}
