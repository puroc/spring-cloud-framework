package com.emrubik.springcloud.idm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/idm/test")
public class TestController {

    @RequestMapping("/haha")
    public String haha(HttpServletRequest request, HttpSession session) {
        System.out.println(request.getMethod());
        System.out.println(session.getLastAccessedTime());
        System.out.println(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getMethod());
        return "haha";
    }
}
