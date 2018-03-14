package com.emrubik.springcloud.idm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudIdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudIdmApplication.class, args);
	}
}
