/** 
 * @(#)CheckRules.java 2008-10-30
 * 
 * Copyright (c) 1995-2008 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.wssip.commons.file;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yao Jianping
 * @version $Revision: 7074 $ 2008-10-30
 * @author $Date: 2008-11-21 15:33:58 +0800 (周五, 21 十一月 2008) $ by $Author: Yaojp $
 * @since 1.0
 */
public class CheckRules {
	/**
	 * 规则列表
	 */
	private List<CheckRule> checkRules = new ArrayList<CheckRule>();

	public CheckRules addCheckRules(CheckRule... checkRules) {
		if (checkRules != null) {
			for (CheckRule checkRule : checkRules) {
				addCheckRule(checkRule);
			}
		}
		return this;
	}

	public List<CheckRule> getCheckRulesWith(int row, int col) {
		if (row == -1 || col == -1) {
			throw new IllegalArgumentException();
		}
		List<CheckRule> result = new ArrayList<CheckRule>();
		for (CheckRule checkRule : checkRules) {
			if (checkRule.getCheckArea().contains(row, col)) {
				result.add(checkRule);
			}
		}
		return result;
	}

	public List<CheckRule> getAllCheckRules() {
		return checkRules;
	}

	public void setCheckRules(List<CheckRule> checkRules) {
		this.checkRules = checkRules;
	}

	public CheckRules addCheckRule(CheckRule checkRule) {
		this.checkRules.add(checkRule);
		return this;
	}

}
