package com.mec.ecommerce.core.transformer;

import org.springframework.stereotype.Service;

import com.mec.persistence.domain.PaymentProvider;

/**
 * 
 * @author sunil
 *
 */
@Service
public class PaymentProviderTransformer {

	public PaymentProvider getPaymentProviderDTO(com.mec.ecommerce.core.domain.PaymentProvider paymentProvider) {
		
		PaymentProvider paymentProviderDTO = new PaymentProvider();
		paymentProviderDTO.setId(paymentProvider.getId());
		paymentProviderDTO.setClientId(paymentProvider.getClientId());
		paymentProviderDTO.setName(paymentProvider.getName());
		paymentProviderDTO.setPassword(paymentProvider.getPassword());
		return paymentProviderDTO;
	}

    public com.mec.ecommerce.core.domain.PaymentProvider getPaymentProviderLocalDomain(PaymentProvider paymentProviderDTO) {

        com.mec.ecommerce.core.domain.PaymentProvider paymentProvider = new com.mec.ecommerce.core.domain.PaymentProvider();
        paymentProvider.setId(paymentProviderDTO.getId());
        paymentProvider.setClientId(paymentProviderDTO.getClientId());
        paymentProvider.setName(paymentProviderDTO.getName());
        paymentProvider.setPassword(paymentProviderDTO.getPassword());
        return paymentProvider;
    }



}
