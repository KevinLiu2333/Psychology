package com.wonders.admin.util;

import java.io.Serializable;
import java.util.List;

/**
 * 用于封装页面传输.
 * 
 * @author Gray
 *
 */
public class Page implements Serializable{

	private static final long serialVersionUID = 607076298703594390L;

	// 页面行集合
	@SuppressWarnings("rawtypes")
	private List<List> list;

	// 页面翻页栏值
	private List<Integer> page;

	@SuppressWarnings("rawtypes")
	public List<List> getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List<List> list) {
		this.list = list;
	}

	public List<Integer> getPage() {
		return page;
	}

	public void setPage(List<Integer> page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Page [list=" + list + ", page=" + page + "]";
	}
}
