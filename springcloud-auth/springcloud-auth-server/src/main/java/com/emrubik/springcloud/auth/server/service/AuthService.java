package com.emrubik.springcloud.auth.server.service;

import com.emrubik.springcloud.api.idm.IUserService;
import com.emrubik.springcloud.auth.common.domain.JwtInfo;
import com.emrubik.springcloud.auth.common.util.JwtHelper;
import com.emrubik.springcloud.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthService {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtHelper jwtHelper;


    public String createToken(User user) throws Exception {
        User info = userService.validate(user);
        String token = "";
        if (!StringUtils.isEmpty(info.getId())) {
            token = jwtHelper.generateToken(new JwtInfo(info.getId(), info.getUsername()));
        }
        return token;
    }
}
