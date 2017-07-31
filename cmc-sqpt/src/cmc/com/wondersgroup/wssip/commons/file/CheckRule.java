/** 
 * @(#)ICheckRule.java 2008-11-20
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

/**
 * @author Yao Jianping
 * @version $Revision: 7074 $ 2008-11-20
 * @author $Date: 2008-11-21 15:33:58 +0800 (周五, 21 十一月 2008) $ by $Author: Yaojp $
 * @since 1.0
 */
public interface CheckRule {

	public Validator getValidator();

	public Area getCheckArea();

}
