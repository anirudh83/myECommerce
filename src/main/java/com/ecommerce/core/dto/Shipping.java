package com.ecommerce.core.dto;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/13/14
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Shipping {

    private String email;
    private String firstName;
    private String lastName;
    private String shippingMethod;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }
}
