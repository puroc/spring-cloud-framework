package com.emrubik.springcloud.api.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.springframework.cloud.openfeign")
@ComponentScan("com.emrubik.springcloud.api")//扫描包下的所有component
@EnableFeignClients("com.emrubik.springcloud.api")//声明可以使用包下的所有feign接口
public class FeignApiAutoConfig {

}
