package com.emrubik.springcloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer  //zipkin服务器 默认使用http进行通信
public class SpringcloudZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudZipkinApplication.class, args);
    }
}
