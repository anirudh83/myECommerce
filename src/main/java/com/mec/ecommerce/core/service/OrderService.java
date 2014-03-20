package com.mec.ecommerce.core.service;

import com.mec.ecommerce.core.domain.Order;


/**
 * 
 * @author sunil
 *
 */
public interface OrderService {
	
	Order get(Long id);
	
	Order persist(Order order);

}
