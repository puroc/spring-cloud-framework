package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.idm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pud123
 * @since 2018-03-15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("test")
    public User test(){
        User user = new User();
        user.setName("pud");
        user.setAge(23);
        userService.insert(user);
        List<User> list = userService.selectList(Condition.create().eq("name", "pud"));
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
}

