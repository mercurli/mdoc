package com.mer.mdoc.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mer.mdoc.modules.system.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Mercurli
 * @date 2019/10/15
 * @version: 1.0
 * @title
 */
@Setter
@Getter
@Entity
public class SysMenu extends BaseEntity {

    @Column
    private String title;

    @Column
    private String path;

    @Column
    private String icon;

    @JsonProperty
    @Column(length = 32)
    private String pId;

    /**
     * 排序字段
     */
    @Column
    private Integer sequence;

}
