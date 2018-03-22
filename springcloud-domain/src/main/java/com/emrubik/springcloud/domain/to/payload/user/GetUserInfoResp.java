package com.emrubik.springcloud.domain.to.payload.user;


import com.emrubik.springcloud.dao.entity.User;
import lombok.Data;

@Data
public class GetUserInfoResp {

    private User user;

}
