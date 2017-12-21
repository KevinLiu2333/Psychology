package com.wonders.mlgl.entity.view;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.View;

@View("V_SOURCE_TREE")
public class VSourceTree {
	
	@Column("ST_NAME")
	private String stName; 	
	
	@Column("ST_CODE")
	private String stCode;	 
	
	@Column("ST_PARENT_CODE")
	private String stParentCode;	 
	
	/**
	 * 统计表类型（1-国家主题 2-部门主题 3-委办局）
	 */
	@Column("ST_TYPE")
	private String stType;

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStCode() {
		return stCode;
	}

	public void setStCode(String stCode) {
		this.stCode = stCode;
	}

	public String getStParentCode() {
		return stParentCode;
	}

	public void setStParentCode(String stParentCode) {
		this.stParentCode = stParentCode;
	}

	public String getStType() {
		return stType;
	}

	public void setStType(String stType) {
		this.stType = stType;
	}	 

}
