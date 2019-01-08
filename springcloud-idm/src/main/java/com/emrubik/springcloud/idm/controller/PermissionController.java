package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.emrubik.springcloud.dao.entity.Permission;
import com.emrubik.springcloud.domain.to.base.BaseResp;
import com.emrubik.springcloud.idm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/idm/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    public ResponseEntity getPermissionList() {
        List<Permission> permissions = permissionService.selectList(new EntityWrapper<Permission>());
        BaseResp<Permission> baseResp = new BaseResp<Permission>();
        baseResp.setPayloads(permissions);
        return ResponseEntity.ok(baseResp);
    }

}

