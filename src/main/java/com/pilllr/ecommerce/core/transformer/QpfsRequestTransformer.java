package com.pilllr.ecommerce.core.transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.pilllr.ecommerce.core.domain.Address;
import com.pilllr.ecommerce.core.domain.CustomerAddress;
import com.pilllr.ecommerce.core.domain.Transaction;
import com.pilllr.persistence.domain.CreditCard;
import com.pilllr.persistence.domain.Customer;
import com.pilllr.persistence.domain.Order;
import com.pilllr.persistence.domain.Product;
import com.pilllr.persistence.domain.QpfsRequest;
import com.pilllr.persistence.domain.QpfsTransaction;

/**
 * 
 * @author sunil
 *
 */
@Service
public class QpfsRequestTransformer {

	private static final String QPFS = "QPFS";
	private static final String BILLING = "billing";
	private static final String SHIPPING = "shipping";
	
	public com.pilllr.ecommerce.core.domain.Order getOrder(QpfsRequest qpfsRequest) {
		QpfsTransaction transaction = qpfsRequest.getTransaction();
		List<Product> productsDTO = transaction.getProducts();
		Order orderDTO = transaction.getOrder();
		
		com.pilllr.ecommerce.core.domain.Order order = new com.pilllr.ecommerce.core.domain.Order();
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


	protected void populateCreditCard(com.pilllr.ecommerce.core.domain.Order order, CreditCard qpfsCreditCard) {
		com.pilllr.ecommerce.core.domain.CreditCard creditCard = new com.pilllr.ecommerce.core.domain.CreditCard();
		creditCard.setCaptureMethod(qpfsCreditCard.getCaptureMethod());
		creditCard.setCardReference(qpfsCreditCard.getCardReference());
		creditCard.setCardType(qpfsCreditCard.getCardType());
		
		order.setCreditCard(creditCard);
	}


	protected void populateTransaction(com.pilllr.ecommerce.core.domain.Order order, QpfsRequest qpfsRequest) {
		Transaction transaction = new Transaction();
		transaction.setMerchantId(qpfsRequest.getMerchant().getId());
		transaction.setPaymentProvider(qpfsRequest.getPaymentProvider());
		transaction.setPilllrTransactionId(qpfsRequest.getTransactionId());
		transaction.setTransactionType(QPFS);
        transaction.setStatus("PENDING");
		
		order.setTransaction(transaction);
	}


	protected void populateCustomer(Customer customerDTO, com.pilllr.ecommerce.core.domain.Order order) {
		com.pilllr.ecommerce.core.domain.Customer customer = new com.pilllr.ecommerce.core.domain.Customer();
		
		customer.setDateOfBirth(customerDTO.getBirthdate());
		customer.setEmailAddress(customerDTO.getEmail());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setLoyaltyCardNumber(customerDTO.getLoyaltyCardNumber());
		customer.setTitle(customerDTO.getTitle());
		
		order.setCustomer(customer);
	}

	protected void populateShippingAddress(com.pilllr.ecommerce.core.domain.Order order, Customer customerDTO) {
		com.pilllr.ecommerce.core.domain.Customer customer = order.getCustomer();
		Collection<CustomerAddress> customerAddresses = customer.getCustomerAddresses();
		if(customerAddresses == null) {
			customerAddresses = new ArrayList<CustomerAddress>();
			customer.setCustomerAddresses(customerAddresses);
		}
		
		
		com.pilllr.persistence.domain.Address shippingDTO = customerDTO.getAddress();

		Address shippingAddress = new Address();
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

	protected void populateBillingAddress(com.pilllr.ecommerce.core.domain.Order order, com.pilllr.persistence.domain.Address billingAddrDTO) {
		com.pilllr.ecommerce.core.domain.Customer customer = order.getCustomer();
		Collection<CustomerAddress> customerAddresses = customer.getCustomerAddresses();
		if(customerAddresses == null) {
			customerAddresses = new ArrayList<CustomerAddress>();
		}
		
		Address billingAddress = new Address();
		
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
	
	protected void populateProductsInOrder(List<Product> productsDTO, com.pilllr.ecommerce.core.domain.Order order) {
		Set<com.pilllr.ecommerce.core.domain.Product> productList = new HashSet<com.pilllr.ecommerce.core.domain.Product>();
		
		for (Product productDTO : productsDTO) {
			com.pilllr.ecommerce.core.domain.Product product = new com.pilllr.ecommerce.core.domain.Product();
			
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
