package com.ecommerce.customerserviceappmaven.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.customerserviceappmaven.dto.CustomerDTO;
import com.ecommerce.customerserviceappmaven.dto.CustomersDTO;
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

	/*
	 * curl -v http://localhost:8080/customerservice/1
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
		CustomerDTO customer = customerService.getCustomer(id);
		LOGGER.info("Customer : {}", customer);
		if (customer != null) {
			return new ResponseEntity<CustomerDTO>(customer, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomersDTO> getCustomers() {
		CustomersDTO customers = customerService.getCustomers();
		LOGGER.info("Customers : {}", customers);
		return new ResponseEntity<CustomersDTO>(customers, HttpStatus.OK);
	}

	/*
	 * {"customer":{"prefix-cd":"dr","name":"Nishant","e-mail":"nishant@gmail.com"}}
	 */
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customer,
			UriComponentsBuilder ucBuilder) {
		if (customer != null) {
			LOGGER.info("Customer To Be Added : ", customer);
			System.out.println("Customer To Be Added : " + customer);
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
