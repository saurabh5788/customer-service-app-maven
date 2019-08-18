package com.ecommerce.customerserviceappmaven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.ecommerce.customerserviceappmaven.entity.CustomerEntity;
import com.ecommerce.customerserviceappmaven.entity.CustomerNamePrefixEntity;

@SpringBootApplication
public class CustomerServiceAppMavenApplication implements ApplicationRunner,
		CommandLineRunner {
	@Value("${spring.application.name}")
	private String applicationName;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerServiceAppMavenApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceAppMavenApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		LOGGER.info("Application started with arguments:"
				+ arg0.getOptionNames());
		LOGGER.info("ApplicationRunner - {}", applicationName);
	}

	@Override
	public void run(String... arg0) throws Exception {
		LOGGER.info("Command Line Arguments");
		int argIndex = 0;
		for (String arg : arg0) {
			LOGGER.info("Arg [" + argIndex + "] : " + arg);
		}
	}

	@Bean(name = "dummyCustomer")
	@DependsOn(value = { "blankCustomer", "dummyCustomerNamePrefix" })
	public CustomerEntity getDummyCustomerEntity(
			@Autowired @Qualifier("blankCustomer") CustomerEntity dummyCustomer,
			@Autowired @Qualifier("dummyCustomerNamePrefix") CustomerNamePrefixEntity dummyCustomerNamePrefix) {
		dummyCustomer.setId(123L);
		dummyCustomer.setName("Saurabh");
		dummyCustomer.setEmail("xyz@gma.com");
		dummyCustomer.setCustomerNamePrefixEntity(dummyCustomerNamePrefix);
		LOGGER.info("Dummy Customer Bean Instantiated!!!");
		return dummyCustomer;
	}

	@Bean(name = "blankCustomer")
	public CustomerEntity getBlankCustomerEntity() {
		LOGGER.info("Blank Customer Bean Instantiated!!!");
		return new CustomerEntity();
	}

	@Bean(name = "dummyCustomerNamePrefix")
	public CustomerNamePrefixEntity getCustomerNamePrefixEntity() {
		CustomerNamePrefixEntity customerNamePrefixEntity = new CustomerNamePrefixEntity();
		customerNamePrefixEntity.setName("Mr");
		customerNamePrefixEntity.setPrefixCode("mr");
		LOGGER.info("Dummy Customer Name Prefix Bean Instantiated!!!");
		return customerNamePrefixEntity;
	}

}
