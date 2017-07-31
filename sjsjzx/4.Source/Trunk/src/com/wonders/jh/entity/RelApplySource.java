package com.wonders.jh.entity;

import org.nutz.dao.entity.annotation.*;

@Table("REL_APPLY_SOURCE")
public class RelApplySource {

    /**
	 * 申请号
	 */      
    @Column("ST_APPLY_ID")
	private String stApplyId; 
    /**
	 * 已发布资源ID
	 */   
    @Column("ST_Pub_ID")
	private String stPubId; 

	public String getStApplyId() {
		return stApplyId;
	}
	public void setStApplyId(String stApplyId) {
		this.stApplyId = stApplyId;
	}
	public String getStPubId() {
		return stPubId;
	}
	public void setStPubId(String stPubId) {
		this.stPubId = stPubId;
	}

    
}
