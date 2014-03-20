package com.ecommerce.core.domain;

import javax.persistence.*;

/**
 * 
 * @author sunil
 *
 */

@Entity
@Table(name = "mec_credit_card")
public class CreditCard {

	private Long id;
	private String captureMethod;
	private String cardReference;
	private String cardType;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false ,unique = true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="capture_method")
	public String getCaptureMethod() {
		return captureMethod;
	}
	
	public void setCaptureMethod(String captureMethod) {
		this.captureMethod = captureMethod;
	}
	
	@Column(name="card_reference")
	public String getCardReference() {
		return cardReference;
	}
	
	public void setCardReference(String cardReference) {
		this.cardReference = cardReference;
	}
	
	@Column(name = "card_type")
	public String getCardType() {
		return cardType;
	}
	
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
