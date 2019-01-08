package com.emrubik.springcloud.samples;

import com.emrubik.springcloud.api.idm.IUserService;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.payload.login.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sample")
public class TestController {

    @Value("${ip}")
    private String ip;

    @Autowired
    private IUserService userService;

    @GetMapping("/getUserInfo")
    public String test() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestContextHolder.setRequestAttributes(attributes, true);
        long start = System.currentTimeMillis();
        ResponseEntity resp = userService.getUserInfo();
        long end = System.currentTimeMillis();
        return "resp: " + resp + ",time:" + (end - start);
    }

    @GetMapping("/login")
    public String login() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestContextHolder.setRequestAttributes(attributes, true);
        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("lisi");
        loginReq.setPassword("111");
        BaseReq<LoginReq> baseReq = new BaseReq<LoginReq>();
        baseReq.setPayLoad(loginReq);
        long start = System.currentTimeMillis();
        ResponseEntity resp = userService.login(baseReq);
        long end = System.currentTimeMillis();
        return "resp: " + resp + ",time:" + (end - start);
    }
}
