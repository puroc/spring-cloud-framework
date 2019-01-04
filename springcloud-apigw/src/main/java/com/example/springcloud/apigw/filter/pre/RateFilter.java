package com.example.springcloud.apigw.filter.pre;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

@Component
public class RateFilter extends ZuulFilter {

    @Value("${zuul.permitsPerSecond}")
    private String permitsPerSecond;

    private RateLimiter rateLimiter;

    @PostConstruct
    public void init() {
        rateLimiter = RateLimiter.create(Integer.parseInt(permitsPerSecond));
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 如果请求最终不会被zuul转发到后面的微服务，则不执行当前filter的run方法
        if (!ctx.sendZuulResponse()) {
            return false;
        }
        //返回true，代表这个filter的run方法会被执行
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //如果没有取到令牌
        if (!rateLimiter.tryAcquire()) {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
            ctx.setResponseBody("over rate limit " + permitsPerSecond);
            //设置sendZuulResponse为false，代表这个请求不会被发送到后面的微服务
            ctx.setSendZuulResponse(false);
        }
        return null;
    }
}
