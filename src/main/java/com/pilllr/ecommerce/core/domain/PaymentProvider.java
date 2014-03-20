package com.pilllr.ecommerce.core.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/4/14
 * Time: 1:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="mec_payment_provider")
public class PaymentProvider {

    private Long id;
    private String name;
    private String clientId;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false ,unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="client_id")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
