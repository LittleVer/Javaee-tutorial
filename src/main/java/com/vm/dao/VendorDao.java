package com.vm.dao;

import java.util.List;

import com.entity.Vendor;

public interface VendorDao {
    public List<Vendor> find(Vendor vendor);
    
    public Vendor findById(Long id);

    public void add(Vendor vendor);

    public void update(Vendor vendor);

    public void delete(Long id);
}
