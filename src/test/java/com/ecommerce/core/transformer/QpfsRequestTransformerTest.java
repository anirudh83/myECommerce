package com.ecommerce.core.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.ecommerce.core.domain.CustomerAddress;
import com.ecommerce.core.domain.Order;
import com.ecommerce.core.domain.Transaction;
import com.mec.persistence.domain.Address;
import com.mec.persistence.domain.CreditCard;
import com.mec.persistence.domain.Customer;
import com.mec.persistence.domain.Merchant;
import com.mec.persistence.domain.Product;
import com.mec.persistence.domain.QpfsRequest;
import com.mec.persistence.domain.QpfsTransaction;

public class QpfsRequestTransformerTest {

	private QpfsRequestTransformer qpfsRequestTransformer = new QpfsRequestTransformer();
	
	@Test
	public void testShouldGetOrderFromQpfsRequest() {
		QpfsRequest qpfsRequest = new QpfsRequest();
		QpfsTransaction transaction = new QpfsTransaction();
		transaction.setQpfsCreditCardRequest(getCreditcardDTO());
		Merchant merchant = new Merchant();
		merchant.setId(1l);
		qpfsRequest.setMerchant(merchant);
		qpfsRequest.setPaymentProvider("Web pay");
		qpfsRequest.setTransactionId("Transaction-1");
		
		Customer customerDTO = new Customer();
		customerDTO.setBirthdate("25/07/1985");
		customerDTO.setEmail("test@pantha.com");
		customerDTO.setFirstName("test");
		customerDTO.setLastName("me");
		customerDTO.setLoyaltyCardNumber("loyalty-1");
		customerDTO.setTitle("Mr.");
		
		Address address = new Address();
		address.setAddress1("The Corso");
		address.setAddress2("line2");
		address.setCity("Sydney");
		address.setCountry("Australia");
		address.setPostalCode("syd123");
		address.setState("NSW");
		
		customerDTO.setAddress(address );
		
		transaction.setCustomer(customerDTO);
		
		Address billingAddrDTO = new Address();
		billingAddrDTO.setAddress1("The Corso");
		billingAddrDTO.setAddress2("line2");
		billingAddrDTO.setCity("Sydney");
		billingAddrDTO.setCountry("Australia");
		billingAddrDTO.setPostalCode("syd123");
		billingAddrDTO.setState("NSW");
		
		transaction.setBillingAddress(billingAddrDTO);
		
		transaction.setProducts(getProductDTOList());
		
		com.mec.persistence.domain.Order orderDTO = new com.mec.persistence.domain.Order();
		orderDTO.setOrderDate("18/02/2014");
		orderDTO.setOrderId("Order-1");
		orderDTO.setOrderType("online_purchase");
		orderDTO.setQuantity("1");
		transaction.setOrder(orderDTO);
		
		qpfsRequest.setTransaction(transaction);
		
		Order order = qpfsRequestTransformer.getOrder(qpfsRequest);
		
		assertNotNull(order);
		
		com.ecommerce.core.domain.CreditCard creditCard = order.getCreditCard();
		assertNotNull(creditCard);
		assertEquals("easy", creditCard.getCaptureMethod());
		assertEquals("Citibank", creditCard.getCardReference());
		assertEquals("Master", creditCard.getCardType());
		
		Transaction transaction2 = order.getTransaction();
		assertNotNull(transaction);
		assertEquals(Long.valueOf(1l), transaction2.getMerchantId());
		assertEquals("Web pay", transaction2.getPaymentProvider());
		assertEquals("Transaction-1", transaction2.getPilllrTransactionId());
		assertEquals("QPFS", transaction2.getTransactionType());
			
		com.ecommerce.core.domain.Customer customer = order.getCustomer();
		assertEquals("25/07/1985", customer.getDateOfBirth());
		assertEquals("test@pantha.com", customer.getEmailAddress());
		assertEquals("test", customer.getFirstName());
		assertEquals("me", customer.getLastName());
		assertEquals("loyalty-1", customer.getLoyaltyCardNumber());
		assertEquals("Mr.", customer.getTitle());
		
		Collection<CustomerAddress> customerAddresses = order.getCustomer().getCustomerAddresses();
		assertNotNull(customerAddresses);
		
		assertEquals(2, customerAddresses.size());
		Set<com.ecommerce.core.domain.Product> products = order.getProducts();
		assertNotNull(products);
		assertEquals(2, products.size());
	}
	
	
	@Test
	public void testShouldPopulateCreditCardDetails() {
		
		Order order = new Order();
		CreditCard qpfsCreditCard = getCreditcardDTO();
		qpfsRequestTransformer.populateCreditCard(order, qpfsCreditCard);
		
		com.ecommerce.core.domain.CreditCard creditCard = order.getCreditCard();
		assertNotNull(creditCard);
		assertEquals("easy", creditCard.getCaptureMethod());
		assertEquals("Citibank", creditCard.getCardReference());
		assertEquals("Master", creditCard.getCardType());
		
	}


	private CreditCard getCreditcardDTO() {
		CreditCard qpfsCreditCard = new CreditCard();
		qpfsCreditCard.setCaptureMethod("easy");
		qpfsCreditCard.setCardReference("Citibank");
		qpfsCreditCard.setCardType("Master");
		return qpfsCreditCard;
	}
	
