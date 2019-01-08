package com.emrubik.springcloud.api.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        // 如果hystrix使用的是thread模式，他会用自己的线程池去发送请求，需要在这步之前执行RequestContextHolder.setRequestAttributes(attributes, true)，将attributes设置到子线程中
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        //将请求中的头信息添加到feign发送的请求中
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }

        Enumeration<String> bodyNames = request.getParameterNames();
        StringBuffer body = new StringBuffer();
        if (bodyNames != null) {
            while (bodyNames.hasMoreElements()) {
                String name = bodyNames.nextElement();
                String values = request.getParameter(name);
                body.append(name).append("=").append(values).append("&");
            }
        }
        if (body.length() != 0) {
            body.deleteCharAt(body.length() - 1);
            requestTemplate.body(body.toString());
            log.info("feign interceptor body:{}", body.toString());
        }
    }
}