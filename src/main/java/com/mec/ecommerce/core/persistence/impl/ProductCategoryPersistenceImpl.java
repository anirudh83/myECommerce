package com.mec.ecommerce.core.persistence.impl;

import org.springframework.stereotype.Repository;

import com.mec.ecommerce.core.domain.ProductCategory;
import com.mec.ecommerce.core.persistence.ProductCategoryPersistence;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class ProductCategoryPersistenceImpl extends GenericDAOImpl<ProductCategory, Long> implements ProductCategoryPersistence {
}
