package com.mec.ecommerce.core.controller;


import com.mec.ecommerce.core.controller.exception.NoSuchMerchantExistsException;
import com.mec.ecommerce.core.domain.Merchant;
import com.mec.ecommerce.core.domain.Transaction;
import com.mec.ecommerce.core.service.MerchantService;
import com.mec.ecommerce.core.service.TransactionService;
import com.mec.ecommerce.core.transformer.MerchantTransformer;
import com.mec.persistence.domain.InitRequest;
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
 * Date: 2/13/14
 * Time: 6:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/init-hcs")
@Transactional(value = "coreTransactionManager")
public class InitRequestController {
    private final static Logger LOGGER = Logger.getLogger(MerchantController.class.getName());

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MerchantTransformer merchantTransformer;
    
    @Autowired
    private MerchantService merchantService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InitRequest create(@RequestBody InitRequest initRequest) throws NoSuchMerchantExistsException {
        LOGGER.info("************* REQUEST RECEIVED TO CREATE INIT REQUEST TRANSACTION**************");
        Transaction transaction = new Transaction();

        Long merchantId = Long.parseLong(initRequest.getMerchantId());

        try {
            Merchant merchant = merchantService.get(merchantId);
        } catch (ObjectNotFoundException e) {
            LOGGER.info("************* NoSuchMerchantExistsException Thrown**************");
            throw new NoSuchMerchantExistsException("The merchant does'nt exist in the system : " + merchantId);
        }

        transaction.setMerchantId(merchantId);
        transaction.setTransactionType("INIT");
        transaction.setPaymentProvider(initRequest.getPaymentProvider());
        transaction.setPilllrTransactionId(initRequest.getPilllrTransactionId());
        transaction.setStatus("PENDING");
        transactionService.persist(transaction);
        LOGGER.info("************* Transaction saved **************");
        return initRequest;

    }


}
