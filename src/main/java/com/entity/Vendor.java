package com.entity;

import java.io.Serializable;

public class Vendor implements Serializable {
	private static final long serialVersionUID = -4471300730875048038L;
	private Long id;
	private String vendorName;
	private String contact;
	private String url;
	private String address;
	private String telephone;
	private String phone;
	private String email;
	private Long attachId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getAttachId() {
		return attachId;
	}
	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Vendor [id=" + id + ", vendorName=" + vendorName + ", contact="
				+ contact + ", url=" + url + ", address=" + address
				+ ", telephone=" + telephone + ", phone=" + phone + ", email="
				+ email + ", attachId=" + attachId + "]";
	}
}
