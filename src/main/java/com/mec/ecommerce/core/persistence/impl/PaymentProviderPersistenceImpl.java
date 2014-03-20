package com.mec.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.mec.ecommerce.core.domain.PaymentProvider;
import com.mec.ecommerce.core.persistence.PaymentProviderPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class PaymentProviderPersistenceImpl extends GenericDAOImpl<PaymentProvider, Long> implements PaymentProviderPersistence {
}
