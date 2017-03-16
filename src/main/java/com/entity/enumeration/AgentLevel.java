package com.entity.enumeration;

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
	
	public static void main(String[] args) {
		for(AgentLevel entry : AgentLevel.values()) {
			System.out.println(entry.getName()+":"+entry.name());
		}
	}
}