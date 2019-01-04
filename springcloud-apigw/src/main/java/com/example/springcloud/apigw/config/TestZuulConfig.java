package com.example.springcloud.apigw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestZuulConfig {

    @Autowired
    private ZuulConfig zuulConfig;

    @PostConstruct
    public void init() {
        System.out.println(zuulConfig.getPrefix());
    }
}
