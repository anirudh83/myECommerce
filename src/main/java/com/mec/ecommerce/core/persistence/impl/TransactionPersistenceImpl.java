package com.mec.ecommerce.core.persistence.impl;

import com.mec.ecommerce.core.domain.Transaction;
import com.mec.ecommerce.core.persistence.TransactionPersistence;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class TransactionPersistenceImpl extends GenericDAOImpl<Transaction, Long> implements TransactionPersistence {
}
