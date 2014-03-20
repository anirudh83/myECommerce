package com.pilllr.ecommerce.core.service;

import com.pilllr.ecommerce.core.domain.PaymentProvider;


/**
 * 
 * @author sunil
 *
 */
public interface PaymentProviderService{
	
	PaymentProvider findByName(String name);

}
