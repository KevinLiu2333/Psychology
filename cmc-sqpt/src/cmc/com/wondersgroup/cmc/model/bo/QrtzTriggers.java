package com.wondersgroup.cmc.model.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.cmc.model.BaseQrtzTriggers;


/**
 * QrtzTriggers entity.
 */
@Entity
@Table(name="QRTZ_TRIGGERS")
public class QrtzTriggers extends BaseQrtzTriggers implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
}
