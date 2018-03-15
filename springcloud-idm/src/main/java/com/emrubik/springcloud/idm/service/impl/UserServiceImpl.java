package com.emrubik.springcloud.idm.service.impl;

import com.emrubik.springcloud.dao.entity.User;
import com.emrubik.springcloud.dao.mapper.UserMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.idm.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pudong123
 * @since 2018-03-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
