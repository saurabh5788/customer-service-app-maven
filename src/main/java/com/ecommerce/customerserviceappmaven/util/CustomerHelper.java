package com.ecommerce.customerserviceappmaven.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ecommerce.customerserviceappmaven.dto.CustomerDTO;
import com.ecommerce.customerserviceappmaven.dto.CustomersDTO;
import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;
import com.ecommerce.customerserviceappmaven.entity.CustomerNamePrefixEntity;

@Component
public class CustomerHelper {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerHelper.class);
	private CustomerCache customerCache;

	@Autowired
	@Required
	@Qualifier(value = "customerCache")
	public void setCustomerCache(CustomerCache customerCache) {
		this.customerCache = customerCache;
		LOGGER.info("Customer Cache Injected!!!");
	}

	public CustomerDTO getCustomerDTO(CustomerEntity customerEntity) {
		CustomerDTO customerDTO = null;
		if (customerEntity != null) {
			customerDTO = new CustomerDTO();
			customerDTO.setName(customerEntity.getName());
			customerDTO.setId(customerEntity.getId());
			customerDTO.setEmail(customerEntity.getEmail());
			Map<String, String> prefixCodes = customerCache.getPrefixCodes();
			if (prefixCodes != null && !prefixCodes.isEmpty()) {
				String namePrefix = prefixCodes.get(customerEntity
						.getCustomerNamePrefixEntity().getPrefixCode());
				customerDTO.setNamePrefix(namePrefix);
			} else {
				LOGGER.warn("Prefix Codes Not Found!!!");
			}
		}
		return customerDTO;
	}
	
	public CustomerEntity getCustomerEntity(CustomerDTO customerDTO) {
		CustomerEntity customerEntity = null;
		if (customerDTO != null) {
			customerEntity = new CustomerEntity();
			customerEntity.setName(customerDTO.getName());
			customerEntity.setEmail(customerDTO.getEmail());
			customerEntity.setId(customerDTO.getId());
			CustomerNamePrefixEntity customerNamePrefixEntity = new CustomerNamePrefixEntity();
			customerNamePrefixEntity.setPrefixCode(customerDTO
					.getNamePrefixCode());
			customerEntity
					.setCustomerNamePrefixEntity(customerNamePrefixEntity);
		}
		LOGGER.info("Customer Entity Created : {}" , customerEntity);
		return customerEntity;
	}

	public CustomersDTO getCustomersDTO(List<CustomerEntity> customerEntities) {
		CustomersDTO customersDTO = new CustomersDTO();
		if (!CollectionUtils.isEmpty(customerEntities)) {
			for (CustomerEntity customerEntity : customerEntities) {
				customersDTO.getCustomerList().add(
						getCustomerDTO(customerEntity));
			}
		}
		return customersDTO;
	}
}
