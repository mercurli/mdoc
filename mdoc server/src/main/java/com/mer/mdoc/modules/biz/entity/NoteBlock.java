package com.mer.mdoc.modules.biz.entity;

import com.mer.mdoc.modules.system.entity.base.BaseEntity;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author mer
 * @version 0.1.4
 * @date 2019/9/23
 */
@Setter
@Getter
@Entity
public class NoteBlock extends BaseEntity {

	@Column(nullable = false)
	private String uId;

	private Integer display;

	@Column(nullable = false)
	private String name;

}