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
 * @title 角色表
 */
@Data
@Entity
public class SysRole implements Serializable {

    @Id
    @Column(length = 32)
    private String id;

    @Column
    private String name;

    @Column
    private Date createTime;

    @Column
    private Date updateTime = new Date();
}
