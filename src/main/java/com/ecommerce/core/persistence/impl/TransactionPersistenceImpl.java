package com.ecommerce.core.persistence.impl;

import com.ecommerce.core.domain.Transaction;
import com.ecommerce.core.persistence.TransactionPersistence;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class TransactionPersistenceImpl extends GenericDAOImpl<Transaction, Long> implements TransactionPersistence {
}
