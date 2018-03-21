package com.emrubik.springcloud.domain.to.payload.user;


import com.emrubik.springcloud.dao.entity.Role;
import com.emrubik.springcloud.dao.entity.User;
import lombok.Data;
import java.util.List;

@Data
public class GetUserInfoResp {

    private User user;

    private List<Role> roles;

}
