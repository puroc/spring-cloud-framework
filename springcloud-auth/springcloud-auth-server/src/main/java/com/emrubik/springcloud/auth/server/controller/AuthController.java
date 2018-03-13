package com.emrubik.springcloud.auth.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/jwt")
    @ResponseBody
    public String createToken(){
        return "111";
    }
}
