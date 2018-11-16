package com.emrubik.springcloud.domain.to.payload.login;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginReq {

    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
