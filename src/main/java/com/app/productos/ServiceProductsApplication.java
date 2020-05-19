package com.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.app.commons.models.entity"})
public class ServiceProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductsApplication.class, args);
	}

}
