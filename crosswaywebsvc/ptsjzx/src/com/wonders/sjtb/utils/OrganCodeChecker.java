package com.wonders.sjtb.utils;

import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public class OrganCodeChecker {
	private static Log log = Logs.get();
	public static String isOrganCode = "true";
	public static String errorCode = "错误的组织机构代码";

	public static String check(String organcode) {
		if (Strings.isEmpty(organcode)||organcode.length()!=9) {
			log.info(errorCode);
			return errorCode;
		}
		int[] ww = { 3, 7, 9, 10, 5, 8, 4, 2 };
		int[] cc = new int[8];
		int DD = 0;
		int C9 = 0;
		for (int i = 0; i < 8; i++) {
			cc[i] = organcode.charAt(i);
			if (47 < cc[i] && cc[i] < 58)
				cc[i] = cc[i] - 48;
			else
				cc[i] = cc[i] - 65;
		}
		for (int i = 0; i < 8; i++) {
			DD += cc[i] * ww[i];
		}
		C9 = 11 - DD % 11;
		char chr9=0;
		if (C9 == 10) {
			chr9='X';
		} else if (C9 == 11) {
			chr9=48;
		} else {
			chr9=(char)(C9 + 48);
		}
		if(organcode.charAt(8)==chr9){
			log.info(organcode+" 正确的组织机构代码");
			return isOrganCode;
		}else {
			log.info(organcode+" "+errorCode);
			return errorCode;
		}
	}
}