	@Test
	public void testShouldPopulateTransaction() {
		Order order = new Order();
		QpfsRequest qpfsRequest = new QpfsRequest();
		
		Merchant merchant = new Merchant();
		merchant.setId(1l);
		qpfsRequest.setMerchant(merchant);
		qpfsRequest.setPaymentProvider("Web pay");
		qpfsRequest.setTransactionId("Transaction-1");
		
		qpfsRequestTransformer.populateTransaction(order, qpfsRequest);
		
		Transaction transaction = order.getTransaction();
		
		
		assertNotNull(transaction);
		assertEquals(Long.valueOf(1l), transaction.getMerchantId());
		assertEquals("Web pay", transaction.getPaymentProvider());
		assertEquals("Transaction-1", transaction.getPilllrTransactionId());
		assertEquals("QPFS", transaction.getTransactionType());
	}
	
	@Test
	public void testShouldPopulateCustomer() {
		Order order = new Order();
		Customer customerDTO = new Customer();
		customerDTO.setBirthdate("25/07/1985");
		customerDTO.setEmail("test@pantha.com");
		customerDTO.setFirstName("test");
		customerDTO.setLastName("me");
		customerDTO.setLoyaltyCardNumber("loyalty-1");
		customerDTO.setTitle("Mr.");
		
		qpfsRequestTransformer.populateCustomer(customerDTO, order );
		
		com.ecommerce.core.domain.Customer customer = order.getCustomer();
		
		assertNotNull(customer);
		assertEquals("25/07/1985", customer.getDateOfBirth());
		assertEquals("test@pantha.com", customer.getEmailAddress());
		assertEquals("test", customer.getFirstName());
		assertEquals("me", customer.getLastName());
		assertEquals("loyalty-1", customer.getLoyaltyCardNumber());
		assertEquals("Mr.", customer.getTitle());
		
	}
	
	@Test
	public void testShouldPopulateShippingAddress() {
		Order order = new Order();
		com.ecommerce.core.domain.Customer customer = new com.ecommerce.core.domain.Customer();
		order.setCustomer(customer);
		
		Customer customerDTO = new Customer();
		Address address = new Address();
		address.setAddress1("The Corso");
		address.setAddress2("line2");
		address.setCity("Sydney");
		address.setCountry("Australia");
		address.setPostalCode("syd123");
		address.setState("NSW");
		
		customerDTO.setAddress(address );
		
		qpfsRequestTransformer.populateShippingAddress(order, customerDTO);
		
		Collection<CustomerAddress> customerAddresses = order.getCustomer().getCustomerAddresses();
		
		assertNotNull(customerAddresses);
		assertFalse(customerAddresses.isEmpty());
		
		ArrayList<CustomerAddress> list = new ArrayList<CustomerAddress>(customerAddresses);
		CustomerAddress custAddress = list.get(0);
		assertNotNull(custAddress);
		assertEquals("shipping", custAddress.getAddressName());
		com.ecommerce.core.domain.Address shippingAddress = custAddress.getAddress();
		assertEquals("The Corso", shippingAddress.getAddressLine1());
		assertEquals("line2", shippingAddress.getAddressLine2());
		assertEquals("Sydney", shippingAddress.getCity());
		assertEquals("Australia", shippingAddress.getCountry());
		assertEquals("syd123", shippingAddress.getZip());
		assertEquals("NSW", shippingAddress.getState());
		
	}

	@Test
	public void testShouldPopulateBillingAddress() {
		Order order = new Order();
		com.ecommerce.core.domain.Customer customer = new com.ecommerce.core.domain.Customer();
		customer.setCustomerAddresses(new ArrayList<CustomerAddress>());
		order.setCustomer(customer);
		Address billingAddrDTO = new Address();
		billingAddrDTO.setAddress1("The Corso");
		billingAddrDTO.setAddress2("line2");
		billingAddrDTO.setCity("Sydney");
		billingAddrDTO.setCountry("Australia");
		billingAddrDTO.setPostalCode("syd123");
		billingAddrDTO.setState("NSW");
		
		qpfsRequestTransformer.populateBillingAddress(order, billingAddrDTO);
		
		Collection<CustomerAddress> customerAddresses = order.getCustomer().getCustomerAddresses();
		
		ArrayList<CustomerAddress> list = new ArrayList<CustomerAddress>(customerAddresses);
		CustomerAddress customerAddress = list.get(0);
		
		assertNotNull(customerAddress);
		assertEquals("billing", customerAddress.getAddressName());
		com.ecommerce.core.domain.Address billingAddress = customerAddress.getAddress();
		assertEquals("The Corso", billingAddress.getAddressLine1());
		assertEquals("line2", billingAddress.getAddressLine2());
		assertEquals("Sydney", billingAddress.getCity());
		assertEquals("Australia", billingAddress.getCountry());
		assertEquals("syd123", billingAddress.getZip());
		assertEquals("NSW", billingAddress.getState());
	}
	
	@Test
	public void testShouldPopulateProductsInOrder() {
		List<Product> productsDTO = getProductDTOList();
		Order order = new Order();
		
		qpfsRequestTransformer.populateProductsInOrder(productsDTO, order);
		
		Set<com.ecommerce.core.domain.Product> products = order.getProducts();
		
		assertNotNull(products);
		assertEquals(2, products.size());
	}

	private List<Product> getProductDTOList() {
		
		Product p1 = new Product();
		p1.setDescription("product1");
		p1.setPrice(Double.valueOf("12.34"));
		p1.setProductCategory("pc1");
		p1.setProductId(1234l);
		p1.setProductType("type1");
		p1.setUnit("1");
		
		Product p2 = new Product();
		p2.setDescription("product1");
		p2.setPrice(Double.valueOf("12.34"));
		p2.setProductCategory("pc1");
		p2.setProductId(1234l);
		p2.setProductType("type1");
		p2.setUnit("1");
		
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(p1);
		products.add(p2);
		
		return products;
	}
}
