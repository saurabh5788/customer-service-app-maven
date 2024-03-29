package com.ecommerce.customerserviceappmaven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

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

	/*@Bean(name = "customerCache")
	@DependsOn(value = "customerService")
	public CustomerCache getCustomerCache(
			@Autowired @Qualifier("customerService") CustomerService customerService) {
		Map<String, String> prefixCodes = customerService.getPrefixCodes();
		CustomerCache customerCache = new CustomerCache();
		customerCache.setPrefixCodes(prefixCodes);
		LOGGER.info("Customer Cache Initialized!!!");
		return customerCache;
	}*/
	
	@Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE); // enables wrapping for root elements
        builder.featuresToEnable(DeserializationFeature.UNWRAP_ROOT_VALUE); // enables wrapping for root elements
        return builder;
    }
}
