package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.emrubik.springcloud.dao.entity.Role;
import com.emrubik.springcloud.dao.entity.RolePermissionBind;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.base.BaseResp;
import com.emrubik.springcloud.idm.service.IRolePermissionBindService;
import com.emrubik.springcloud.idm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Validated
@RequestMapping("/idm/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRolePermissionBindService rolePermissionBindService;

    @PostMapping
    public @NotNull
    ResponseEntity addRole(@RequestBody @Validated BaseReq<Role> baseReq) {
        Role role = baseReq.getPayloads().get(0);
        boolean result = roleService.insert(role);
        BaseResp baseResp = new BaseResp();
        if (!result) {
            baseResp.setResultCode(BaseResp.RESULT_FAILED);
            baseResp.setMessage("角色添加失败，roleName：" + role.getName());
        }
        return ResponseEntity.ok(baseResp);
    }

    @PostMapping("/{id}/permission")
    public @NotNull
    ResponseEntity addRolePermissionBind(@PathVariable String id, @RequestBody @Validated BaseReq<RolePermissionBind> baseReq) {
        List<RolePermissionBind> permissionBinds = baseReq.getPayloads();
        boolean result = rolePermissionBindService.insertBatch(permissionBinds);
        BaseResp baseResp = new BaseResp();
        if (!result) {
            baseResp.setResultCode(BaseResp.RESULT_FAILED);
            baseResp.setMessage("角色和权限绑定关系添加失败,roleId:" + id);
        }
        return ResponseEntity.ok(baseResp);
    }

    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public @NotNull
    ResponseEntity deleteRole(@PathVariable String id) {
        BaseResp resp = new BaseResp();
        //先删除角色与权限的绑定关系
        rolePermissionBindService.delete(new EntityWrapper<RolePermissionBind>().eq("role_id", id));
        //删除角色
        boolean result = roleService.delete(new EntityWrapper<Role>().eq("id", id));
        if (!result) {
            resp.setResultCode(BaseResp.RESULT_FAILED);
            resp.setMessage("角色删除失败，roleId:" + id);
        }
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public @NotNull
    ResponseEntity updateRole(@PathVariable String id, @RequestBody @Validated BaseReq<Role> baseReq) {
        Role role = baseReq.getPayloads().get(0);
        boolean result = roleService.update(role, new EntityWrapper<Role>().eq("id", id));
        BaseResp baseResp = new BaseResp();
        if (!result) {
            baseResp.setMessage("更新角色失败，roleId:" + id);
            baseResp.setResultCode(BaseResp.RESULT_FAILED);
        }
        return ResponseEntity.ok(baseResp);
    }

    @DeleteMapping("/{id}/permission")
    public @NotNull
    ResponseEntity deleteRolePermissionBind(@PathVariable String id) {
        boolean result = rolePermissionBindService.delete(new EntityWrapper<RolePermissionBind>().eq("role_id", id));
        BaseResp resp = new BaseResp();
        if (!result) {
            resp.setMessage("删除角色和权限绑定关系失败，roleId:" + id);
            resp.setResultCode(BaseResp.RESULT_FAILED);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public @NotNull
    ResponseEntity getRoleInfo(@PathVariable String id) {
        Role role = roleService.getRoleInfo(id);
        BaseResp<Role> baseResp = new BaseResp<Role>();
        baseResp.setPayLoad(role);
        return ResponseEntity.ok(baseResp);
    }

}

