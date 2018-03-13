package com.emrubik.springcloud.idm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class SpringcloudIdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudIdmApplication.class, args);
	}
}
