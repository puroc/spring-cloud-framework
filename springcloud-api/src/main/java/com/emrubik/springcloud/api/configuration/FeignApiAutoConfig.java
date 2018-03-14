package com.emrubik.springcloud.api.configuration;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableFeignClients("com.emrubik.springcloud.api")
public class FeignApiAutoConfig {

}
