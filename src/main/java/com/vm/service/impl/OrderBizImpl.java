package com.vm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Order;
import com.vm.dao.OrderDao;
import com.vm.service.OrderBiz;

@Service
public class OrderBizImpl implements OrderBiz {
    @Autowired
    private OrderDao orderDao;

    public List<Order> find(Order order) {
    	return orderDao.find(order);
    }

	public Order findById(Long id) {
		return orderDao.findById(id);
	}

	public void add(Order order) {
        orderDao.add(order);
    }

    public void update(Order order) {
        orderDao.update(order);
    }

    public void delete(Long orderId) {
        orderDao.delete(orderId);
    }

}
