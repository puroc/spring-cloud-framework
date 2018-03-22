package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.domain.to.base.BaseResp;
import com.emrubik.springcloud.idm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author puroc123
 * @since 2018-03-20
 */
@Controller
@RequestMapping("/idm/org")
public class OrgController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{orgId}/users")
    public @NotNull
    ResponseEntity getUserListByOrgId(@PathVariable String orgId) throws Exception {
        List<User> users = userService.selectList(new EntityWrapper<User>().eq("org_id", orgId));
        BaseResp<User> baseResp = new BaseResp<User>();
        baseResp.setPayloads(users);
        return ResponseEntity.ok(baseResp);
    }

}

