package com.emrubik.springcloud.dao.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author puroc123
 * @since 2018-11-30
 */
public class DaBasicPressureT implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识
     */
    private String cmCode;
    /**
     * 压力
     */
    private BigDecimal pressure;
    /**
     * 统计时间
     */
    private Date createTime;


    public String getCmCode() {
        return cmCode;
    }

    public void setCmCode(String cmCode) {
        this.cmCode = cmCode;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DaBasicPressureT{" +
        ", cmCode=" + cmCode +
        ", pressure=" + pressure +
        ", createTime=" + createTime +
        "}";
    }
}
