package com.ecommerce.core.transformer;


import com.ecommerce.core.domain.CustomerAddress;
import com.ecommerce.core.domain.Transaction;
import com.ecommerce.core.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 
 * @author anirudh
 *
 */
@Service
public class QpfsRequestTransformer {

	private static final String QPFS = "QPFS";
	private static final String BILLING = "billing";
	private static final String SHIPPING = "shipping";
	
	public com.ecommerce.core.domain.Order getOrder(QpfsRequest qpfsRequest) {
		QpfsTransaction transaction = qpfsRequest.getTransaction();
		List<Product> productsDTO = transaction.getProducts();
		Order orderDTO = transaction.getOrder();
		
		com.ecommerce.core.domain.Order order = new com.ecommerce.core.domain.Order();
		order.setDate(orderDTO.getOrderDate());
		order.setOrderNumber(orderDTO.getOrderId());
		order.setOrderType(orderDTO.getOrderType());
		order.setTotalAmount(transaction.getTotalAmount());
		order.setCurrency(transaction.getCurrency());

		
		populateProductsInOrder(productsDTO, order);
		
		Customer customerDTO = transaction.getCustomer();
		populateCustomer(customerDTO, order);
		populateShippingAddress(order, customerDTO);
		populateBillingAddress(order, transaction.getBillingAddress());

		populateTransaction(order, qpfsRequest);
		populateCreditCard(order, transaction.getQpfsCreditCardRequest());
		
		return order;
	}


	protected void populateCreditCard(com.ecommerce.core.domain.Order order, CreditCard qpfsCreditCard) {
		com.ecommerce.core.domain.CreditCard creditCard = new com.ecommerce.core.domain.CreditCard();
		creditCard.setCaptureMethod(qpfsCreditCard.getCaptureMethod());
		creditCard.setCardReference(qpfsCreditCard.getCardReference());
		creditCard.setCardType(qpfsCreditCard.getCardType());
		
		order.setCreditCard(creditCard);
	}


	protected void populateTransaction(com.ecommerce.core.domain.Order order, QpfsRequest qpfsRequest) {
		Transaction transaction = new Transaction();
		transaction.setMerchantId(qpfsRequest.getMerchant().getId());
		transaction.setPaymentProvider(qpfsRequest.getPaymentProvider());
		transaction.setPilllrTransactionId(qpfsRequest.getTransactionId());
		transaction.setTransactionType(QPFS);
        transaction.setStatus("PENDING");
		
		order.setTransaction(transaction);
	}


	protected void populateCustomer(Customer customerDTO, com.ecommerce.core.domain.Order order) {
		com.ecommerce.core.domain.Customer customer = new com.ecommerce.core.domain.Customer();
		
		customer.setDateOfBirth(customerDTO.getBirthdate());
		customer.setEmailAddress(customerDTO.getEmail());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setLoyaltyCardNumber(customerDTO.getLoyaltyCardNumber());
		customer.setTitle(customerDTO.getTitle());
		
		order.setCustomer(customer);
	}

	protected void populateShippingAddress(com.ecommerce.core.domain.Order order, Customer customerDTO) {
		com.ecommerce.core.domain.Customer customer = order.getCustomer();
		Collection<CustomerAddress> customerAddresses = customer.getCustomerAddresses();
		if(customerAddresses == null) {
			customerAddresses = new ArrayList<CustomerAddress>();
			customer.setCustomerAddresses(customerAddresses);
		}
		
		
		com.ecommerce.core.dto.Address shippingDTO = customerDTO.getAddress();

		com.ecommerce.core.domain.Address shippingAddress = new com.ecommerce.core.domain.Address();
		shippingAddress.setAddressLine1(shippingDTO.getAddress1());
		shippingAddress.setAddressLine2(shippingDTO.getAddress2());
		shippingAddress.setCity(shippingDTO.getCity());
		shippingAddress.setCountry(shippingDTO.getCountry());
		shippingAddress.setState(shippingDTO.getState());
		shippingAddress.setZip(shippingDTO.getPostalCode());
		
		CustomerAddress custShippingAddress = new CustomerAddress();
		custShippingAddress.setAddressName(SHIPPING);
		custShippingAddress.setCustomer(customer);
		custShippingAddress.setAddress(shippingAddress);
		
		customerAddresses.add(custShippingAddress);
		
	}

	protected void populateBillingAddress(com.ecommerce.core.domain.Order order,Address billingAddrDTO) {
		com.ecommerce.core.domain.Customer customer = order.getCustomer();
		Collection<CustomerAddress> customerAddresses = customer.getCustomerAddresses();
		if(customerAddresses == null) {
			customerAddresses = new ArrayList<CustomerAddress>();
		}

        com.ecommerce.core.domain.Address billingAddress = new com.ecommerce.core.domain.Address();
		
		billingAddress.setAddressLine1(billingAddrDTO.getAddress1());
		billingAddress.setAddressLine2(billingAddrDTO.getAddress2());
		billingAddress.setCity(billingAddrDTO.getCity());
		billingAddress.setCountry(billingAddrDTO.getCountry());
		billingAddress.setState(billingAddrDTO.getState());
		billingAddress.setZip(billingAddrDTO.getPostalCode());
		
		
		
		CustomerAddress custBillingAddress = new CustomerAddress();
		custBillingAddress.setAddressName(BILLING);
		custBillingAddress.setCustomer(customer);
		custBillingAddress.setAddress(billingAddress);
		
		customerAddresses.add(custBillingAddress);
		
	}	
	
	protected void populateProductsInOrder(List<Product> productsDTO, com.ecommerce.core.domain.Order order) {
		Set<com.ecommerce.core.domain.Product> productList = new HashSet<com.ecommerce.core.domain.Product>();
		
		for (Product productDTO : productsDTO) {
			com.ecommerce.core.domain.Product product = new com.ecommerce.core.domain.Product();
			
			product.setProductId(productDTO.getProductId());
			product.setDescription(productDTO.getDescription());
			
			product.setOrder(order);
			product.setPrice(productDTO.getPrice());
			product.setProductCode(productDTO.getProductCode());
			product.setCategory(productDTO.getProductCategory());

			product.setQuantity(productDTO.getUnit());
			product.setType(productDTO.getProductType());

			productList.add(product);
			
		}
		
		order.setProducts(productList);
	}

}
