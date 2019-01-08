package com.emrubik.springcloud.samples.config;


import com.emrubik.springcloud.api.interceptor.FeignRequestInterceptor;
import com.emrubik.springcloud.common.interceptor.ControllerInterceptor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;


@Configuration("sampleWebConfig")
@Primary
@Data
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ControllerInterceptor controllerInterceptor;

    /*
     * 添加拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getControllerInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        super.addInterceptors(registry);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }


    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/v2/api-docs",
                "/swagger-resources/**",
                "/cache/**",
                "/api/log/save",
                "/error"
        };
        Collections.addAll(list, urls);
        return list;
    }

}