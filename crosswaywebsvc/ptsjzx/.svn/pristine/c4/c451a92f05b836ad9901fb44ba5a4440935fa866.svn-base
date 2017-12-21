package com.wonders.zymlgx.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("R_APPLY_NUMBER")
public class ApplyNumber {
	/**主键名称**/
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("ID_NAME")
	private String idName;
	/**当前值**/
	@Column("CURRENT_VALUE")
	private Long currentValue;
	/**描述**/
	@Column("DESCRIPTION")
	private String description;
	
	public String initUUID() {
		if (Strings.isEmpty(idName))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.idName;
	}
	
	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public Long getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(Long currentValue) {
		this.currentValue = currentValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
