package com.hss.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 用户信息
 */
public class User extends BaseDomain implements UserDetails {

    public User() {
    }

    private Long id;
    /** 用户名 */
    private String userName;
    /** 用户姓名 */
    private String name;
    /** 密码 */
    private String passWord;
    /** 手机号 */
    private String mobileNumber;
    /** 出生日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Date birthday;
    /** 性别 */
    private Integer sex;
    /** 头像 */
    private String headImgUrl;
    /** 是否管理员 **/
    private Boolean tfAdmin;
    /** 是否可用 */
    private Boolean enabled;
    /** 是否锁定 */
    private Boolean locked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {//账号未过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//账号未锁定
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {//凭证未过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Boolean getTfAdmin() {
        return tfAdmin;
    }

    public void setTfAdmin(Boolean tfAdmin) {
        this.tfAdmin = tfAdmin;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
