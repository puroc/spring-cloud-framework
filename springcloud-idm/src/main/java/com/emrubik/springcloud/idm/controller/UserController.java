package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.emrubik.springcloud.common.annotation.IgnoreJwtValidation;
import com.emrubik.springcloud.common.util.JwtHelper;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.base.BaseResp;
import com.emrubik.springcloud.domain.to.payload.login.LoginReq;
import com.emrubik.springcloud.domain.to.payload.login.LoginResp;
import com.emrubik.springcloud.domain.vo.JwtInfo;
import com.emrubik.springcloud.idm.constant.Constants;
import com.emrubik.springcloud.idm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
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

    @Autowired
    private JwtHelper jwtHelper;

    @GetMapping("/test")
    @ResponseBody
    public User get() {
        List<User> list = userService.selectList(Condition.create().eq("name", "pud"));
        return list.get(0);
    }

    @IgnoreJwtValidation
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody BaseReq<LoginReq> baseReq) throws Exception {
        LoginReq loginReq = baseReq.getPayloads().get(0);

        //验证用户合法性
        User user = userService.selectOne(new EntityWrapper<User>().eq("username", loginReq.getUsername()).eq("password", loginReq.getPassword()));

        BaseResp resp = new BaseResp();
        //用户不存在，返回登录失败
        if (user == null) {
            resp.setResultCode(BaseResp.RESULT_FAILED);
            resp.setMessage(Constants.USER_NOT_EXIST);
        }else{
            LoginResp loginResp = new LoginResp();
            loginResp.setToken(jwtHelper.generateToken(new JwtInfo(user.getId() + "", user.getName())));
            resp.setPayLoad(loginResp);
        }
        return ResponseEntity.ok(resp);

    }

}

