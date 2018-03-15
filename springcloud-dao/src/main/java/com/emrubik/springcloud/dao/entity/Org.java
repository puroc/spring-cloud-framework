package com.emrubik.springcloud.dao.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author puroc123
 * @since 2018-03-15
 */
public class Org extends Model<Org> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer parentId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Org{" +
        ", id=" + id +
        ", name=" + name +
        ", parentId=" + parentId +
        "}";
    }
}
