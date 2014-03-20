package com.mec.ecommerce.core.controller;

import com.mec.ecommerce.core.controller.exception.NoSuchPaymentProviderException;
import com.mec.ecommerce.core.service.PaymentProviderService;
import com.mec.ecommerce.core.transformer.PaymentProviderTransformer;
import com.mec.persistence.domain.PaymentProvider;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

/**
 * @author anirudh
 */
@Controller
@RequestMapping("/paymentprovider")
@Transactional(value = "coreTransactionManager")
public class PaymentProviderController {

    private final static Logger LOGGER = Logger.getLogger(PaymentProviderController.class.getName());

    @Autowired
    PaymentProviderService paymentProviderService;

    @Autowired
    PaymentProviderTransformer paymentProviderTransformer;

    @RequestMapping(value = "name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public PaymentProvider find(@PathVariable String name) {
        try {
            com.mec.ecommerce.core.domain.PaymentProvider paymentProvider = paymentProviderService.findByName(name);
            return paymentProviderTransformer.getPaymentProviderDTO(paymentProvider);
        } catch (ObjectNotFoundException e) {
            LOGGER.info("************* NoSuchMerchantExistsException Thrown**************");
            throw new NoSuchPaymentProviderException("No Such payment Provider exists in the system ");
        }
    }

}
