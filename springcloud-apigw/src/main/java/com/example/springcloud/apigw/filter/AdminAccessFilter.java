package com.example.springcloud.apigw.filter;

import com.emrubik.springcloud.api.idm.IUserService;
import com.emrubik.springcloud.common.util.BaseContextHandler;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class AdminAccessFilter extends ZuulFilter {
    @Autowired
    @Lazy
    private IUserService userService;

    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Value("${zuul.prefix}")
    private String zuulPrefix;

    @Override
    public String filterType() {
        //pre filter，最先执行，pre filter可以有多个，按照filterOrder()定义的顺序执行
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        //返回true，代表这个filter的run方法会被调用
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        final String method = request.getMethod();
        BaseContextHandler.setToken(null);
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            return null;
        }
//        IJWTInfo user = null;
//        try {
//            user = getJWTUser(request, ctx);
//        } catch (Exception e) {
//            setFailedRequest(JSON.toJSONString(new TokenErrorResponse(e.getMessage())), 200);
//            return null;
//        }
//        List<PermissionInfo> permissionIfs = userService.getAllPermissionInfo();
//        // 判断资源是否启用权限约束
//        Stream<PermissionInfo> stream = getPermissionIfs(requestUri, method, permissionIfs);
//        List<PermissionInfo> result = stream.collect(Collectors.toList());
//        PermissionInfo[] permissions = result.toArray(new PermissionInfo[]{});
//        if (permissions.length > 0) {
//            checkUserPermission(permissions, ctx, user);
//        }
//        BaseContextHandler.remove();
        //鉴权不过时回401
//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        return null;
    }

//    /**
//     * 获取目标权限资源
//     *
//     * @param requestUri
//     * @param method
//     * @param serviceInfo
//     * @return
//     */
//    private Stream<PermissionInfo> getPermissionIfs(final String requestUri, final String method, List<PermissionInfo> serviceInfo) {
//        return serviceInfo.parallelStream().filter(new Predicate<PermissionInfo>() {
//            @Override
//            public boolean test(PermissionInfo permissionInfo) {
//                String url = permissionInfo.getUri();
//                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
//                String regEx = "^" + uri + "$";
//                return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
//                        && method.equals(permissionInfo.getMethod());
//            }
//        });
//    }
//
//    private void setCurrentUserInfo(RequestContext ctx, JwtInfo user, PermissionInfo pm) {
//        ctx.addZuulRequestHeader("userId", user.getUserId());
////        ctx.addZuulRequestHeader("userName", URLEncoder.encode(user.getUserName()));
//
//    }
//
//    /**
//     * 返回session中的用户信息
//     *
//     * @param request
//     * @param ctx
//     * @return
//     */
//    private JwtInfo getJWTUser(HttpServletRequest request, RequestContext ctx) throws Exception {
//        String authToken = request.getHeader(jwtHelper.getTokenHeader());
//        if (StringUtils.isBlank(authToken)) {
//            authToken = request.getParameter("token");
//        }
//        ctx.addZuulRequestHeader(jwtHelper.getTokenHeader(), authToken);
//        BaseContextHandler.setToken(authToken);
//        return jwtHelper.getInfoFromToken(authToken);
//    }
//
//
//    private void checkUserPermission(PermissionInfo[] permissions, RequestContext ctx, JwtInfo user) {
//        List<PermissionInfo> permissionInfos = userService.getPermissionByUserId(user.getUserId());
//        PermissionInfo current = null;
//        for (PermissionInfo info : permissions) {
//            boolean anyMatch = permissionInfos.parallelStream().anyMatch(new Predicate<PermissionInfo>() {
//                @Override
//                public boolean test(PermissionInfo permissionInfo) {
//                    return permissionInfo.getCode().equals(info.getCode());
//                }
//            });
//            if (anyMatch) {
//                current = info;
//                break;
//            }
//        }
//        if (current == null) {
//            setFailedRequest(JSON.toJSONString(new TokenForbiddenResponse("Token Forbidden!")), 200);
//        } else {
//            if (!RequestMethod.GET.toString().equals(current.getMethod())) {
//                setCurrentUserInfo(ctx, user, current);
//            }
//        }
//    }


    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

}
