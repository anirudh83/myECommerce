package com.mec.ecommerce.core.service;

import java.util.List;

import com.mec.ecommerce.core.domain.Transaction;

/**
 * 
 * @author sunil
 *
 */
public interface TransactionService{
    Transaction get(Long id);
    Transaction persist(Transaction transaction);
    void delete(Long id);
    List<Transaction> findAll();
    Transaction findByPilllrTransactionId(String pilllrTransactionId);

}
