package com.emrubik.springcloud.dao.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@ImportResource(locations = {"classpath:springcloud-dao-bean.xml"})
@Configuration
public class DruidConfiguration {


    @Bean
    public ServletRegistrationBean DruidStatViewServlet() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //白名单：
//        servletRegistrationBean.addInitParameter("allow", "192.168.1.20");
        //黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
//        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");

        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean("druidStatFilter")
    public StatFilter statFilter() {
        StatFilter sf = new StatFilter();
        sf.setSlowSqlMillis(200);
        sf.setLogSlowSql(true);
        return sf;
    }

    @Bean("druidLogFilter")
    public Slf4jLogFilter druidLogFilter(){
        Slf4jLogFilter filter = new Slf4jLogFilter();
        filter.setDataSourceLogEnabled(true);
        filter.setStatementExecutableSqlLogEnable(true);
//        filter.setResultSetLogEnabled(false);
//        filter.setConnectionLogEnabled(false);
//        filter.setStatementParameterClearLogEnable(false);
//        filter.setStatementCreateAfterLogEnabled(false);
//        filter.setStatementCloseAfterLogEnabled(false);
//        filter.setStatementParameterSetLogEnabled(false);
//        filter.setStatementPrepareAfterLogEnabled(false);
        return  filter;
    }


//    @Bean
//    public DruidStatInterceptor druidStatInterceptor(){
//        return new DruidStatInterceptor();
//    }
//
//    @Bean
//    @Scope("prototype")
//    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut(){
//        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
//        List<String> list = new ArrayList<String>();
//        list.add("com.emrubik.springcloud.*");
//        list.toArray(pointcut.getPatterns());
//        return pointcut;
//    }


}
