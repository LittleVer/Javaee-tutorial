package com.vm.dao;

import java.util.List;

import com.entity.Procurement;

public interface ProcurementDao {
    public List<Procurement> find(Procurement procurement);
    
    public Procurement findById(Long id);

    public void add(Procurement procurement);

    public void update(Procurement procurement);

    public void delete(Long id);

}
