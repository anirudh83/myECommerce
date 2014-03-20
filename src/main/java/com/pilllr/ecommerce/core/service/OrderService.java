package com.pilllr.ecommerce.core.service;

import com.pilllr.ecommerce.core.domain.Order;


/**
 * 
 * @author sunil
 *
 */
public interface OrderService {
	
	Order get(Long id);
	
	Order persist(Order order);

}
