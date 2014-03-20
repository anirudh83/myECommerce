package com.ecommerce.core.controller;

import com.ecommerce.core.domain.Merchant;
import com.ecommerce.core.dto.QpfsRequest;
import com.ecommerce.core.exception.NoSuchMerchantExistsException;
import com.ecommerce.core.service.MerchantService;
import com.ecommerce.core.service.OrderService;
import com.ecommerce.core.service.TransactionService;
import com.ecommerce.core.transformer.QpfsRequestTransformer;
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
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/qpfs")
@Transactional(value = "coreTransactionManager")
public class QpfsRequestController {
    private final static Logger LOGGER = Logger.getLogger(MerchantController.class.getName());

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private QpfsRequestTransformer qpfsRequestTransformer;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String create(@RequestBody QpfsRequest qpfsRequest) {
        LOGGER.info("************* REQUEST RECEIVED TO CREATE QPFS REQUEST TRANSACTION**************");
        Long merchantId = qpfsRequest.getMerchant().getId();

        try {
            Merchant merchant = merchantService.get(merchantId);
        } catch (ObjectNotFoundException e) {
            LOGGER.info("************* NoSuchMerchantExistsException Thrown**************");
            throw new NoSuchMerchantExistsException("The merchant does'nt exist in the system : " + merchantId);
        }
        com.ecommerce.core.domain.Order order = qpfsRequestTransformer.getOrder(qpfsRequest);

        com.ecommerce.core.domain.Order savedOrder = orderService.persist(order);

        LOGGER.info("************* Transaction saved **************");
        return "TRANSACTION SAVED SUCCESSFULLY";
    }

}
