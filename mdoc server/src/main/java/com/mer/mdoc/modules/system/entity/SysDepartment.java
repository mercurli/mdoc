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
 * @title 部门表
 */
@Data
@Entity
public class SysDepartment implements Serializable {

    @Id
    @Column(length = 32)
    private String id;

    @Column
    private String name;

    @Column(length = 32)
    private String parentId;

    /**
     * 是否可用
     */
    @Column
    private boolean enabled = true;

    @Column
    private Date createTime;

    @Column
    private Date updateTime = new Date();
}
