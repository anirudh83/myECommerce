package com.ecommerce.core.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/13/14
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class QpfsTransaction {

    private Address billingAddress;
    private CreditCard qpfsCreditCardRequest;
    private String currency;
    private Customer customer;
    private String date;
    private Order order;
    private List<Product> products;
    private Shipping shipping;
    private String totalAmount;

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public CreditCard getQpfsCreditCardRequest() {
        return qpfsCreditCardRequest;
    }

    public void setQpfsCreditCardRequest(CreditCard qpfsCreditCardRequest) {
        this.qpfsCreditCardRequest = qpfsCreditCardRequest;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
