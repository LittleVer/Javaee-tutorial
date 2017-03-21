package com.vm.service;

import java.util.List;

import com.entity.Order;

public interface OrderBiz {
	public List<Order> find(Order order);

    public void add(Order order);

    public void update(Order order);

    public void delete(Long id);

	public Order findById(Long id);


}
