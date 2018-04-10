package com.emrubik.springcloud.idm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.Org;
import com.emrubik.springcloud.dao.mapper.OrgMapper;
import com.emrubik.springcloud.domain.to.org.OrgTree;
import com.emrubik.springcloud.idm.service.IOrgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Integer> getUpperOrgList(String orgId) {
        List<Org> orgList = selectList(new EntityWrapper<Org>());
        //将数据库中所有的机构放入到map中，key为机构Id，value为机构对象
        HashMap<String, Org> map = new HashMap<String, Org>();
        for (Org org : orgList) {
            String id = org.getId() + "";
            map.put(id, org);
        }
        //如果map中没有要查找的机构，则抛出异常
        if (!map.containsKey(orgId)) {
            throw new RuntimeException("orgId:" + orgId + "不存在");
        }
        List<Integer> list = new ArrayList<Integer>();
        String nextOrgId = orgId;
        //从map中找到该机构和其所有的上级机构，组成list
        while (true) {
            //
            Org org = map.get(nextOrgId);
            if (org == null) {
                throw new RuntimeException("orgId:" + nextOrgId + "不存在");
            }
            list.add(org.getId());
            if (org.getParentId() == 0) {
                break;
            }
            nextOrgId = org.getParentId() + "";
        }
        return list;
    }

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

    @Override
    public List<Integer> getOrgList(List<Integer> orgList, OrgTree orgTree) {
        //将当前机构ID放到list中
        orgList.add(orgTree.getId());
        //判断当前机构是否有子机构，若有，则地柜调用此方法
        if (!orgTree.getChildren().isEmpty()) {
            for (OrgTree child : orgTree.getChildren()) {
                getOrgList(orgList, child);
            }
        }
        return orgList;
    }

}
