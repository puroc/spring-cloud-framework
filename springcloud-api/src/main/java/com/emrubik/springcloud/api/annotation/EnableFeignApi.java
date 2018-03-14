package com.emrubik.springcloud.api.annotation;

import com.emrubik.springcloud.api.configuration.FeignApiAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FeignApiAutoConfig.class)//导入FeignApiAutoConfig配置
@Documented
@Inherited
public @interface EnableFeignApi {
}
