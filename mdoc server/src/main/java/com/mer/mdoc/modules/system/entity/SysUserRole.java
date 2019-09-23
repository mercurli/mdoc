package com.mer.mdoc.modules.system.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title 用户 角色关系表
 */
public class SysUserRole implements Serializable {

    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String uId;

    @Column(length = 32)
    private String rId;
}
