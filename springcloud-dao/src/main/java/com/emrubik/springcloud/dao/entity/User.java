package com.emrubik.springcloud.dao.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author puroc123
 * @since 2018-03-20
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Pattern(regexp = "^\\w+$",message = "用户名只能由字母数字和下划线组成")
    @NotBlank
    private String username;

    @Pattern(regexp = "^\\w+$",message = "密码只能由字母数字和下划线组成")
    @Length(min = 3,max =6,message = "密码长度位3到6位")
    @NotBlank
    private String password;

    @Pattern(regexp = "^[\\u4e00-\\u9fa5]*$",message = "姓名必须为中文")
    @NotBlank
    private String name;

    @Past
    private Date birthday;

    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$",message = "手机号必须是以13~18开头的11位数字")
    @NotBlank
    private String phone;

    @Email
    @NotBlank
    private String email;

    //性别，1:男，0:女
    @Range(min=0,max=1,message="性别值为0（女）或1（男）")
    private String sex;

    @NotNull(message = "机构ID不能为空")
    private Integer orgId;

    @TableField(exist = false)
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", name=" + name +
        ", birthday=" + birthday +
        ", phone=" + phone +
        ", email=" + email +
        ", sex=" + sex +
        ", orgId=" + orgId +
        "}";
    }
}
