package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.emrubik.springcloud.dao.entity.Org;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.base.BaseResp;
import com.emrubik.springcloud.domain.to.base.PageResp;
import com.emrubik.springcloud.domain.to.org.AddOrgReq;
import com.emrubik.springcloud.domain.to.org.OrgTree;
import com.emrubik.springcloud.idm.service.IOrgService;
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
import java.util.HashMap;
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

    @GetMapping("/{orgId}/users")
    public @NotNull
    ResponseEntity getUserListByOrgId(@PathVariable String orgId,
                                      @RequestParam int current, int size,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String username,
                                      @RequestParam(required = false) String phone,
                                      @RequestParam(required = false) String email) throws Exception {
        Wrapper<User> wrapper = new EntityWrapper<User>().eq("org_id", orgId);
        if (!StringUtils.isEmpty(name)) {
            wrapper.eq("name", name);
        }
        if (!StringUtils.isEmpty(username)) {
            wrapper.eq("username", username);
        }
        if (!StringUtils.isEmpty(email)) {
            wrapper.eq("email", email);
        }
        if (!StringUtils.isEmpty(phone)) {
            wrapper.eq("phone", phone);
        }
        Page<User> userPage = userService.selectPage(new Page<User>(current, size), wrapper);
        PageResp<User> baseResp = new PageResp<User>();
        baseResp.setPayloads(userPage.getRecords());
        baseResp.setTotalNum(userPage.getTotal());
        return ResponseEntity.ok(baseResp);
    }

    @DeleteMapping("/{orgId}/users")
    public @NotNull
    ResponseEntity
    deleteUserList(@PathVariable String orgId, @RequestBody BaseReq<User> baseReq) {
        List<String> userIdList = new ArrayList<String>();
        List<User> users = baseReq.getPayloads();
        int size = users.size();
        for (int i = 0; i < size; i++) {
            userIdList.add(users.get(i).getId() + "");
        }
        BaseResp resp = new BaseResp();
        boolean result = userService.deleteBatchIds(userIdList);
        if (!result) {
            resp.setMessage("删除失败,userIdList:" + userIdList);
            resp.setResultCode(BaseResp.RESULT_FAILED);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("{orgId}/tree")
    public @NotNull
    ResponseEntity getOrgTree(@PathVariable @NotBlank String orgId) {
        List<Org> orgList = orgService.selectList(new EntityWrapper<Org>());
        HashMap<String, OrgTree> map = new HashMap<String, OrgTree>();
        OrgTree orgTree = null;
        for (Org org : orgList) {
            String id = org.getId() + "";
            OrgTree node = new OrgTree();
            node.setId(org.getId());
            node.setLabel(org.getName());
            map.put(id, node);
        }
        for (Org org : orgList) {
            String id = org.getId() + "";
            String parentId = org.getParentId() + "";
            if (map.containsKey(parentId)) {
                map.get(parentId).getChildren().add(map.get(id));
            }
        }
        orgTree = map.get(orgId);
        BaseResp<OrgTree> baseResp = new BaseResp<OrgTree>();
        baseResp.setPayLoad(orgTree);
        return ResponseEntity.ok(baseResp);
    }

    @PostMapping("{orgId}")
    public @NotNull
    ResponseEntity addOrg(@RequestBody @Validated BaseReq<AddOrgReq> baseReq, @PathVariable String orgId) {
        AddOrgReq addOrgReq = baseReq.getPayloads().get(0);
        Org org = new Org();
        org.setName(addOrgReq.getLabel());
        org.setParentId(Integer.parseInt(orgId));
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

