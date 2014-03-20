package com.ecommerce.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.core.domain.ProductCategory;
import com.ecommerce.core.persistence.ProductCategoryPersistence;
import com.ecommerce.core.service.ProductCategoryService;

/**
 * 
 * @author sunil
 *
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryPersistence productCategoryPersistence;

	@Override
	public ProductCategory get(Long id) {
		return productCategoryPersistence.findById(id);
	}

	@Override
	public ProductCategory findByName(String name) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName(name);
		List<ProductCategory> productCategoryList = productCategoryPersistence.findByExample(productCategory);
		if(productCategoryList != null && productCategoryList.size() > 0) {
			return productCategoryList.get(0);
		}
		
		return null;
	}
   
}
