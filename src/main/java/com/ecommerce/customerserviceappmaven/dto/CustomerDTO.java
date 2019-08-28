package com.ecommerce.customerserviceappmaven.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("customer")
@JsonPropertyOrder({ "id", "prefix", "name", "e-mail" })
public class CustomerDTO extends AbstractObject {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("e-mail")
	@JsonInclude(value = Include.NON_NULL)
	private String email;
	@JsonProperty(value = "prefix", access = Access.READ_ONLY)
	@JsonInclude(value = Include.NON_NULL)
	private String namePrefix;

	@JsonProperty(value = "prefix-cd", access = Access.WRITE_ONLY)
	private String namePrefixCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getNamePrefixCode() {
		return namePrefixCode;
	}

	public void setNamePrefixCode(String namePrefixCode) {
		this.namePrefixCode = namePrefixCode;
	}
}
