package com.ecommerce.core.dto;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/4/14
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaymentProvider {

    private Long id;
    private String name;
    private String clientId;
    private String password;

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
