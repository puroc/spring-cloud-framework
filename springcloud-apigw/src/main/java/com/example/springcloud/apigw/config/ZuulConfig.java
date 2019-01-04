package com.example.springcloud.apigw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("zuul")
public class ZuulConfig {

    private String prefix = "";
}
