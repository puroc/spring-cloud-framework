package com.emrubik.springcloud.auth.server;


import com.emrubik.springcloud.api.annotation.EnableFeignApi;
import com.emrubik.springcloud.auth.common.annotation.EnableJwt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableJwt
@EnableFeignApi
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudAuthServerApplication.class, args);
    }
}
