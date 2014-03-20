package com.pilllr.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.pilllr.ecommerce.core.domain.PaymentProvider;
import com.pilllr.ecommerce.core.persistence.PaymentProviderPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class PaymentProviderPersistenceImpl extends GenericDAOImpl<PaymentProvider, Long> implements PaymentProviderPersistence {
}
