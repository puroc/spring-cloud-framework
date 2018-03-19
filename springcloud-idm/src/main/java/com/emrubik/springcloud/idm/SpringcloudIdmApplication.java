package com.emrubik.springcloud.idm;

import com.emrubik.springcloud.common.annotation.EnableCommon;
import com.emrubik.springcloud.dao.annotation.EnableMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCommon
@EnableMybatis
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudIdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudIdmApplication.class, args);
	}
}
