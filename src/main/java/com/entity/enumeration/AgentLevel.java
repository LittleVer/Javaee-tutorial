package com.entity.enumeration;

import org.springframework.util.StringUtils;

public enum AgentLevel {
	VIP("VIP",0,Integer.MAX_VALUE),NORMAL("普通",0,2000),NOCARE("不关注",2000,5000);
	private String name;
	private Integer minPrice;
	private Integer maxPrice;
	AgentLevel(String name,Integer minPrice,Integer maxPrice) {
		this.name = name;
		this.minPrice=minPrice;
		this.maxPrice=maxPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
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