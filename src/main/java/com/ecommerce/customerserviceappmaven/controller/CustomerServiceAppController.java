package com.ecommerce.customerserviceappmaven.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;
import com.ecommerce.customerserviceappmaven.service.CustomerService;

@RestController
@RequestMapping("/customerservice")
public class CustomerServiceAppController {
	// @Value("${eureka.instance.instance-id}")
	// private String instanceId;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerServiceAppController.class);
	@Autowired
	private CustomerService customerService;
	private CustomerEntity customerBlank;
	private CustomerEntity customerDummy;

	@Autowired
	@Qualifier(value = "dummyCustomer")
	public void setCustomerDummy(CustomerEntity customerEntity) {
		this.customerDummy = customerEntity;
		LOGGER.info("Dummy Customer Injected!!!");
	}

	@Autowired
	@Qualifier(value = "blankCustomer")
	public void setCustomerBlank(CustomerEntity customerBlank) {
		this.customerBlank = customerBlank;
		LOGGER.info("Blank Customer Injected!!!");
	}

	@GetMapping
	public @ResponseBody
	ResponseEntity<CustomerEntity> getCustomer() {
		return new ResponseEntity<CustomerEntity>(customerDummy, HttpStatus.OK);
	}

	/*
	 * curl -v http://localhost:8080/customerservice/1
	 */
	@GetMapping(value = "/{id}")
	public @ResponseBody
	ResponseEntity<CustomerEntity> getCustomer(@PathVariable Long id) {
		// LOGGER.info("Instance ID : {}", instanceId);
		CustomerEntity customer = customerService.getCustomer(id);
		if (customer == null) {
			return new ResponseEntity<CustomerEntity>(customerBlank,
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomerEntity>(customer, HttpStatus.FOUND);
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
			headers.setLocation(ucBuilder
					.path("/customerservice/{id}")
					.buildAndExpand(customer.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {
			LOGGER.info("Customer To Be Added is null");
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}
}
