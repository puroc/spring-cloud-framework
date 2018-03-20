package com.emrubik.springcloud.idm.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.UserTokenBind;
import com.emrubik.springcloud.dao.mapper.UserTokenBindMapper;
import com.emrubik.springcloud.idm.service.IUserTokenBindService;
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
public class UserTokenBindServiceImpl extends ServiceImpl<UserTokenBindMapper, UserTokenBind> implements IUserTokenBindService {

}
