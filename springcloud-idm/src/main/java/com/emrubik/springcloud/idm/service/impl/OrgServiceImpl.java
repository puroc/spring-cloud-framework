package com.emrubik.springcloud.idm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.Org;
import com.emrubik.springcloud.dao.mapper.OrgMapper;
import com.emrubik.springcloud.domain.to.org.OrgTree;
import com.emrubik.springcloud.idm.service.IOrgService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author puroc123
 * @since 2018-03-20
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

    @Override
    public OrgTree getOrgTree(String orgId) {
        List<Org> orgList = selectList(new EntityWrapper<Org>());
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
        return orgTree;
    }

}
