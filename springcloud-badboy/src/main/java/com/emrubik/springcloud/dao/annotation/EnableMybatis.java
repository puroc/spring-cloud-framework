package com.emrubik.springcloud.dao.annotation;

import com.emrubik.springcloud.dao.config.DruidConfiguration;
import com.emrubik.springcloud.dao.config.MybatisPlusConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({MybatisPlusConfig.class,DruidConfiguration.class})//导入MybatisPlusConfig配置
@Documented
@Inherited
public @interface EnableMybatis {
}
