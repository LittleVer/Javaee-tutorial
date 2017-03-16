package com.entity.enumeration;

import org.springframework.util.StringUtils;

public enum AgentLevel {
	VIP("VIP"),NORMAL("普通"),POTENTIAL("潜在"),NOCARE("不关注");
	private String name;
	AgentLevel(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static AgentLevel parse(String text) {
		if(!StringUtils.isEmpty(text)) {
			for(AgentLevel entry : AgentLevel.values()) {
				if(entry.getName().equals(text)) 
					return entry;
			}
		}
		return null;
	}
}