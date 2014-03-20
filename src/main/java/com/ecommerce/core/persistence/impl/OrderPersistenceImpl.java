package com.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.ecommerce.core.domain.Order;
import com.ecommerce.core.persistence.OrderPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class OrderPersistenceImpl extends GenericDAOImpl<Order, Long> implements OrderPersistence {
}
