package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.idm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author puroc123
 * @since 2018-03-15
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/test")
    @ResponseBody
    public User get(){
        List<User> list = userService.selectList(Condition.create().eq("name", "pud"));
        return list.get(0);
    }

}

