package com.emrubik.springcloud.dao.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

@ImportResource(locations = {"classpath:springcloud-dao-bean.xml"})
@Configuration
public class DruidConfiguration {


    @Bean
    public ServletRegistrationBean druidServlet() {
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
    public FilterRegistrationBean druidWebFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public StatFilter druidStatFilter() {
        StatFilter sf = new StatFilter();
        sf.setSlowSqlMillis(3000);
        sf.setLogSlowSql(true);
        sf.setMergeSql(true);
        return sf;
    }

    @Bean
    public Slf4jLogFilter druidLogFilter(){
        Slf4jLogFilter filter = new Slf4jLogFilter();
        filter.setDataSourceLogEnabled(true);
        filter.setStatementExecutableSqlLogEnable(true);
        filter.setResultSetLogEnabled(false);
        filter.setConnectionLogEnabled(false);
        filter.setStatementParameterClearLogEnable(false);
        filter.setStatementCreateAfterLogEnabled(false);
        filter.setStatementCloseAfterLogEnabled(false);
        filter.setStatementParameterSetLogEnabled(false);
        filter.setStatementPrepareAfterLogEnabled(false);
        return  filter;
    }

    public class MyStatLogger extends DruidDataSourceStatLoggerAdapter implements DruidDataSourceStatLogger {
        private Logger logger = LoggerFactory.getLogger(MyStatLogger.class);

        @Override
        public void log(DruidDataSourceStatValue statValue) {
            logger.info("***************************************************");
            logger.info("                  Druid监控数据清空:"+statValue.getSqlList().size()+"                    ");
            logger.info("***************************************************");
        }
    }

    @Autowired
    public void setStatLogger(DataSource dataSource){
        ((DruidDataSource)dataSource).setTimeBetweenLogStatsMillis(15000);
        ((DruidDataSource)dataSource).setStatLogger(new MyStatLogger());
    }



}
