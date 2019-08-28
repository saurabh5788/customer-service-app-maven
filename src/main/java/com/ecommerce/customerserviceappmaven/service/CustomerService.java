package com.ecommerce.customerserviceappmaven.service;

import java.util.Map;

import com.ecommerce.customerserviceappmaven.dto.CustomerDTO;
import com.ecommerce.customerserviceappmaven.dto.CustomersDTO;

public interface CustomerService {
	CustomerDTO getCustomer(Long id);
	void saveCustomer(CustomerDTO customer);
	
	CustomersDTO getCustomers();
}
