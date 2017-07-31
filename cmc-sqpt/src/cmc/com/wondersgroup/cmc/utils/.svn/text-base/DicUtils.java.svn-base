/** 
 * @(#)DicUtils.java Jun 22, 2010
 * 
 * Copyright (c) 1995-2010 Wonders Information Co.,Ltd. 
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

package com.wondersgroup.cmc.utils;

import java.util.ArrayList;
import java.util.List;

import com.wondersgroup.framework.util.CollectionUtils;
import com.wondersgroup.wssip.commons.dictionary.DictionaryUtils;
import com.wondersgroup.wssip.commons.dictionary.codes.bo.Aa09;
import com.wondersgroup.wssip.commons.dictionary.codes.bo.Aa10;
import com.wondersgroup.wssip.util.StringTools;

/**
 * @author xieguojun
 * @version $Revision$ Jun 22, 2010
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class DicUtils {

	/**
	 * 根据dateStr过滤字典
	 * 
	 * @param aa10s
	 * @param dateStr 格式yyyyMMdd
	 * @return
	 */
	public static List<Aa10> filterByDate(List<Aa10> aa10s, String dateStr) {

		if (!StringTools.hasText(dateStr))
			return aa10s;

		List<Aa10> result = new ArrayList<Aa10>();

		for (Aa10 aa10 : aa10s) {

			if (dateStr.compareTo(aa10.getInDate()) <= 0 && dateStr.compareTo(aa10.getOutDate()) >= 0) {
				result.add(aa10);
			}
		}

		return result;
	}

	/**
	 * 根据codeType和code查找父节点
	 * 
	 * @param codeType
	 * @param sonCode
	 * @return
	 */
	public static Aa10 getParentAa10(String codeType, String sonCode) {

		Aa09 aa09 = DictionaryUtils.getCodeService().getCodeTypeByCode(codeType);
		Aa10 aa10 = DictionaryUtils.getCodeService().getCodeInfoByTypeAndCode(aa09, sonCode);

		return aa10.getParent();
	}

	public static String codeToName(String codeType, String code) {
		Aa09 aa09 = DictionaryUtils.getCodeService().getCodeTypeByCode(codeType);
		Aa10 aa10 = DictionaryUtils.getCodeService().getCodeInfoByTypeAndCode(aa09, code);

		return aa10 != null ? aa10.getName() : code;
	}

	public static String nameToCode(String codeType, String name) {
		Aa09 aa09 = DictionaryUtils.getCodeService().getCodeTypeByCode(codeType);
		List<Aa10> list = DictionaryUtils.getCodeService().getAllCodes(aa09);

		if (CollectionUtils.isNotEmpty(list)) {
			for (Aa10 aa10 : list) {
				if (name.equals(aa10.getName())) {
					return aa10.getCode();
				}
			}
		}

		return null;
	}

}
