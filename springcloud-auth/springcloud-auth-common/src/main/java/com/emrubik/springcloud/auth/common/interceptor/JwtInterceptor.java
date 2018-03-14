package com.emrubik.springcloud.auth.common.interceptor;

import com.emrubik.springcloud.auth.common.util.BaseContextHandler;
import com.emrubik.springcloud.auth.common.annotation.IgnoreJwt;
import com.emrubik.springcloud.auth.common.domain.JwtInfo;
import com.emrubik.springcloud.auth.common.util.JwtHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreJwt annotation = handlerMethod.getBeanType().getAnnotation(IgnoreJwt.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreJwt.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader(jwtHelper.getTokenHeader());
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(jwtHelper.getTokenHeader())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        JwtInfo jwtInfo = jwtHelper.getInfoFromToken(token);
        BaseContextHandler.setUserName(jwtInfo.getUsername());
        BaseContextHandler.setUserId(jwtInfo.getUserId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
