package com.emrubik.springcloud.samples;

import com.emrubik.springcloud.api.idm.IUserService;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.payload.login.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Test implements CommandLineRunner{
    @Autowired
    private IUserService userService;
    @Override
    public void run(String... args) throws Exception {
        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("lisi");
        loginReq.setPassword("111");
        BaseReq<LoginReq> baseReq = new BaseReq<LoginReq>();
        baseReq.setPayLoad(loginReq);
        ResponseEntity resp = userService.login(baseReq);
        System.out.println("111111111111111 "+resp.getStatusCode());
    }
}
