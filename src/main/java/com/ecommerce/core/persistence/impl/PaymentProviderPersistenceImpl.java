package com.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.ecommerce.core.domain.PaymentProvider;
import com.ecommerce.core.persistence.PaymentProviderPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class PaymentProviderPersistenceImpl extends GenericDAOImpl<PaymentProvider, Long> implements PaymentProviderPersistence {
}
