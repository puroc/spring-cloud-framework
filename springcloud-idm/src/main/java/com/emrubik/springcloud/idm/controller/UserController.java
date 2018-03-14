package com.emrubik.springcloud.idm.controller;

import com.emrubik.springcloud.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/validate")
    public User validate(User user) {
        User user2 = new User();
        user2.setId("123");
        user2.setUsername(user.getUsername());
        user2.setPassword(user.getPassword());
        return user2;
    }
}
