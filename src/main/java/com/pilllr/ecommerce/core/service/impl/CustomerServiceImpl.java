package com.pilllr.ecommerce.core.service.impl;

import com.pilllr.ecommerce.core.domain.Customer;
import com.pilllr.ecommerce.core.persistence.CustomerPersistence;
import com.pilllr.ecommerce.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/29/14
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerPersistence customerPersistence;
    @Override
    public Customer get(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Customer persist(Customer customer) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<Customer> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Customer> findByEmail(String email){
        Customer customer = new Customer();
        customer.setEmailAddress(email);
        return customerPersistence.findByExample(customer);
    }
}
