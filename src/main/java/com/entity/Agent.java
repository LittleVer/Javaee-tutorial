package com.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.entity.enumeration.AgentLevel;
import com.google.common.collect.Lists;

public class Agent implements Serializable{
	private static final long serialVersionUID = 4678225051291399613L;
	private Long id;
	private String openid;
    private AgentLevel level;
    private String agentName;
    private List<String> userIds;
    private String userIdsStr;
    private String area;
    
    private volatile String usernames;
	
	public Agent() {
		super();
	}

	public Agent(AgentLevel level, String agentName, String area, String openid) {
		super();
		this.level = level;
		this.agentName = agentName;
		this.area = area;
		this.openid = openid;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
		StringBuffer buff = new StringBuffer();
		for(String id : userIds) {
			buff.append(id).append(',');
		}
		this.userIdsStr = buff.toString();
	}

	public String getUserIdsStr() {
		return userIdsStr;
	}

	public void setUserIdsStr(String userIdsStr) {
		this.userIdsStr = userIdsStr;
		this.userIds = Lists.newArrayList();
		if(!StringUtils.isEmpty(userIdsStr)) {
			String[] ids = userIdsStr.split(",");
			for(String id : ids) {
				this.userIds.add(id);
			}
		}
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", level=" + level + ", agentName="
				+ agentName + ", userIds=" + userIds + ", area=" + area + "]";
	}
}
