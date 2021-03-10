package com.mer.mdoc.modules.biz.entity;

import com.mer.mdoc.modules.system.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

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
	private String userId;

	private String disorder;

	@Column(nullable = false)
	private String name;

}