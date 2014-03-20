package com.mec.ecommerce.core.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/30/14
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="mec_product_category")
public class ProductCategory {

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique=true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
