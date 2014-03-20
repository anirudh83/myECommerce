package com.pilllr.ecommerce.core.persistence.impl;

import com.pilllr.ecommerce.core.domain.Transaction;
import com.pilllr.ecommerce.core.persistence.TransactionPersistence;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class TransactionPersistenceImpl extends GenericDAOImpl<Transaction, Long> implements TransactionPersistence {
}
