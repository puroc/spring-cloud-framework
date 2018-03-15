package com.emrubik.springcloud.idm.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.RolePermission;
import com.emrubik.springcloud.dao.mapper.RolePermissionMapper;
import com.emrubik.springcloud.idm.service.IRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author puroc123
 * @since 2018-03-15
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
