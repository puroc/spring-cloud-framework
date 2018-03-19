package com.emrubik.springcloud.samples;

import com.emrubik.springcloud.api.annotation.EnableFeignApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableFeignApi
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudSamplesApplication.class, args);
	}
}
