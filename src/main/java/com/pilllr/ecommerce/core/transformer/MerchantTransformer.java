package com.pilllr.ecommerce.core.transformer;

import java.util.ArrayList;
import java.util.List;

import com.pilllr.ecommerce.core.domain.PaymentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilllr.persistence.domain.Merchant;
import org.springframework.util.CollectionUtils;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/4/14
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MerchantTransformer {

    @Autowired
    private PaymentProviderTransformer paymentProviderTransformer;

    /**
     * @param merchant
     * @return DTO merchant object
     */
    public Merchant getMerchantDTO(com.pilllr.ecommerce.core.domain.Merchant merchant) {
        Merchant merchantDTO = new Merchant();
        merchantDTO.setId(merchant.getId());
        merchantDTO.setName(merchant.getName());
        merchantDTO.setPaymentProvider(paymentProviderTransformer.getPaymentProviderDTO(merchant.getPaymentProvider()));
        return merchantDTO;
    }

    public com.pilllr.ecommerce.core.domain.Merchant getLocalDomainObjectFromDTO(Merchant merchantDTO) {
        com.pilllr.ecommerce.core.domain.Merchant merchant = new com.pilllr.ecommerce.core.domain.Merchant();
        merchant.setName(merchantDTO.getName());
        merchant.setPaymentProvider(paymentProviderTransformer.getPaymentProviderLocalDomain(merchantDTO.getPaymentProvider()));
        return merchant;
    }

    public List<Merchant> getMerchantDTOList(List<com.pilllr.ecommerce.core.domain.Merchant> merchantList) {
        List<Merchant> merchantDTOList = new ArrayList<Merchant>();

        for (com.pilllr.ecommerce.core.domain.Merchant merchant : merchantList) {
            Merchant merchantDTO = new Merchant();
            merchantDTO.setId(merchant.getId());
            merchantDTO.setName(merchant.getName());
            merchantDTO.setPaymentProvider(paymentProviderTransformer.getPaymentProviderDTO(merchant.getPaymentProvider()));
            merchantDTOList.add(merchantDTO);
        }
        return merchantDTOList;

    }
}
