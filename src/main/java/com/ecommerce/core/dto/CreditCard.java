package com.ecommerce.core.dto;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/13/14
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreditCard {

    private Long id;
    private String captureMethod;
    private String cardReference;
    private String cardType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaptureMethod() {
        return captureMethod;
    }

    public void setCaptureMethod(String captureMethod) {
        this.captureMethod = captureMethod;
    }

    public String getCardReference() {
        return cardReference;
    }

    public void setCardReference(String cardReference) {
        this.cardReference = cardReference;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
