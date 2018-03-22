package com.emrubik.springcloud.common.interceptor;

import com.emrubik.springcloud.common.annotation.IgnoreJwtValidation;
import com.emrubik.springcloud.common.exception.JwtParseException;
import com.emrubik.springcloud.common.exception.JwtTokenNotExistException;
import com.emrubik.springcloud.common.util.BaseContextHandler;
import com.emrubik.springcloud.common.util.JwtHelper;
import com.emrubik.springcloud.domain.vo.JwtInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
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
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //类上是否有IgnoreJwtValidation注解，若有，则不对该类所有的方法进行jwt校验
        IgnoreJwtValidation annotation = handlerMethod.getBeanType().getAnnotation(IgnoreJwtValidation.class);
        if (annotation == null) {
            //当前调用的方法上是否有IgnoreJwtValidation注解，若有，则不对该方法进行jwt校验
            annotation = handlerMethod.getMethodAnnotation(IgnoreJwtValidation.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        //取消息头中的jwt token，若不存在，抛异常
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            throw new JwtTokenNotExistException("消息头中缺少jwt token");
        }
        //解析jwt token，若解析失败，抛异常
        JwtInfo jwtInfo = jwtHelper.getInfoFromToken(token);
        if (jwtInfo == null) {
            throw new JwtParseException("jwt token解析失败");
        }
        //将username和userid设置到上下文对象中，方便后续使用
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
