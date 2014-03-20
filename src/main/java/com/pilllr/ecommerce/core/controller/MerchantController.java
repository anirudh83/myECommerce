package com.pilllr.ecommerce.core.controller;

import com.pilllr.ecommerce.core.controller.exception.NoPaymentProviderProvidedException;
import com.pilllr.ecommerce.core.controller.exception.NoSuchMerchantExistsException;
import com.pilllr.ecommerce.core.service.MerchantService;
import com.pilllr.ecommerce.core.transformer.MerchantTransformer;

import com.pilllr.persistence.domain.Merchant;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/merchants")
@Transactional(value = "coreTransactionManager")
public class MerchantController {

    private final static Logger LOGGER = Logger.getLogger(MerchantController.class.getName());

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantTransformer merchantTransformer;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Merchant getMerchant(@PathVariable Long id) {
       LOGGER.info("****************** GET MERCHANT with id: "+id+"***************");
        return merchantTransformer.getMerchantDTO(merchantService.get(id));
    }

    private void copy(Merchant resultMerchant, Merchant merchant) {
        resultMerchant.setId(merchant.getId());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<Merchant> getAllMerchants(){
        return merchantTransformer.getMerchantDTOList(merchantService.findAll());
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Merchant createMerchant(@RequestBody Merchant merchant){
        LOGGER.info("*********Creating new merchant********");
        if(merchant.getPaymentProvider()==null){
            LOGGER.info("*********Could not save merchant as payment provider not provided********");
            throw new NoPaymentProviderProvidedException("Payment provider not provided");
        }
            return merchantTransformer.getMerchantDTO(merchantService.persist(merchantTransformer.getLocalDomainObjectFromDTO(merchant)));
    }


    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Merchant updateMerchant(@RequestBody Merchant merchant){

        try {
            com.pilllr.ecommerce.core.domain.Merchant existingMerchant = merchantService.get(merchant.getId());
        } catch (ObjectNotFoundException e) {
            LOGGER.info("************* NoSuchMerchantExistsException Thrown**************");
            throw new NoSuchMerchantExistsException("Trying to update non existing merchant : " + merchant.getId());
        }
        return merchantTransformer.getMerchantDTO(merchantService.persist(merchantTransformer.getLocalDomainObjectFromDTO(merchant)));

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMerchant(@PathVariable Long id){
           merchantService.delete(id);
    }


}


