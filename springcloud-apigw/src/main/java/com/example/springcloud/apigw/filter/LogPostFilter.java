package com.example.springcloud.apigw.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class LogPostFilter extends ZuulFilter {

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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        System.out.println(requestContext.getRequest().toString());
        return null;
    }
}
