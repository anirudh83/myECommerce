package com.pilllr.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.pilllr.ecommerce.core.domain.ProductCategory;
import com.pilllr.ecommerce.core.persistence.ProductCategoryPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class ProductCategoryPersistenceImpl extends GenericDAOImpl<ProductCategory, Long> implements ProductCategoryPersistence {
}
