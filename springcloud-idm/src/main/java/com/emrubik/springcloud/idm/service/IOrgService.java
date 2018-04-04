package com.emrubik.springcloud.idm.service;

import com.baomidou.mybatisplus.service.IService;
import com.emrubik.springcloud.dao.entity.Org;
import com.emrubik.springcloud.domain.to.org.OrgTree;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author puroc123
 * @since 2018-03-20
 */
public interface IOrgService extends IService<Org> {

     OrgTree getOrgTree(String orgId);

}
