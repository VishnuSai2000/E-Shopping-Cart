package com.scart.apigatewayscart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayScartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayScartApplication.class, args);
	}

}
