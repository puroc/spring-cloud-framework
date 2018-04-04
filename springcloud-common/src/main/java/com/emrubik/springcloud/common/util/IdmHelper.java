package com.emrubik.springcloud.common.util;

import com.emrubik.springcloud.domain.to.org.OrgTree;

import java.util.ArrayList;
import java.util.List;

public class IdmHelper {

    public static List<Integer> getOrgList(List<Integer> orgList,OrgTree orgTree) {
        //将当前机构ID放到list中
        orgList.add(orgTree.getId());
        //判断当前机构是否有子机构，若有，则地柜调用此方法
        if (!orgTree.getChildren().isEmpty()) {
            for (OrgTree child : orgTree.getChildren()) {
                getOrgList(orgList,child);
            }
        }
        return orgList;
    }

    public static void main(String[] args) {
        OrgTree orgTree = new OrgTree();
        orgTree.setId(1);
        orgTree.setLabel("数融科技");
        orgTree.setChildren(new ArrayList<OrgTree>() {{
            this.add(new OrgTree() {{
                this.setId(2);
                this.setLabel("ios");
                this.setChildren(new ArrayList<OrgTree>() {{
                    this.add(new OrgTree() {{
                        this.setId(4);
                    }});
                }});
            }});
            this.add(new OrgTree() {{
                this.setId(3);
                this.setLabel("android");
            }});

        }});
        List<Integer> orgList = new IdmHelper().getOrgList(new ArrayList<Integer>(),orgTree);
        for (Integer i : orgList) {
            System.out.println(i);
        }

    }
}
