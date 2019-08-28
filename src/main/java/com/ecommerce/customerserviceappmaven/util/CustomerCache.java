package com.ecommerce.customerserviceappmaven.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ecommerce.customerserviceappmaven.dao.CustomerNamePreifxDaoRepository;
import com.ecommerce.customerserviceappmaven.dto.AbstractObject;
import com.ecommerce.customerserviceappmaven.entity.CustomerNamePrefixEntity;

@Component
public class CustomerCache extends AbstractObject {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerCache.class);
	@Autowired
	private CustomerNamePreifxDaoRepository customerNamePreifxDao;
	private Map<String, String> prefixCodes = new HashMap<String,String>();

	public Map<String, String> getPrefixCodes() {
		return prefixCodes;
	}

	public void setPrefixCodes(Map<String, String> prefixCodes) {
		this.prefixCodes = prefixCodes;
	}

	@PostConstruct
	public void initCustomerCache() {
		loadNamePrefixCodes();
	}
	
	private void loadNamePrefixCodes(){
		List<CustomerNamePrefixEntity> customerPrefixList = customerNamePreifxDao
				.findAll();
		if (!CollectionUtils.isEmpty(customerPrefixList)) {
			for (CustomerNamePrefixEntity customerNamePrefixEntity : customerPrefixList) {
				prefixCodes.put(customerNamePrefixEntity.getPrefixCode(),
						customerNamePrefixEntity.getName());
			}
		}
		LOGGER.info("Customer Cache Initialized!!!");
	}
}
