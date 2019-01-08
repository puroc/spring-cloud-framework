package com.emrubik.springcloud.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author puroc123
 * @since 2018-11-30
 */
public class DaBasicFlowT implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识
     */
    private String cmCode;
    /**
     * 瞬时流量
     */
    private BigDecimal insNum;
    /**
     * 正向累计流量
     */
    private BigDecimal rightAccNum;
    /**
     * 反向累计流量
     */
    private BigDecimal againstAccNum;
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

    public BigDecimal getInsNum() {
        return insNum;
    }

    public void setInsNum(BigDecimal insNum) {
        this.insNum = insNum;
    }

    public BigDecimal getRightAccNum() {
        return rightAccNum;
    }

    public void setRightAccNum(BigDecimal rightAccNum) {
        this.rightAccNum = rightAccNum;
    }

    public BigDecimal getAgainstAccNum() {
        return againstAccNum;
    }

    public void setAgainstAccNum(BigDecimal againstAccNum) {
        this.againstAccNum = againstAccNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DaBasicFlowT{" +
        ", cmCode=" + cmCode +
        ", insNum=" + insNum +
        ", rightAccNum=" + rightAccNum +
        ", againstAccNum=" + againstAccNum +
        ", createTime=" + createTime +
        "}";
    }
}
