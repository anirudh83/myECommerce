package com.ecommerce.core.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */


public class Order {

    private String orderId;
    private String orderDate;
    private String orderType;
    private String quantity;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
