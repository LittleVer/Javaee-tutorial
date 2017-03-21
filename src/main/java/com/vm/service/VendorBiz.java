package com.vm.service;

import java.util.List;

import com.entity.Vendor;

public interface VendorBiz {
	public List<Vendor> find(Vendor vendor);

    public void add(Vendor vendor);

    public void update(Vendor vendor);

    public void delete(Long id);

	public Vendor findById(Long vendor);
}
