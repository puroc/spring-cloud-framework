package com.emrubik.springcloud.idm.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.emrubik.springcloud.dao.entity.Org;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.domain.to.base.BaseReq;
import com.emrubik.springcloud.domain.to.base.BaseResp;
import com.emrubik.springcloud.domain.to.base.PageResp;
import com.emrubik.springcloud.domain.to.org.OrgTree;
import com.emrubik.springcloud.idm.service.IOrgService;
import com.emrubik.springcloud.idm.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    ResponseEntity getOrgTree() {
        List<Org> orgList = orgService.selectList(new EntityWrapper<Org>());
        HashMap<String, OrgTree> map = new HashMap<String, OrgTree>();
        OrgTree orgTree = null;
        String rootId = null;
        for (Org org : orgList) {
            String id = org.getId() + "";
            OrgTree node = new OrgTree();
            node.setLabel(org.getName());
            map.put(id, node);
        }
        for (Org org : orgList) {
            String id = org.getId() + "";
            String parentId = org.getParentId() + "";
            if ("0".equals(parentId)) {
                rootId = id;
            }
            if (map.containsKey(parentId)) {
                map.get(parentId).getChildren().add(map.get(id));
            }
        }
        if (!StringUtils.isEmpty(rootId)) {
            orgTree = map.get(rootId);
        }
        BaseResp<OrgTree> baseResp = new BaseResp<OrgTree>();
        baseResp.setPayLoad(orgTree);
        return ResponseEntity.ok(baseResp);
    }


}

