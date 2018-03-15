package com.emrubik.springcloud.auth.server;


import com.emrubik.springcloud.api.annotation.EnableFeignApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableFeignApi
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudAuthServerApplication.class, args);
    }
}
