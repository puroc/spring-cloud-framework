package com.emrubik.springcloud.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.emrubik.springcloud.dao.entity.DaBasicPressureT;
import com.emrubik.springcloud.dao.mapper.DaBasicPressureTMapper;
import com.emrubik.springcloud.service.IDaBasicPressureTService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 水厂压力计压力实时数据表 服务实现类
 * </p>
 *
 * @author puroc123
 * @since 2018-11-30
 */
@Service
public class DaBasicPressureTServiceImpl extends ServiceImpl<DaBasicPressureTMapper, DaBasicPressureT> implements IDaBasicPressureTService {

}
