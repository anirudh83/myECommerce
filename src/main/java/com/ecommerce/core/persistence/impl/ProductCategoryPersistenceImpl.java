package com.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.ecommerce.core.domain.ProductCategory;
import com.ecommerce.core.persistence.ProductCategoryPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class ProductCategoryPersistenceImpl extends GenericDAOImpl<ProductCategory, Long> implements ProductCategoryPersistence {
}
