package com.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class Procurement implements Serializable{
	private static final long serialVersionUID = 6534271072663770032L;
	private Long id;
	@NotEmpty
    private String procureId;
	@NotEmpty
    private Integer inventory;
	@NotEmpty
    private Integer startInventory;
	@NotEmpty
    private Integer endInventory;
	@NotEmpty
    private Integer price;
	@NotEmpty
    private Integer startPrice;
	@NotEmpty
    private Integer endPrice;
	@NotEmpty
    private String procureTime;
	@NotEmpty
    private Date startTime;
	@NotEmpty
    private Date endTime;
	
	private String procureFilePath;
	private String procureFileName;
	
	public String getProcureFilePath() {
		return procureFilePath;
	}
	public void setProcureFilePath(String procureFilePath) {
		this.procureFilePath = procureFilePath;
	}
	public String getProcureFileName() {
		return procureFileName;
	}
	public void setProcureFileName(String procureFileName) {
		this.procureFileName = procureFileName;
	}
	public Procurement() {
		super();
	}
	public Procurement(String carId,Boolean isSale, Integer high,
			Integer length, Integer wide, Integer weight) {
		super();
		this.procureId = procureId;
		this.inventory = inventory;
		this.price = price;
		this.procureTime = procureTime;
	}
	public Integer getStartInventory() {
		return startInventory;
	}
	public void setStartInventory(Integer startInventory) {
		this.startInventory = startInventory;
	}
	public Integer getEndInventory() {
		return endInventory;
	}
	public void setEndInventory(Integer endInventory) {
		this.endInventory = endInventory;
	}
	public Integer getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(Integer startPrice) {
		this.startPrice = startPrice;
	}
	public Integer getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(Integer endPrice) {
		this.endPrice = endPrice;
	}

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProcureId() {
		return procureId;
	}
	public void setProcureId(String procureId) {
		this.procureId = procureId;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getProcureTime() {
		return procureTime;
	}
	public void setProcureTime(String procureTime) {
		this.procureTime = procureTime;
	}
	@Override
	public String toString() {
		return "Procurement [id=" + id + ", procureId=" + procureId + ", inventory=" + inventory
				+ ", price=" + price + ", procureTime=" + procureTime + "]";
	}
}
