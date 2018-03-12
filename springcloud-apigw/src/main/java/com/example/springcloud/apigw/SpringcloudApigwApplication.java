package com.example.springcloud.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@EnableZuulProxy
@EnableScheduling
@SpringBootApplication
public class SpringcloudApigwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudApigwApplication.class, args);
	}
}
