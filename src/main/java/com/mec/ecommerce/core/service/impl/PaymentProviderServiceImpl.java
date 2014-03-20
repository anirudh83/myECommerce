package com.mec.ecommerce.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.ecommerce.core.domain.PaymentProvider;
import com.mec.ecommerce.core.persistence.PaymentProviderPersistence;
import com.mec.ecommerce.core.service.PaymentProviderService;

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
