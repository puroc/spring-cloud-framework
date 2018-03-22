package com.emrubik.springcloud.domain.to.payload.org;

import com.emrubik.springcloud.dao.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GetOrgInfoResp {

    private List<User> users;
}
