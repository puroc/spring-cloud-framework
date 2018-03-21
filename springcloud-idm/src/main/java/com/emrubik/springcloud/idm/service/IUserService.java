package com.emrubik.springcloud.idm.service;

import com.baomidou.mybatisplus.service.IService;
import com.emrubik.springcloud.dao.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author puroc123
 * @since 2018-03-20
 */
public interface IUserService extends IService<User> {

    User selectUserAndRoles(String userId);

}
