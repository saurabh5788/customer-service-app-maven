package com.ecommerce.customerserviceappmaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.customerserviceappmaven.dao.CustomerDaoRepository;
import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDaoRepository customerDao;

	@Override
	public CustomerEntity getCustomer(Long id) {
		return customerDao.findOne(id);
	}

	@Override
	public void saveCustomer(CustomerEntity customer) {
		customerDao.save(customer);
	}

}
