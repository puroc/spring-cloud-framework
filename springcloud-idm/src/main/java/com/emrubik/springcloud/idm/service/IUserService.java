package com.emrubik.springcloud.idm.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.emrubik.springcloud.dao.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author puroc123
 * @since 2018-03-20
 */
public interface IUserService extends IService<User> {

    User getUserInfo(String userId);

    Page<User> getUserListByOrgId(Page<User> page, @Param("ew") Wrapper<User> wrapper);

}
