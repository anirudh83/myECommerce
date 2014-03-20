package com.ecommerce.core.service;

import com.ecommerce.core.domain.ProductCategory;


/**
 * 
 * @author sunil
 *
 */
public interface ProductCategoryService {
	
	ProductCategory get(Long id);
	
	ProductCategory findByName(String name);

}
