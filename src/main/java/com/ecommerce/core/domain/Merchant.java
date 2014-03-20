package com.ecommerce.core.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "mec_merchant")
@TableGenerator(name="mec_merchant_sequence_generator",initialValue = 9999 ,allocationSize = 50)
public class Merchant {

    private Long id;
    private String name;
    private PaymentProvider paymentProvider;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE , generator = "mec_merchant_sequence_generator")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="merchant_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="payment_provider_id")
    public PaymentProvider getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }
}
