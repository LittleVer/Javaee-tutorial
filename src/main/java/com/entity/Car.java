package com.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class Car implements Serializable{
	private static final long serialVersionUID = 6534271072663770032L;
	private Long id;
	@NotEmpty
    private Boolean isSale;
	@NotEmpty
    private String carId;
	@NotEmpty
    private Integer high;
	@NotEmpty
    private Integer length;
	@NotEmpty
    private Integer wide;
	@NotEmpty
    private Integer weight;
	
	public Car() {
		super();
	}
	public Car(String carId,Boolean isSale, Integer high,
			Integer length, Integer wide, Integer weight) {
		super();
		this.isSale = isSale;
		this.carId = carId;
		this.high = high;
		this.length = length;
		this.wide = wide;
		this.weight = weight;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getIsSale() {
		return isSale;
	}
	public void setIsSale(Boolean isSale) {
		this.isSale = isSale;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public Integer getHigh() {
		return high;
	}
	public void setHigh(Integer high) {
		this.high = high;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getWide() {
		return wide;
	}
	public void setWide(Integer wide) {
		this.wide = wide;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", isSale=" + isSale + ", carId=" + carId
				+ ", high=" + high + ", length=" + length + ", wide=" + wide
				+ ", weight=" + weight + "]";
	}
}
