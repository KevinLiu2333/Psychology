package com.wonders.admin.entity.unifiedmonitoring;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

/**
 * 对应数据库发阀值紫电表的实体类.
 * 
 * @author Gray
 *
 */
@Table("TEST_DIC_THRESHOLD")
public class DicThresoldBean implements Serializable{

	private static final long serialVersionUID = 2122169128957899663L;

	// 阀值Id
	@Name
	@Readonly
	private String id;

	// 阀值名称
	@Column("THRESHOLD_NAME")
	private String name;

	// 阀值单位
	@Column("THRESHOLD_UNIT")
	private String unit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "DicThresoldBean [id=" + id + ", name=" + name + ", unit=" + unit + "]";
	}
}
