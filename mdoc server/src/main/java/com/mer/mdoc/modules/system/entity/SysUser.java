package com.mer.mdoc.modules.system.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title 用户表
 */
@Data
@Entity
public class SysUser implements Serializable {

    @Id
    @Column(length = 32)
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String nick;

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
    private String email;

    @Column
    private Date createTime;

    @Column
    private Date updateTime = new Date();

}
