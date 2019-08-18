package com.ecommerce.customerserviceappmaven.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {
	public CustomerEntity(CustomerEntity customerEntity) {
		id = customerEntity.id;
		name = customerEntity.name;
		email = customerEntity.email;
		customerNamePrefixEntity = customerEntity.customerNamePrefixEntity;
	}

	public CustomerEntity() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;
	@Column(name = "CUSTOMER_NAME", length = 64, nullable = false)
	private String name;
	@Column(name = "EMAIL", length = 64, nullable = false)
	private String email;
	@ManyToOne
	@JoinColumn(name = "PREFIX_CD")
	private CustomerNamePrefixEntity customerNamePrefixEntity;

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

	public CustomerNamePrefixEntity getCustomerNamePrefixEntity() {
		return customerNamePrefixEntity;
	}

	public void setCustomerNamePrefixEntity(
			CustomerNamePrefixEntity customerNamePrefixEntity) {
		this.customerNamePrefixEntity = customerNamePrefixEntity;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
