package com.entity;

import java.io.Serializable;

public class Area implements Serializable {
	private static final long serialVersionUID = 8852738152430122859L;
	private Long id;
	private String area;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "Area [id=" + id + ", area=" + area + "]";
	}
}
