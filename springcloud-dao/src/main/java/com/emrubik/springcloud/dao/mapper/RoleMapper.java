package com.emrubik.springcloud.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.emrubik.springcloud.dao.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author puroc123
 * @since 2018-03-20
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role getRoleInfo(String id);

    List<Role> getRoleListByOrgId(Page<Role> page, @Param("ew") Wrapper<Role> wrapper);

}
