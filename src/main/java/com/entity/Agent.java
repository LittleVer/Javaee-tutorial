package com.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;

import com.entity.enumeration.AgentLevel;
import com.google.common.collect.Lists;

public class Agent implements Serializable{
	private static final long serialVersionUID = 4678225051291399613L;
	private Long id;
    private AgentLevel level;
    private String agentName;
    private List<Long> clerkIds;
    private String clerkIdsStr;
    private String area;
	
	public Agent() {
		super();
	}

	public Agent(AgentLevel level, String agentName, String clerkIdsStr,
			String area) {
		super();
		this.level = level;
		this.agentName = agentName;
		this.setClerkIdsStr(clerkIdsStr);
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AgentLevel getLevel() {
		return level;
	}

	public void setLevel(AgentLevel level) {
		this.level = level;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public List<Long> getClerkIds() {
		return clerkIds;
	}

	public void setClerkIds(List<Long> clerkIds) {
		this.clerkIds = clerkIds;
		StringBuffer buff = new StringBuffer();
		for(Long id : clerkIds) {
			buff.append(id).append(',');
		}
		this.clerkIdsStr = buff.toString();
	}

	public String getClerkIdsStr() {
		return clerkIdsStr;
	}

	public void setClerkIdsStr(String clerkIdsStr) {
		this.clerkIdsStr = clerkIdsStr;
		if(!StringUtils.isEmpty(clerkIdsStr)) {
			this.clerkIds = Lists.newArrayList();
			String[] ids = clerkIdsStr.split(",");
			for(String id : ids) {
				this.clerkIds.add(Long.parseLong(id));
			}
		}
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", level=" + level + ", agentName="
				+ agentName + ", clerdIds=" + clerkIds + ", area=" + area + "]";
	}
}
