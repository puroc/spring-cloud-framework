package com.emrubik.springcloud.idm.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.dao.mapper.UserMapper;
import com.emrubik.springcloud.idm.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User selectUserAndRoles(String userId) {
        return baseMapper.selectUserAndRoles(userId);
    }
}
