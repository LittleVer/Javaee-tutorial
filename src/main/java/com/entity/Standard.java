package com.entity;

import java.io.Serializable;

public class Standard implements Serializable {
	private static final long serialVersionUID = 8777006716841782687L;
	private Long id;
    private String carId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Override
	public String toString() {
		return "Standard [id=" + id + ", carId=" + carId + "]";
	}
}
