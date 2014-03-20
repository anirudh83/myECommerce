package com.mec.ecommerce.core.service;

import com.mec.ecommerce.core.domain.ProductCategory;


/**
 * 
 * @author sunil
 *
 */
public interface ProductCategoryService {
	
	ProductCategory get(Long id);
	
	ProductCategory findByName(String name);

}
