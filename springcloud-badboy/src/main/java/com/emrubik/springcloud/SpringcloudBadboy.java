package com.emrubik.springcloud;

import com.emrubik.springcloud.dao.annotation.EnableMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMybatis
@SpringBootApplication
public class SpringcloudBadboy {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudBadboy.class, args);
    }
}
