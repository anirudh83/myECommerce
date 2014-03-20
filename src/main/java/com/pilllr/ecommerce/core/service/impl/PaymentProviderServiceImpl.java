package com.pilllr.ecommerce.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilllr.ecommerce.core.domain.PaymentProvider;
import com.pilllr.ecommerce.core.persistence.PaymentProviderPersistence;
import com.pilllr.ecommerce.core.service.PaymentProviderService;

/**
 * 
 * @author sunil
 *
 */
@Service
public class PaymentProviderServiceImpl implements PaymentProviderService {

    @Autowired
    PaymentProviderPersistence paymentProviderPersistence;

	@Override
	public PaymentProvider findByName(String name) {
		PaymentProvider paymentProvider = new PaymentProvider();
		paymentProvider.setName(name);
		List<PaymentProvider> paymentProviderList = paymentProviderPersistence.findByExample(paymentProvider);
		if(paymentProviderList != null && paymentProviderList.size() > 0) {
			return paymentProviderList.get(0);
		}
		
		return null;
	}

   
}
