package com.emrubik.springcloud.auth.common.annotation;

import com.emrubik.springcloud.auth.common.config.JwtAutoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(JwtAutoConfig.class)
@Documented
@Inherited
public @interface EnableJwt {
}
