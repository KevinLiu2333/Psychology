/** 
 * @(#)Validator.java 2008-10-30
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
 * @version $Revision: 5495 $ 2008-10-30
 * @author $Date: 2008-10-31 11:46:16 +0800 (周五, 31 十月 2008) $ by $Author: Yaojp $
 * @since 1.0
 */
public interface Validator {

	void validate(String target) throws ValidationException;

}
