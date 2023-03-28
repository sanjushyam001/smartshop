package com.smartshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages= {"com.smartshop.common.entity"})
public class SmartShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartShopBackendApplication.class, args);
	}

}
