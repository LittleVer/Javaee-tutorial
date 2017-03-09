package com.system.model;

public class Breadcrumb {
	private String name;
	private String view;
	public Breadcrumb() {
		super();
	}
	public Breadcrumb(String name, String view) {
		super();
		this.name = name;
		this.view = view;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}
