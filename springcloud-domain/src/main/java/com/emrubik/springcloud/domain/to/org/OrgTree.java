package com.emrubik.springcloud.domain.to.org;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrgTree {

    private int id;
    private String label;
    private List<OrgTree> children = new ArrayList<OrgTree>();

}
