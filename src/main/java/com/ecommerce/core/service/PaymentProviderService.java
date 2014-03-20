package com.ecommerce.core.service;

import com.ecommerce.core.domain.PaymentProvider;


/**
 * 
 * @author sunil
 *
 */
public interface PaymentProviderService{
	
	PaymentProvider findByName(String name);

}
