package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.emrubik.springcloud.dao.entity.Permission;
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
import java.util.ArrayList;
import java.util.Date;
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

    @Transactional
    @PostMapping
    public 
    ResponseEntity addRole(@RequestBody @Validated BaseReq<Role> baseReq) {
        Role role = baseReq.getPayloads().get(0);
        role.setTimestamp(new Date());
        boolean result = roleService.insert(role);
        BaseResp baseResp = new BaseResp();
        if (!result) {
            baseResp.setResultCode(BaseResp.RESULT_FAILED);
            baseResp.setMessage("角色添加失败，roleName：" + role.getName());
            return ResponseEntity.ok(baseResp);
        }

        List<RolePermissionBind> permissionBinds = changePermissionListToRolePermissionBind(role);
        if(!permissionBinds.isEmpty()){
            result = rolePermissionBindService.insertBatch(permissionBinds);
            if (!result) {
                baseResp.setResultCode(BaseResp.RESULT_FAILED);
                baseResp.setMessage("角色与权限的绑定关系添加失败，roleName：" + role.getName());
            }
        }
        return ResponseEntity.ok(baseResp);
    }

    @PostMapping("/{id}/permission")
    public 
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
    public 
    ResponseEntity deleteRole(@PathVariable String id) {
        BaseResp resp = new BaseResp();
        //如果有用户绑定了这个角色，则不允许删除
        boolean binded = roleService.isRoleBinded(id);
        if (binded) {
            resp.setResultCode(BaseResp.RESULT_FAILED);
            resp.setMessage("有用户绑定了该角色，不允许删除，roleId:" + id);
            return ResponseEntity.ok(resp);
        }

        //先删除角色与权限的绑定关系
        boolean result = rolePermissionBindService.delete(new EntityWrapper<RolePermissionBind>().eq("role_id", id));
        if (!result) {
            resp.setResultCode(BaseResp.RESULT_FAILED);
            resp.setMessage("角色与权限的绑定关系删除失败，roleId:" + id);
            return ResponseEntity.ok(resp);
        }

        //删除角色
        result = roleService.delete(new EntityWrapper<Role>().eq("id", id));
        if (!result) {
            resp.setResultCode(BaseResp.RESULT_FAILED);
            resp.setMessage("角色删除失败，roleId:" + id);
        }
        return ResponseEntity.ok(resp);
    }

    @Transactional
    @PutMapping("/{id}")
    public 
    ResponseEntity updateRole(@PathVariable String id, @RequestBody @Validated BaseReq<Role> baseReq) {
        Role role = baseReq.getPayloads().get(0);
        role.setTimestamp(new Date());
        boolean result = roleService.update(role, new EntityWrapper<Role>().eq("id", id));
        BaseResp baseResp = new BaseResp();
        if (!result) {
            baseResp.setMessage("更新角色失败，roleId:" + id);
            baseResp.setResultCode(BaseResp.RESULT_FAILED);
            return ResponseEntity.ok(baseResp);
        }

        //更新角色和权限的绑定关系
        result = updateRolePermissionBind(role);
        if (!result) {
            baseResp.setMessage("更新角色与权限绑定关系失败，roleId:" + id);
            baseResp.setResultCode(BaseResp.RESULT_FAILED);
            return ResponseEntity.ok(baseResp);
        }
        return ResponseEntity.ok(baseResp);
    }

    private boolean updateRolePermissionBind(Role role) {
        //先删除该角色之前绑定的所有权限
        rolePermissionBindService.delete(new EntityWrapper<RolePermissionBind>().eq("role_id", role.getId()));

        List<RolePermissionBind> permissionBinds = changePermissionListToRolePermissionBind(role);
        //如果权限集合为空，则直接返回，不进行插入
        if (permissionBinds.isEmpty()) {
            return true;
        }
        //否则，批量插入角色和权限的绑定关系
        return rolePermissionBindService.insertBatch(permissionBinds);
    }

    //将permissionId的列表转换为RolePermissionBind对象的列表
    private List<RolePermissionBind> changePermissionListToRolePermissionBind(Role role) {
        List<RolePermissionBind> permissionBinds = new ArrayList<RolePermissionBind>();
        for (Permission permission : role.getPermissions()) {
            permissionBinds.add(new RolePermissionBind() {{
                this.setRoleId(role.getId());
                this.setPermissionId(permission.getId());
            }});
        }
        return permissionBinds;
    }

    @DeleteMapping("/{id}/permission")
    public 
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
    public 
    ResponseEntity getRoleInfo(@PathVariable String id) {
        Role role = roleService.getRoleInfo(id);
        BaseResp<Role> baseResp = new BaseResp<Role>();
        baseResp.setPayLoad(role);
        return ResponseEntity.ok(baseResp);
    }

    @DeleteMapping
    public 
    ResponseEntity
    deleteRoleList(@RequestBody BaseReq<Role> baseReq) {
        List<String> roleIdList = new ArrayList<String>();
        BaseResp resp = new BaseResp();
        List<Role> roles = baseReq.getPayloads();
        int size = roles.size();
        for (int i = 0; i < size; i++) {
            String roleId = roles.get(i).getId() + "";
            boolean binded = roleService.isRoleBinded(roleId);
            if (binded) {
                resp.setMessage("有用户绑定了该角色，不允许删除，roleId:" + roleId);
                resp.setResultCode(BaseResp.RESULT_FAILED);
                return ResponseEntity.ok(resp);
            }
            roleIdList.add(roleId);
        }

        boolean result = roleService.deleteBatchIds(roleIdList);
        if (!result) {
            resp.setMessage("删除失败,roleIdList:" + roleIdList);
            resp.setResultCode(BaseResp.RESULT_FAILED);
        }
        return ResponseEntity.ok(resp);
    }

}

