package com.mer.mdoc.modules.system.entity;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Mercurli
 * @date 2019/10/15
 * @version: 1.0
 * @title
 */
@Setter
@Getter
@Entity
public class SysRoleMenu {

    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String rId;

    @Column(length = 32)
    private String mId;
}
