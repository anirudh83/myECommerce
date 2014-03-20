package com.ecommerce.core.service;

import com.ecommerce.core.domain.Merchant;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MerchantService{
    Merchant get(Long id);
    Merchant persist(Merchant merchant);
    void delete(Long id);
    List<Merchant> findAll();

}
