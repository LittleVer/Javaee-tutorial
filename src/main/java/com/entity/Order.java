package com.entity;

import java.io.Serializable;
import java.util.Date;

import com.entity.enumeration.OrderType;
import com.entity.enumeration.Status;

public class Order implements Serializable{
	private static final long serialVersionUID = -2545303099330416597L;
	private Long id;
	private String openid;
	private String carId;
	private Integer carCnt;
	private Double price;
	private Status status;
	private OrderType orderType;
	private Date startTime;
	private Date deliverTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public Integer getCarCnt() {
		return carCnt;
	}
	public void setCarCnt(Integer carCnt) {
		this.carCnt = carCnt;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", openid=" + openid + ", carId=" + carId
				+ ", carCnt=" + carCnt + ", price=" + price + ", status="
				+ status + ", orderType=" + orderType + ", startTime="
				+ startTime + ", deliverTime=" + deliverTime + "]";
	}
}
