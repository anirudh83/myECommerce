package com.pilllr.ecommerce.core.service;

import com.pilllr.ecommerce.core.domain.ProductCategory;


/**
 * 
 * @author sunil
 *
 */
public interface ProductCategoryService {
	
	ProductCategory get(Long id);
	
	ProductCategory findByName(String name);

}
