package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.emrubik.springcloud.common.annotation.IgnoreJwtValidation;
import com.emrubik.springcloud.common.util.BaseContextHandler;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
            return ResponseEntity.ok(resp);
        }

        LoginResp loginResp = new LoginResp();

        //生成token
        String jwtToken = createToken(user);
        loginResp.setToken(jwtToken);
        resp.setPayLoad(loginResp);
        return ResponseEntity.ok(resp);
    }

    private String createToken(User user) throws Exception {
        //生成token
        String jwtToken = jwtHelper.generateToken(createJwtInfo(user));

        //存储token和用户的关系
        insertOrUpdateUserTokenBind(user, jwtToken);
        return jwtToken;
    }

    @GetMapping("/info")
    public @NotNull
    ResponseEntity getUserInfo() {
        User user = userService.getUserInfo(BaseContextHandler.getUserId());
        BaseResp<User> resp = new BaseResp<User>();
        resp.setPayLoad(user);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping
    public @NotNull
    ResponseEntity
    deleteUserList(@RequestBody BaseReq<User> baseReq) {
        List<String> userIdList = new ArrayList<String>();
        List<User> users = baseReq.getPayloads();
        int size = users.size();
        for (int i = 0; i < size; i++) {
            userIdList.add(users.get(i).getId() + "");
        }
        BaseResp resp = new BaseResp();
        boolean result = userService.deleteBatchIds(userIdList);
        if (!result) {
            resp.setMessage("删除失败,userIdList:" + userIdList);
            resp.setResultCode(BaseResp.RESULT_FAILED);
        }
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{username}")
    public @NotNull
    ResponseEntity deleteUser(@PathVariable String username) {
        boolean result = userService.delete(new EntityWrapper<User>().eq("username", username));
        BaseResp resp = new BaseResp();
        if (!result) {
            resp.setMessage("username:" + username + "的用户不存在，删除失败");
            resp.setResultCode(BaseResp.RESULT_FAILED);
        }
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{username}")
    public @NotNull
    ResponseEntity updateUser(@PathVariable String username, @RequestBody BaseReq<User> baseReq) {
        User user = baseReq.getPayloads().get(0);
        user.setTimestamp(new Date());
        boolean result = userService.update(user, new EntityWrapper<User>().eq("username", username));
        BaseResp resp = new BaseResp();
        if (!result) {
            resp.setMessage("username:" + username + "的用户不存在，修改失败");
            resp.setResultCode(BaseResp.RESULT_FAILED);
        }
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public @NotNull
    ResponseEntity addUser(@RequestBody @Validated BaseReq<User> baseReq) {
        User user = baseReq.getPayloads().get(0);
        user.setTimestamp(new Date());
        BaseResp resp = new BaseResp();
        boolean result = false;
        if (userService.selectOne(Condition.create().eq("username", user.getUsername())) != null) {
            resp.setMessage("username:" + user.getUsername() + "用户已经存在，不能添加用户名相同的用户");
        } else {
            result = userService.insert(user);
        }
        if (!result) {
            if (StringUtils.isEmpty(resp.getMessage())) {
                resp.setMessage("username:" + user.getUsername() + " 添加用户失败");
            }
            resp.setResultCode(BaseResp.RESULT_FAILED);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/logout")
    public @NotNull
    ResponseEntity logout() throws Exception {
        boolean result = userTokenBindService.delete(new EntityWrapper<UserTokenBind>().eq("user_id", BaseContextHandler.getUserId()));
        return ResponseEntity.ok().build();
    }

    private void insertOrUpdateUserTokenBind(User user, String jwtToken) {
        UserTokenBind userToken = userTokenBindService.selectOne(new EntityWrapper<UserTokenBind>().eq("user_id", user.getId()));
        UserTokenBind userTokenBind = new UserTokenBind();
        //设置了主键属性，mybatis的insertOrUpdate方法会先根据ID进行更新，如果没有更新到数据，则插入数据
        if (userToken != null) {
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

    @GetMapping("token")
    public @NotNull
    ResponseEntity refreshToken() throws Exception {
        User user = userService.selectOne(new EntityWrapper<User>().eq("id", BaseContextHandler.getUserId()));
        //生成token
        String jwtToken = createToken(user);
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(jwtToken);
        BaseResp<LoginResp> resp = new BaseResp<LoginResp>();
        resp.setPayLoad(loginResp);
        return ResponseEntity.ok(resp);
    }

}

