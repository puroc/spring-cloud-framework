package com.emrubik.springcloud.domain.to.payload.login;

import lombok.Data;

@Data
public class LoginReq {
    private String username;
    private String password;
}
