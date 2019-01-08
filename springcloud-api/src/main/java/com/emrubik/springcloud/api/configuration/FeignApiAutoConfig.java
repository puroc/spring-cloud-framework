package com.emrubik.springcloud.api.configuration;

import com.emrubik.springcloud.api.interceptor.FeignBasicAuthRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;

@ComponentScan("com.emrubik.springcloud.api")//扫描包下的所有component
@EnableFeignClients("com.emrubik.springcloud.api")//声明可以使用包下的所有feign接口
public class FeignApiAutoConfig {

//    @Bean
//    Request.Options requestOptions(ConfigurableEnvironment env) {
//        int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 6000);
//        int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 3000);
//
//        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
//    }

//    @Bean
//    public RequestContextListener requestContextListener(){
//        return new RequestContextListener();
//    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }


}
