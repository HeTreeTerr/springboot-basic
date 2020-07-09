package com.hss.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hss.util.BaseDomain;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 */
@JsonIgnoreProperties({ "password"})
public class User extends BaseDomain implements UserDetails {

    public User() {
    }

    private Long id;
    /** 用户名 */
    private String userName;
    /** 用户姓名 */
    private String name;
    /** 密码 */
    private String password;
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
    /** 用户角色 */
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
