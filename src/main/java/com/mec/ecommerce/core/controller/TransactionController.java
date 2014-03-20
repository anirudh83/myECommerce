package com.mec.ecommerce.core.controller;

import com.mec.ecommerce.core.controller.exception.NoSuchTransactionExists;
import com.mec.ecommerce.core.domain.Transaction;
import com.mec.ecommerce.core.service.TransactionService;
import com.mec.persistence.domain.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * @author anirudh
 */
@Controller
@RequestMapping("/transactions")
@Transactional(value = "coreTransactionManager")
public class TransactionController {
    private final static Logger LOGGER = Logger.getLogger(MerchantController.class.getName());

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/{pilllrTransactionId}", method = RequestMethod.GET)
    @ResponseBody
    public Transaction getTransaction(@PathVariable String pilllrTransactionId) {
        LOGGER.info("****************** GET Transaction with mec Transaction id: " + pilllrTransactionId);
        Transaction transaction = transactionService.findByPilllrTransactionId(pilllrTransactionId);
        if(transaction==null){
            throw new NoSuchTransactionExists("TRANSACTION DOES NOT EXIST");
        }
        return transaction;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<Transaction> getAllTransactions() {
        return transactionService.findAll();
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object updateTransaction(@RequestBody TransactionStatus transactionStatus) {
        LOGGER.info("****** REQUEST RECEIVED TO update TRANSACTION status with mec Txn id : " + transactionStatus.getPilllrTransactionId() + "**************");
        Transaction transaction = transactionService.findByPilllrTransactionId(transactionStatus.getPilllrTransactionId());
        if (transaction != null) {
            transaction.setStatus(transactionStatus.getStatus());
            transactionService.persist(transaction);

            LOGGER.info("************* Transaction status updated **************");
            return transactionStatus;
        }
        LOGGER.info("***UPDATE FAILED AS TRANSACTION DOES NOT EXIST***");
        return "FAILED AS TRANSACTION DOES NOT EXIST";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object createTransaction(@RequestBody TransactionStatus transactionStatus) {
        LOGGER.info("****** REQUEST RECEIVED TO create new transaction status with mec Txn id : " + transactionStatus.getPilllrTransactionId() + "**************");
        Transaction transaction = new Transaction();
        transaction.setPilllrTransactionId(transactionStatus.getPilllrTransactionId());
        transaction.setStatus("PENDING");
        transactionService.persist(transaction);
        return transaction;
    }



}


