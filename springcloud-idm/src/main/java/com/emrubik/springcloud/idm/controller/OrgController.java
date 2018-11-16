package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.emrubik.springcloud.dao.entity.Org;
import com.emrubik.springcloud.dao.entity.Role;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.base.BaseResp;
import com.emrubik.springcloud.domain.to.base.PageResp;
import com.emrubik.springcloud.domain.to.org.AddOrgReq;
import com.emrubik.springcloud.domain.to.org.OrgTree;
import com.emrubik.springcloud.idm.service.IOrgService;
import com.emrubik.springcloud.idm.service.IRoleService;
import com.emrubik.springcloud.idm.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/idm/org")
public class OrgController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrgService orgService;

    @Autowired
    private IRoleService roleService;

    @GetMapping("/{orgId}/users")
    public @NotNull
    ResponseEntity getUserListByOrgId(@PathVariable String orgId,
                                      @RequestParam int current, int size,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String username,
                                      @RequestParam(required = false) String phone,
                                      @RequestParam(required = false) String email) throws Exception {

        //根据当前机构ID查询出机构树，并转换为list
        List<Integer> orgList = getOrgList(orgId);

        Wrapper<User> wrapper = new EntityWrapper<User>().in("org_id", orgList);
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("user.name", name.trim());
        }
        if (!StringUtils.isEmpty(username)) {
            wrapper.like("user.username", username.trim());
        }
        if (!StringUtils.isEmpty(email)) {
            wrapper.like("user.email", email.trim());
        }
        if (!StringUtils.isEmpty(phone)) {
            wrapper.like("user.phone", phone.trim());
        }

        Page<User> userPage = userService.getUserListByOrgId(new Page<User>(current, size), wrapper);
        PageResp<User> baseResp = new PageResp<User>();
        baseResp.setPayloads(userPage.getRecords());
        baseResp.setTotalNum(userPage.getTotal());
        return ResponseEntity.ok(baseResp);
    }

    @GetMapping("/{orgId}/roles")
    public @NotNull
    ResponseEntity getRoleListByOrgId(@PathVariable String orgId,
                                      @RequestParam int current, int size,
                                      @RequestParam(required = false) String name) {
        //根据当前机构获取其上级所有机构（包括当前机构）
        List<Integer> upperOrgList = orgService.getUpperOrgList(orgId);

        Wrapper<Role> wrapper = new EntityWrapper<Role>().in("org_id", upperOrgList);
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("role.name", name.trim());
        }

        Page<Role> rolePage = roleService.getRoleListByOrgId(new Page<Role>(current, size), wrapper);
        PageResp<Role> baseResp = new PageResp<Role>();
        baseResp.setPayloads(rolePage.getRecords());
        baseResp.setTotalNum(rolePage.getTotal());
        return ResponseEntity.ok(baseResp);
    }

    private List<Integer> getOrgList(@PathVariable String orgId) {
        OrgTree orgTree = orgService.getOrgTree(orgId);
        List<Integer> orgList = orgService.getOrgList(new ArrayList<Integer>(), orgTree);
        return orgList;
    }

    @GetMapping("{orgId}/tree")
    public @NotNull
    ResponseEntity getOrgTree(@PathVariable @NotBlank String orgId) {
        OrgTree orgTree = orgService.getOrgTree(orgId);
        BaseResp<OrgTree> baseResp = new BaseResp<OrgTree>();
        baseResp.setPayLoad(orgTree);
        return ResponseEntity.ok(baseResp);
    }

    @GetMapping("{orgId}")
    public @NotNull
    ResponseEntity getOrgInfo(@PathVariable @NotBlank String orgId) {
        Org org = orgService.selectOne(new EntityWrapper<Org>().eq("id", orgId));
        BaseResp<Org> baseResp = new BaseResp<Org>();
        baseResp.setPayLoad(org);
        return ResponseEntity.ok(baseResp);
    }

    @PostMapping("{orgId}")
    public @NotNull
    ResponseEntity addOrg(@RequestBody @Validated BaseReq<AddOrgReq> baseReq, @PathVariable String orgId) {
        AddOrgReq addOrgReq = baseReq.getPayloads().get(0);
        Org org = new Org();
        org.setName(addOrgReq.getLabel());
        org.setParentId(Integer.parseInt(orgId));
        org.setTimestamp(new Date());
        boolean result = orgService.insert(org);
        BaseResp baseResp = new BaseResp();
        if (!result) {
            baseResp.setResultCode(BaseResp.RESULT_FAILED);
            baseResp.setMessage("机构" + addOrgReq.getLabel() + "插入失败");
        }
        return ResponseEntity.ok(baseResp);
    }

    @DeleteMapping("{orgId}")
    public @NotNull
    ResponseEntity deleteOrg(@PathVariable String orgId) {
        BaseResp baseResp = new BaseResp();
        List<Org> sonOrgList = orgService.selectList(new EntityWrapper<Org>().eq("parent_id", orgId));
        if (!sonOrgList.isEmpty()) {
            baseResp.setResultCode(BaseResp.EXIST_SON_ORG);
            baseResp.setMessage("该机构拥有下级机构，不允许删除");
            return ResponseEntity.ok(baseResp);
        }
        boolean result = orgService.delete(new EntityWrapper<Org>().eq("id", orgId));
        if (!result) {
            baseResp.setResultCode(BaseResp.RESULT_FAILED);
            baseResp.setMessage("删除机构：" + orgId + "失败");
        }
        return ResponseEntity.ok(baseResp);
    }

}

