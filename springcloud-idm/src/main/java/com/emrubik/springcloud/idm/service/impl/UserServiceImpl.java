package com.emrubik.springcloud.idm.service.impl;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
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
    public User getUserInfo(String userId) {
        return baseMapper.getUserInfo(userId);
    }

    @Override
    public Page<User> getUserListByOrgId(Page<User> page, Wrapper<User> wrapper) {
        page.setRecords(baseMapper.getUserListByOrgId(page,wrapper));
        return page;
    }

}
