package com.wondersgroup.cmc.model.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.cmc.model.BaseOrgan;

/**
 * Organ entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ORGAN")
public class Organ extends BaseOrgan implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
}
