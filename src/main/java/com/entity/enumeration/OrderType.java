package com.entity.enumeration;

import org.springframework.util.StringUtils;


public enum OrderType {
	DISTRIBUTION("分销订单"),DISTRIBUTION_REJECTED("分销退货订单"),DISTRIBUTION_EXCHANGE("分销换货订单");
	
	private String name;
	OrderType(String name) {
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