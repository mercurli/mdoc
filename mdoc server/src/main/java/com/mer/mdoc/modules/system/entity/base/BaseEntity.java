package com.mer.mdoc.modules.system.entity.base;

import com.mer.mdoc.core.util.IdUtils;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mercurli
 * @date 2019/10/8
 * @version: 1.0
 * @title
 */
@Setter
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(length = 32)
    private String id = IdUtils.generateId();

    @Column
    private Date createTime = new Date();

}
