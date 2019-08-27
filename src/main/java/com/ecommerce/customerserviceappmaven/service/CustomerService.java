package com.ecommerce.customerserviceappmaven.service;

import java.util.Map;

import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;

public interface CustomerService {
	CustomerEntity getCustomer(Long id);
	void saveCustomer(CustomerEntity customer);
	
	Map<String,String> getPrefixCodes();
}
