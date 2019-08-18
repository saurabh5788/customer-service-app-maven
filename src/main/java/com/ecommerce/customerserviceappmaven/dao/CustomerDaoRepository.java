package com.ecommerce.customerserviceappmaven.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;

@Repository
public interface CustomerDaoRepository extends JpaRepository<CustomerEntity, Long> {	
}
