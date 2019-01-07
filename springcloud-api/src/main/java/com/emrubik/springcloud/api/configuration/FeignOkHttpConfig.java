//package com.emrubik.springcloud.api.configuration;
//
//import feign.Feign;
//import okhttp3.ConnectionPool;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.cloud.openfeign.FeignAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@ConditionalOnClass(Feign.class) //@ConditionalOnClass 判断是否有Feign.class
//@AutoConfigureBefore(FeignAutoConfiguration.class) //在配置FeignAutoConfiguration之前配置FeignOkHttpConfig这个类
//public class FeignOkHttpConfig {
//
//    @Autowired
//    OkHttpTokenInterceptor okHttpLoggingInterceptor;
//
//    @Bean
//    public okhttp3.OkHttpClient okHttpClient() {
//        return new okhttp3.OkHttpClient.Builder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(120, TimeUnit.SECONDS)
//                .connectionPool(new ConnectionPool())
//                .addInterceptor(okHttpLoggingInterceptor)
//                .build();
//    }
//
//
//}