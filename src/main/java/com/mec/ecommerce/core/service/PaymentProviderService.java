package com.mec.ecommerce.core.service;

import com.mec.ecommerce.core.domain.PaymentProvider;


/**
 * 
 * @author sunil
 *
 */
public interface PaymentProviderService{
	
	PaymentProvider findByName(String name);

}
