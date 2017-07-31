package com.wonders.pzgl.service;

import java.util.Comparator;

import com.wonders.pzgl.entity.ColumnConfig;

public class OrderComparator implements Comparator<ColumnConfig>{
	public int compare(ColumnConfig col0, ColumnConfig col1) {

		int flag = col0.getOrderNum().compareTo(col1.getOrderNum());
			return flag;
		}
}
