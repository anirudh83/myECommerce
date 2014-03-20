package com.pilllr.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.pilllr.ecommerce.core.domain.Order;
import com.pilllr.ecommerce.core.persistence.OrderPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class OrderPersistenceImpl extends GenericDAOImpl<Order, Long> implements OrderPersistence {
}
