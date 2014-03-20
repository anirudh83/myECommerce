package com.ecommerce.core.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */


public class Merchant {

    private Long id;
    private String name;
    private PaymentProvider paymentProvider;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentProvider getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }
}
