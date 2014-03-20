package com.pilllr.ecommerce.core.domain;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/23/14
 * Time: 8:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "mec_transaction")
public class Transaction {
    private Long id;
    private String pilllrTransactionId;
    private String transactionType;
    private Long merchantId;
    private String paymentProvider;
    private String status;
    private String createdDate;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="pilllr_transaction_id")
    public String getPilllrTransactionId() {
        return pilllrTransactionId;
    }

    public void setPilllrTransactionId(String pilllrTransactionId) {
        this.pilllrTransactionId = pilllrTransactionId;
    }

    @Column
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Column(name="merchant_id")
    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    @Column(name="payment_provider")
    public String getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="created_date")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
