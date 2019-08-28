package com.ecommerce.customerserviceappmaven.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.customerserviceappmaven.dao.CustomerDaoRepository;
import com.ecommerce.customerserviceappmaven.dto.CustomerDTO;
import com.ecommerce.customerserviceappmaven.dto.CustomersDTO;
import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;
import com.ecommerce.customerserviceappmaven.util.CustomerHelper;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerServiceImpl.class);
	@Autowired
	CustomerDaoRepository customerDao;	
	private CustomerHelper customerHelper;

	@Autowired
	@Required
	@Qualifier(value = "customerHelper")
	public void setCustomerHelper(CustomerHelper customerHelper) {
		this.customerHelper = customerHelper;
		LOGGER.info("Customer Helper Injected!!!");
	}
	
	@Override
	public CustomerDTO getCustomer(Long id) {
		CustomerEntity customerEntity = customerDao.findOne(id);
		CustomerDTO customerDTO = customerHelper.getCustomerDTO(customerEntity);
		return customerDTO;
	}
	@Override
	public void saveCustomer(CustomerDTO customer) {
		CustomerEntity customerEntity = customerHelper.getCustomerEntity(customer);
		customerDao.save(customerEntity);
		
	}
	
	@Override
	public CustomersDTO getCustomers() {
		List<CustomerEntity> customerEntities = customerDao.findAll();
		CustomersDTO customersDTO = customerHelper.getCustomersDTO(customerEntities);
		return customersDTO;
	}

	
}
