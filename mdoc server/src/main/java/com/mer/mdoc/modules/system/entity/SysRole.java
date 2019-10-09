package com.mer.mdoc.modules.system.entity;

import com.mer.mdoc.modules.system.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title 角色表
 */
@Data
@Entity
public class SysRole extends BaseEntity {


    @Column
    private String name;

    /**
     * 角色状态 1-启用 0-禁用
     */
    @Column
    private Integer status = 1;

}
