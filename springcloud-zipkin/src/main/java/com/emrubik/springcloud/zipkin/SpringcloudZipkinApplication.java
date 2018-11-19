package com.emrubik.springcloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import zipkin.server.EnableZipkinServer;
import zipkin.storage.mysql.MySQLStorage;
import javax.sql.DataSource;

@SpringBootApplication
@EnableZipkinServer  //zipkin服务器 默认使用http进行通信
public class SpringcloudZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudZipkinApplication.class, args);
    }


    @Bean
    @Primary
    public MySQLStorage mySQLStorage(DataSource datasource) {
        return MySQLStorage.builder().datasource(datasource).executor(Runnable::run).build();
    }

}
