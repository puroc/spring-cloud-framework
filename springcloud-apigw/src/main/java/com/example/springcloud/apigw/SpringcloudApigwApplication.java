package com.example.springcloud.apigw;

import com.emrubik.springcloud.api.annotation.EnableFeignApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableFeignApi
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudApigwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudApigwApplication.class, args);
	}


}
