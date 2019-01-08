package com.emrubik.springcloud.api.idm;

import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.payload.login.LoginReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "springcloud-idm", fallback = IUserService.IUserServiceFallback.class)
public interface IUserService {

    @PostMapping("/idm/user/login")
    ResponseEntity login(BaseReq<LoginReq> baseReq);

    @GetMapping("/idm/user/info")
    ResponseEntity getUserInfo();

    @Component
    static class IUserServiceFallback implements IUserService {

        @Override
        public ResponseEntity login(BaseReq<LoginReq> baseReq) {
            return null;
        }

        @Override
        public ResponseEntity getUserInfo() {
            return null;
        }
    }

}
