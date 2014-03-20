package com.ecommerce.core.dto;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 3/3/14
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class SettlementRequest {

    private String pilllrTransactionId;
    private String merchantReference;
    private Merchant merchant;
    private SettlementTransaction settlementTransaction;
    private String authCode;
    private String providerTransactionReference;
    private String transactionType;

    public String getPilllrTransactionId() {
        return pilllrTransactionId;
    }

    public void setPilllrTransactionId(String pilllrTransactionId) {
        this.pilllrTransactionId = pilllrTransactionId;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public SettlementTransaction getSettlementTransaction() {
        return settlementTransaction;
    }

    public void setSettlementTransaction(SettlementTransaction settlementTransaction) {
        this.settlementTransaction = settlementTransaction;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getProviderTransactionReference() {
        return providerTransactionReference;
    }

    public void setProviderTransactionReference(String providerTransactionReference) {
        this.providerTransactionReference = providerTransactionReference;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
