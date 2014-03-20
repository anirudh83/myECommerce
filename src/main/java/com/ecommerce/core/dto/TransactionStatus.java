package com.ecommerce.core.dto;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/24/14
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionStatus {

    private String pilllrTransactionId;
    private String status;

    public String getPilllrTransactionId() {
        return pilllrTransactionId;
    }

    public void setPilllrTransactionId(String pilllrTransactionId) {
        this.pilllrTransactionId = pilllrTransactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
