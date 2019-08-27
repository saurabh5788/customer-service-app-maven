package com.ecommerce.customerserviceappmaven.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.customerserviceappmaven.entity.CustomerNamePrefixEntity;

@Repository
public interface CustomerNamePreifxDaoRepository extends
		JpaRepository<CustomerNamePrefixEntity, Long> {
}
