package com.mer.mdoc.modules.system.entity;

import com.mer.mdoc.modules.system.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title 用户表
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
public class SysUser extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * 密码盐
     */
    @Column(nullable = false)
    private String salt;

    @Column
    private String name;

    /**
     * 头像
     */
    @Column
    private String avatar;

    @Column
    private Date birthDay;

    /**
     * 性别 1-男 2-女
     */
    @Column
    private Integer sex;

    @Column
    private String mobile;

    @Column
    private String email;

    /**
     * 用户状态 1-正常 0-注销
     */
    @Column
    private Integer status = 1;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }

}
