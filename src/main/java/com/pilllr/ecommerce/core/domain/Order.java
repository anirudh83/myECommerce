package com.pilllr.ecommerce.core.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "mec_order")
public class Order {

    private Long id;
    private String orderNumber;
    private Payment payment;
    private String quantity;
    private Customer customer;
    private String date;
    private String orderType;
    private Status status;
    private Transaction transaction;
    private String totalAmount;
    private String currency;
    private CreditCard creditCard;
    
    private Set<Product> products = new HashSet<Product>(0);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false , unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="order_number", nullable = false)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Column(name="quantity")
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @OneToOne
    @JoinColumn(name="payment_id")
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="transaction_id")
    public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name="order_type")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @OneToOne
    @JoinColumn(name="status_id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	@Column(name="total_amount")
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name="currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="credit_card_id")
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	
	
}
