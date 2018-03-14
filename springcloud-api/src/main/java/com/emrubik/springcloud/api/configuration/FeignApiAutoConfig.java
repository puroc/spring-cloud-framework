package com.emrubik.springcloud.api.configuration;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan("com.emrubik.springcloud.api")//扫描包下的所有component
@EnableFeignClients("com.emrubik.springcloud.api")//声明可以使用包下的所有feign接口
public class FeignApiAutoConfig {

}
