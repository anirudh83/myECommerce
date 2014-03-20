package com.mec.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.mec.ecommerce.core.domain.Order;
import com.mec.ecommerce.core.persistence.OrderPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class OrderPersistenceImpl extends GenericDAOImpl<Order, Long> implements OrderPersistence {
}
