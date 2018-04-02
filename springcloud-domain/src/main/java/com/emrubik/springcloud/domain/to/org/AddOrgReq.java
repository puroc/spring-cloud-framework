package com.emrubik.springcloud.domain.to.org;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AddOrgReq {

    //添加的机构名称
    @NotBlank
    private String label;
}
