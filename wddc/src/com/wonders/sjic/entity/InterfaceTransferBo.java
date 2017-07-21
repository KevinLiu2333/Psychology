package com.wonders.sjic.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author Wanda
 * 接口交换数量实体类
 */
@Table("sjic_interface_transfer")
public class InterfaceTransferBo {
	
	/**
	 * 接口ID
	 */
	@Name
	@Column("jkid")
	private String jkId;
	
	/**
	 * 交易总数
	 */
	@Column("transfer_sum")
	private int transferSum;
	
	/**
	 * 交易成功
	 */
	@Column("transfer_success")
	private int transferSuccess;
	
	/**
	 * 交易失败
	 */
	@Column("transfer_failed")
	private int transferFailed;

	public String getJkId() {
		return jkId;
	}

	public void setJkId(String jkId) {
		this.jkId = jkId;
	}

	public int getTransferSum() {
		return transferSum;
	}

	public void setTransferSum(int transferSum) {
		this.transferSum = transferSum;
	}

	public int getTransferSuccess() {
		return transferSuccess;
	}

	public void setTransferSuccess(int transferSuccess) {
		this.transferSuccess = transferSuccess;
	}

	public int getTransferFailed() {
		return transferFailed;
	}

	public void setTransferFailed(int transferFailed) {
		this.transferFailed = transferFailed;
	}

	
	
	
	
}
