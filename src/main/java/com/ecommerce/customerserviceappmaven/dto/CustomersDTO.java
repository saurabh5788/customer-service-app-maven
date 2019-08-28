package com.ecommerce.customerserviceappmaven.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("customers")
public class CustomersDTO extends AbstractObject {
	@JsonProperty("customers")
	private List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();

	public List<CustomerDTO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerDTO> customerList) {
		this.customerList = customerList;
	}
}
