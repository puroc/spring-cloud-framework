package com.emrubik.springcloud.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringcloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudAdminApplication.class, args);
    }
}
