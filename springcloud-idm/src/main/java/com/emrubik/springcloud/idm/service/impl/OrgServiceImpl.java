package com.emrubik.springcloud.idm.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.Org;
import com.emrubik.springcloud.dao.mapper.OrgMapper;
import com.emrubik.springcloud.idm.service.IOrgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author puroc123
 * @since 2018-03-20
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

}
