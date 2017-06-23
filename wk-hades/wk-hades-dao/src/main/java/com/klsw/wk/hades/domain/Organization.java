package com.klsw.wk.hades.domain;

import javax.persistence.Table;

/**
 * 机构信息
 * @author liulixi
 * @version 2017年6月19日16:44:45
 */
@Table(name = "tb_organization")
public class Organization extends BaseModel {
	private static final long serialVersionUID = 1L;

	/** 机构名称 */
	private String name;
	
	/** 描述 */
	private String description;
	
	public Organization() {
		// NOOP
	}
	
	public Organization(String dtGridPager) {
		super(dtGridPager);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
