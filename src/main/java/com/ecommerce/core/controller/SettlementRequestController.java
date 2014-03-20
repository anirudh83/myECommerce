package com.ecommerce.core.controller;

import com.ecommerce.core.exception.NoSuchMerchantExistsException;
import com.ecommerce.core.domain.Merchant;
import com.ecommerce.core.domain.Transaction;
import com.ecommerce.core.service.MerchantService;
import com.ecommerce.core.service.TransactionService;
import com.ecommerce.core.transformer.MerchantTransformer;
import com.mec.persistence.domain.SettlementRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 3/3/14
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/settlement")
@Transactional(value = "coreTransactionManager")
public class SettlementRequestController {
    private final static Logger LOGGER = Logger.getLogger(SettlementRequestController.class.getName());

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MerchantTransformer merchantTransformer;

    @Autowired
    private MerchantService merchantService;


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Transaction create(@RequestBody SettlementRequest settlementRequest) throws NoSuchMerchantExistsException {
        LOGGER.info("************* REQUEST RECEIVED TO CREATE SETTLEMENT REQUEST TRANSACTION**************");
        Transaction transaction = new Transaction();

        Long merchantId = settlementRequest.getMerchant().getId();

        try {
            Merchant merchant = merchantService.get(merchantId);
        } catch (ObjectNotFoundException e) {
            LOGGER.info("************* NoSuchMerchantExistsException Thrown**************");
            throw new NoSuchMerchantExistsException("The merchant does'nt exist in the system : " + merchantId);
        }

        transaction.setMerchantId(merchantId);
        transaction.setTransactionType("SETTLEMENT");
        transaction.setStatus("PENDING");
        if(settlementRequest.getMerchant().getPaymentProvider()!=null){
            transaction.setPaymentProvider(settlementRequest.getMerchant().getPaymentProvider().getName());
        }else{
            //For now putting default
            transaction.setPaymentProvider("WEBPAY");
        }
        transaction.setPilllrTransactionId(settlementRequest.getPilllrTransactionId());
        transactionService.persist(transaction);
        LOGGER.info("************* Transaction saved **************");
        return transaction;

    }

}
