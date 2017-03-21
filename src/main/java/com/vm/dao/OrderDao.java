package com.vm.dao;

import java.util.List;

import com.entity.Order;

public interface OrderDao {
    public List<Order> find(Order order);
    
    public Order findById(Long id);

    public void add(Order order);

    public void update(Order order);

    public void delete(Long id);

}
