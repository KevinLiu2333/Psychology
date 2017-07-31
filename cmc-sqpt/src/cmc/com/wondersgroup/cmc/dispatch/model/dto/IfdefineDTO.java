package com.wondersgroup.cmc.dispatch.model.dto;

import com.wondersgroup.cmc.dispatch.model.BaseIfdefine;

public class IfdefineDTO extends BaseIfdefine implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long transsum;
	
	private Long transsuc;

	private Long transfail;

	public Long getTranssum() {
		return transsum;
	}

	public void setTranssum(Long transsum) {
		this.transsum = transsum;
	}

	public Long getTranssuc() {
		return transsuc;
	}

	public void setTranssuc(Long transsuc) {
		this.transsuc = transsuc;
	}

	public Long getTransfail() {
		return transfail;
	}

	public void setTransfail(Long transfail) {
		this.transfail = transfail;
	}
	
	
}
