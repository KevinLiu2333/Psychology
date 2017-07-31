package com.wondersgroup.wssip.model.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.wssip.model.BaseUaUserInfo;

/**
 * Ifdefine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UAUSERINFO")
public class UaUserInfo extends BaseUaUserInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
}
