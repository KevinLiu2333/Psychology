package com.wondersgroup.cmc.commons.event.model.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.cmc.commons.event.model.BaseBusilogInfo;

/**
 * Busilog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUSILOG")
public class BusilogInfo extends BaseBusilogInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
}
