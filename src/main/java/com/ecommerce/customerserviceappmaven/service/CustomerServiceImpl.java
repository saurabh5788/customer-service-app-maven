package com.ecommerce.customerserviceappmaven.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ecommerce.customerserviceappmaven.dao.CustomerDaoRepository;
import com.ecommerce.customerserviceappmaven.dao.CustomerNamePreifxDaoRepository;
import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;
import com.ecommerce.customerserviceappmaven.entity.CustomerNamePrefixEntity;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDaoRepository customerDao;
	@Autowired
	CustomerNamePreifxDaoRepository customerNamePreifxDao;

	@Override
	public CustomerEntity getCustomer(Long id) {
		return customerDao.findOne(id);
	}

	@Override
	public void saveCustomer(CustomerEntity customer) {
		customerDao.save(customer);
	}

	@Override
	public Map<String, String> getPrefixCodes() {
		Map<String, String> prefixMap = new HashMap<String, String>();
		List<CustomerNamePrefixEntity> customerPrefixList = customerNamePreifxDao
				.findAll();
		if (!CollectionUtils.isEmpty(customerPrefixList)) {
			for (CustomerNamePrefixEntity customerNamePrefixEntity : customerPrefixList) {
				prefixMap.put(customerNamePrefixEntity.getPrefixCode(),
						customerNamePrefixEntity.getName());
			}
		}

		return prefixMap;
	}
}
