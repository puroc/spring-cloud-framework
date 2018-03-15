package com.emrubik.springcloud.auth.common.annotation;

import com.emrubik.springcloud.auth.common.configuration.AuthAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthAutoConfig.class)//导入AuthAutoConfig配置
@Documented
@Inherited
public @interface EnableJwt {
}
