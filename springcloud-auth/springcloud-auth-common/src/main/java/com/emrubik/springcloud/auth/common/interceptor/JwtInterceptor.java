package com.emrubik.springcloud.auth.common.interceptor;

import com.emrubik.springcloud.auth.common.domain.JwtInfo;
import com.emrubik.springcloud.auth.common.util.BaseContextHandler;
import com.emrubik.springcloud.auth.common.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    public static final String JWT_HEADER = "Authorization";

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JWT_HEADER);
        JwtInfo jwtInfo = JwtHelper.getInfoFromToken(token);
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
