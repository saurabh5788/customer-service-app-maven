package com.ecommerce.customerserviceappmaven.util;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.customerserviceappmaven.CustomerServiceAppMavenApplication;
import com.ecommerce.customerserviceappmaven.dto.AbstractObject;
import com.ecommerce.customerserviceappmaven.service.CustomerService;

@Component
public class CustomerCache extends AbstractObject {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerCache.class);
	@Autowired
	private CustomerService customerService;
	private Map<String, String> prefixCodes;

	public Map<String, String> getPrefixCodes() {
		return prefixCodes;
	}

	public void setPrefixCodes(Map<String, String> prefixCodes) {
		this.prefixCodes = prefixCodes;
	}

	@PostConstruct
	public void initCustomerCache() {
		prefixCodes = customerService.getPrefixCodes();
		LOGGER.info("Customer Cache Initialized!!!");
	}
}
