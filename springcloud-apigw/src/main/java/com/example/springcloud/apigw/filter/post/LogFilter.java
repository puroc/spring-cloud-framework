package com.example.springcloud.apigw.filter.post;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class LogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //post filter，在prefilter之后执行，post filter可以有多个，按照filterOrder()定义的顺序执行
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 如果请求最终不会被zuul转发到后端服务器，则不执行当前filter的run方法
        if (!ctx.sendZuulResponse()) {
            return false;
        }
        //返回true，代表这个filter的run方法会被执行
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        System.out.println(requestContext.getRequest().toString());
        return null;
    }
}
