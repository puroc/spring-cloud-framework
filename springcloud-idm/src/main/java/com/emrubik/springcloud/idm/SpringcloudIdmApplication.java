package com.emrubik.springcloud.idm;

import com.emrubik.springcloud.dao.annotation.EnableMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableMybatis //扫描java mapper类和mybatis配置
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudIdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudIdmApplication.class, args);
	}
}
