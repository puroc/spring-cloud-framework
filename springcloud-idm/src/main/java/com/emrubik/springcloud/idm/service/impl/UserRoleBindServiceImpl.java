package com.emrubik.springcloud.idm.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.UserRoleBind;
import com.emrubik.springcloud.dao.mapper.UserRoleBindMapper;
import com.emrubik.springcloud.idm.service.IUserRoleBindService;
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
public class UserRoleBindServiceImpl extends ServiceImpl<UserRoleBindMapper, UserRoleBind> implements IUserRoleBindService {

}
