package com.emrubik.springcloud.api.configuration;

import com.emrubik.springcloud.api.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.emrubik.springcloud.api")//扫描包下的所有component
@EnableFeignClients("com.emrubik.springcloud.api")//声明可以使用包下的所有feign接口
public class FeignApiAutoConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }


}
