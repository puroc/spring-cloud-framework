package com.emrubik.springcloud.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringcloudAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudAuthServerApplication.class, args);
	}
}
