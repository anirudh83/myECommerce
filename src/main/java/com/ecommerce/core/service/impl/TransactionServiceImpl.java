package com.ecommerce.core.service.impl;

import com.ecommerce.core.domain.Transaction;
import com.ecommerce.core.persistence.TransactionPersistence;
import com.ecommerce.core.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author anirudh
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionPersistence transactionPersistence;

    @Override
    public Transaction get(Long id) {
        Transaction transaction = transactionPersistence.findById(id);
        return transaction;
    }

    @Override
    public Transaction persist(Transaction transaction) {
        transaction.setCreatedDate(new Timestamp(new Date().getTime()).toString());
        return transactionPersistence.makePersistent(transaction);
    }

    @Override
    public void delete(Long id) {
        transactionPersistence.delete(get(id));
    }

    @Override
    public List<Transaction> findAll() {
        return transactionPersistence.findAll();
    }

    @Override
    public Transaction findByPilllrTransactionId(String pilllrTransactionId) {
        Transaction transaction = new Transaction();
        transaction.setPilllrTransactionId(pilllrTransactionId);
        List<Transaction> transactionList = transactionPersistence.findByExample(transaction);
        if(transactionList != null && transactionList.size() > 0) {
            return transactionList.get(0);
        }
        return null;
    }


}
