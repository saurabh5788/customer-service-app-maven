package com.ecommerce.customerserviceappmaven.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.customerserviceappmaven.dto.CustomerDTO;
import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;
import com.ecommerce.customerserviceappmaven.service.CustomerService;
import com.ecommerce.customerserviceappmaven.util.CustomerCache;

@RestController
@RequestMapping("/customerservice")
public class CustomerServiceAppController {
	// @Value("${eureka.instance.instance-id}")
	// private String instanceId;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerServiceAppController.class);
	@Autowired
	private CustomerService customerService;
	private CustomerCache customerCache;

	@Autowired
	@Required
	@Qualifier(value = "customerCache")
	public void setCustomerCache(CustomerCache customerCache) {
		this.customerCache = customerCache;
		LOGGER.info("Customer Cache Injected!!!");
	}

	/*
	 * curl -v http://localhost:8080/customerservice/1
	 */
	@GetMapping(value = "/{id}")
	public @ResponseBody
	ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
		// LOGGER.info("Instance ID : {}", instanceId);
		CustomerEntity customerEntity = customerService.getCustomer(id);
		if (customerEntity == null) {
			return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
		} else {
			LOGGER.info("Customer Entity : {}", customerEntity);
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setName(customerEntity.getName());
			customerDTO.setId(customerEntity.getId());
			customerDTO.setEmail(customerEntity.getEmail());
			Map<String, String> prefixCodes = customerCache.getPrefixCodes();
			if (prefixCodes != null) {
				String namePrefix = prefixCodes.get(customerEntity
						.getCustomerNamePrefixEntity().getPrefixCode());
				customerDTO.setNamePrefix(namePrefix);
			}
			else{
				LOGGER.warn("Prefix Codes Not Loaded!!!");
			}
			LOGGER.info("Customer DTO : {}", customerDTO);
			return new ResponseEntity<CustomerDTO>(customerDTO,
					HttpStatus.FOUND);
		}

	}

	/*
	 * curl -d
	 * "{\"titleCode\":\"mr\",\"name\":\"Ankit\",\"email\":\"ank@gmail.com\"}"
	 * http://localhost:8080/customerservice -H "Content-Type: application/json"
	 */
	@PostMapping
	public ResponseEntity<Void> addCustomer(
			@RequestBody CustomerEntity customer, UriComponentsBuilder ucBuilder) {
		if (customer != null) {
			LOGGER.info("Customer Name To Be Added \n", customer);
			customerService.saveCustomer(customer);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/customerservice/{id}")
					.buildAndExpand(customer.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {
			LOGGER.info("Customer To Be Added is null");
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}
}
