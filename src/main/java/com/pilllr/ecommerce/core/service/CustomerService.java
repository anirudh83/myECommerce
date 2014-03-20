package com.pilllr.ecommerce.core.service;

import com.pilllr.ecommerce.core.domain.Customer;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/29/14
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerService {
    Customer get(Long id);
    Customer persist(Customer customer);
    void delete(Long id);
    Collection<Customer> findAll();
}
