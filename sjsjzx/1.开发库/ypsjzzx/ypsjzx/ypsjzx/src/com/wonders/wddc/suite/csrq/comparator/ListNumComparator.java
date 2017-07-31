package com.wonders.wddc.suite.csrq.comparator;

import java.util.Comparator;

import com.wonders.wddc.suite.csrq.entity.ColRelationConfigBo;

public class ListNumComparator  implements Comparator<ColRelationConfigBo>{

	@Override
	public int compare(ColRelationConfigBo o1, ColRelationConfigBo o2) {
		int flag = o1.getListNum().compareTo(o2.getListNum());
		return flag;
	}

}
