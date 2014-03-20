package com.ecommerce.core.service;

import com.ecommerce.core.domain.Order;


/**
 * 
 * @author sunil
 *
 */
public interface OrderService {
	
	Order get(Long id);
	
	Order persist(Order order);

}
