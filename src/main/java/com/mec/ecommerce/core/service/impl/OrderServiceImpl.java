package com.mec.ecommerce.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.ecommerce.core.domain.Order;
import com.mec.ecommerce.core.persistence.OrderPersistence;
import com.mec.ecommerce.core.service.OrderService;

/**
 * 
 * @author sunil
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderPersistence orderPersistence;

	@Override
	public Order get(Long id) {
		return orderPersistence.findById(id);
	}

	@Override
	public Order persist(Order order) {
		return orderPersistence.makePersistent(order);
	}
   
}
