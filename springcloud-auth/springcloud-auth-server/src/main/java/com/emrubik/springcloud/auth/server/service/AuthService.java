package com.emrubik.springcloud.auth.server.service;

import com.emrubik.springcloud.api.idm.IUserService;
import com.emrubik.springcloud.auth.common.domain.JwtInfo;
import com.emrubik.springcloud.auth.common.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthService  {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtHelper jwtHelper;


    public String createToken(String username, String password) throws Exception {
//        UserInfo info = userService.validate(username,password);
//        String token = "";
//        if (!StringUtils.isEmpty(info.getId())) {
//            token = jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
//        }

        String token = jwtHelper.generateToken(new JwtInfo("1", username));
        return token;
    }
}
