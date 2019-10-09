package com.mer.mdoc.modules.system.entity;

import com.mer.mdoc.modules.system.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title 部门表
 */
@Data
@Entity
public class SysDepartment extends BaseEntity {

    @Column
    private String name;

    @Column(length = 32)
    private String parentId;

    /**
     * 是否可用
     */
    @Column
    private boolean enabled = true;

}
