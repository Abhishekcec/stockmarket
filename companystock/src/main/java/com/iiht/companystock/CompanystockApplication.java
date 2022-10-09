package com.iiht.companystock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class CompanystockApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanystockApplication.class, args);
	}

}
