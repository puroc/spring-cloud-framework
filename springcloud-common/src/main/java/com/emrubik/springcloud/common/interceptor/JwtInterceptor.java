package com.emrubik.springcloud.common.interceptor;

import com.emrubik.springcloud.common.util.BaseContextHandler;
import com.emrubik.springcloud.common.util.JwtHelper;
import com.emrubik.springcloud.domain.vo.JwtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    public static final String TOKEN_HEADER = "Authorization";

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN_HEADER);
        JwtInfo jwtInfo = jwtHelper.getInfoFromToken(token);
        BaseContextHandler.setUserName(jwtInfo.getUserName());
        BaseContextHandler.setUserId(jwtInfo.getUserId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
