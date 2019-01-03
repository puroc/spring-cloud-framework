package com.emrubik.springcloud.api.idm;

import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.payload.login.LoginReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "springcloud-idm")
public interface IUserService {

    @PostMapping("/idm/user/login")
    ResponseEntity login(BaseReq<LoginReq> baseReq);

}
