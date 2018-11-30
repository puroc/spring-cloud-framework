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
public class DaBasicQualityT implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识
     */
    private String cmCode;
    /**
     * ph值
     */
    private BigDecimal ph;
    /**
     * 浊度
     */
    private BigDecimal ntu;
    /**
     * 余氯
     */
    private BigDecimal cl;
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

    public BigDecimal getPh() {
        return ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    public BigDecimal getNtu() {
        return ntu;
    }

    public void setNtu(BigDecimal ntu) {
        this.ntu = ntu;
    }

    public BigDecimal getCl() {
        return cl;
    }

    public void setCl(BigDecimal cl) {
        this.cl = cl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DaBasicQualityT{" +
        ", cmCode=" + cmCode +
        ", ph=" + ph +
        ", ntu=" + ntu +
        ", cl=" + cl +
        ", createTime=" + createTime +
        "}";
    }
}
