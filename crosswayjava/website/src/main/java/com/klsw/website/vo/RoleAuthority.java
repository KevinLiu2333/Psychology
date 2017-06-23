package com.klsw.website.vo;

import java.util.List;

import com.klsw.common.mall.model.BgAuthority;

/*
 * 将一个权限及其子权限进行封装，方便在页面中进行取值
 */
public class RoleAuthority {
	private BgAuthority bgAuthority;//定义大权限属性
	private List<BgAuthority> bgAuthorityList;//定义大权限对应的小权限集合
	
	public BgAuthority getBgAuthority() {
		return bgAuthority;
	}
	public void setBgAuthority(BgAuthority bgAuthority) {
		this.bgAuthority = bgAuthority;
	}
	public List<BgAuthority> getBgAuthorityList() {
		return bgAuthorityList;
	}
	public void setBgAuthorityList(List<BgAuthority> bgAuthorityList) {
		this.bgAuthorityList = bgAuthorityList;
	}	
}
