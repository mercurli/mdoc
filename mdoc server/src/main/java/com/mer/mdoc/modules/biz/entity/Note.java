package com.mer.mdoc.modules.biz.entity;

import com.mer.mdoc.modules.system.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
/**
 *
 * @author mer
 * @version 0.1.4
 * @date 2019/9/23
 */
@Setter
@Getter
@Entity
public class Note extends BaseEntity {

	private Integer display;

	@Column(nullable = false)
	private Date updateTime;

	@Column(nullable = false)
	private String bId;

	@Column(nullable = false)
	private String title;

	private String content;

}