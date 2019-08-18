package com.ecommerce.customerserviceappmaven.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


@Entity
@Table(name = "CUSTOMER_NAME_PREFIX_CD")
public class CustomerNamePrefixEntity {
	@Id
	@Column(name = "PREFIX_CD", length = 2, nullable = false)
	private String prefixCode;
	@Column(name = "PREFIX_NAME", length = 5, nullable = false)
	private String name;
	
	public String getPrefixCode() {
		return prefixCode;
	}
	public void setPrefixCode(String prefixCode) {
		this.prefixCode = prefixCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
