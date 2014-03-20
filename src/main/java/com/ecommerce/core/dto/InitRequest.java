package com.ecommerce.core.dto;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/13/14
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class InitRequest {

    private String pilllrTransactionId;
    private String merchantId;
    private String paymentProvider;

    public String getPilllrTransactionId() {
        return pilllrTransactionId;
    }

    public void setPilllrTransactionId(String pilllrTransactionId) {
        this.pilllrTransactionId = pilllrTransactionId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }
}
