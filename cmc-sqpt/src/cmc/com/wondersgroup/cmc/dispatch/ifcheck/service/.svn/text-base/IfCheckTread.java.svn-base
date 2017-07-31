package com.wondersgroup.cmc.dispatch.ifcheck.service;

import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;

public class IfCheckTread extends Thread {
	private Ifdefine ifdefine;
	private IfCheckVS ifCheckVS;
	
	public IfCheckTread(Ifdefine ifdefine,IfCheckVS ifCheckVS) {
		this.ifdefine = ifdefine;
		this.ifCheckVS = ifCheckVS;
	}
	
	/**
	 * 校验接口联通性
	 * @param ifdefine
	 */
	public void run() {
		ifCheckVS.ifcheck(ifdefine);
	}
}