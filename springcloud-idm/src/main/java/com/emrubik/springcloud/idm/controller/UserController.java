package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.emrubik.springcloud.common.annotation.IgnoreJwtValidation;
import com.emrubik.springcloud.common.util.JwtHelper;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.dao.entity.UserTokenBind;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.base.BaseResp;
import com.emrubik.springcloud.domain.to.payload.login.LoginReq;
import com.emrubik.springcloud.domain.to.payload.login.LoginResp;
import com.emrubik.springcloud.domain.vo.JwtInfo;
import com.emrubik.springcloud.idm.constant.Constants;
import com.emrubik.springcloud.idm.service.IUserService;
import com.emrubik.springcloud.idm.service.IUserTokenBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
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
@Validated
@RequestMapping("/idm/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserTokenBindService userTokenBindService;

    @Autowired
    private JwtHelper jwtHelper;

    @GetMapping("/")
    @ResponseBody
    public User getUserInfo() {
        List<User> list = userService.selectList(Condition.create().eq("name", "pud"));
        return list.get(0);
    }

    @IgnoreJwtValidation
    @PostMapping("/login")
    public @NotNull
    ResponseEntity login(@RequestBody @Validated BaseReq<LoginReq> baseReq) throws Exception {
        LoginReq loginReq = baseReq.getPayloads().get(0);

        //验证用户合法性
        User user = validateUser(loginReq);

        BaseResp<LoginResp> resp = new BaseResp<LoginResp>();

        //用户不存在，返回404，登录失败
        if (user == null) {
            resp.setResultCode(BaseResp.RESULT_FAILED);
            resp.setMessage(Constants.USER_NOT_EXIST);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }

        //生成token
        LoginResp loginResp = new LoginResp();
        String jwtToken = jwtHelper.generateToken(createJwtInfo(user));
        loginResp.setToken(jwtToken);
        resp.setPayLoad(loginResp);

        //存储token和用户的关系
        insertOrUpdateUserTokenBind(user, jwtToken);

        return ResponseEntity.ok(resp);
    }

    private void insertOrUpdateUserTokenBind(User user, String jwtToken) {
        UserTokenBind userToken = userTokenBindService.selectOne(new EntityWrapper<UserTokenBind>().eq("user_id", user.getId()));
        UserTokenBind userTokenBind = new UserTokenBind();
        //设置了主键属性，mybatis的insertOrUpdate方法会先根据ID进行更新，如果没有更新到数据，则插入数据
        if(userToken != null){
            userTokenBind.setId(userToken.getId());
        }
        userTokenBind.setToken(jwtToken);
        userTokenBind.setUserId(user.getId());
        userTokenBind.setExpire(Integer.parseInt(jwtHelper.getExpire()));
        userTokenBind.setTimestamp(new Date());
        userTokenBindService.insertOrUpdate(userTokenBind);
    }

    private JwtInfo createJwtInfo(User user) {
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setUserId(user.getId() + "");
        jwtInfo.setUserName(user.getName());
        jwtInfo.setCurrentTime(System.currentTimeMillis());
        return jwtInfo;
    }

    private User validateUser(LoginReq loginReq) {
        return userService.selectOne(new EntityWrapper<User>().eq("username", loginReq.getUsername()).eq("password", loginReq.getPassword()));
    }

}

