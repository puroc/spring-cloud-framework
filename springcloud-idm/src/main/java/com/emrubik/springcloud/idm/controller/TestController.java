package com.emrubik.springcloud.idm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/idm")
@Controller
public class TestController {


    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "123";
    }
}
