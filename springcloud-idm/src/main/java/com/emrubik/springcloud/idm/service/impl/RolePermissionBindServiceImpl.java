package com.emrubik.springcloud.idm.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.RolePermissionBind;
import com.emrubik.springcloud.dao.mapper.RolePermissionBindMapper;
import com.emrubik.springcloud.idm.service.IRolePermissionBindService;
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
public class RolePermissionBindServiceImpl extends ServiceImpl<RolePermissionBindMapper, RolePermissionBind> implements IRolePermissionBindService {

}
