package com.ecommerce.core.service.impl;

import com.ecommerce.core.domain.Merchant;
import com.ecommerce.core.persistence.MerchantPersistence;
import com.ecommerce.core.service.MerchantService;
import com.ecommerce.core.service.PaymentProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantPersistence merchantPersistence;

    @Autowired
    PaymentProviderService paymentProviderService;

    @Override
    public Merchant get(Long id) {
        Merchant merchant = merchantPersistence.findById(id);
        merchant.getPaymentProvider();
        return merchant;
    }

    @Override
    public Merchant persist(Merchant merchant) {
        return merchantPersistence.makePersistent(merchant);
    }

    @Override
    public void delete(Long id) {
        merchantPersistence.delete(get(id));
    }

    @Override
    public List<Merchant> findAll() {
        return merchantPersistence.findAll();
    }
}
