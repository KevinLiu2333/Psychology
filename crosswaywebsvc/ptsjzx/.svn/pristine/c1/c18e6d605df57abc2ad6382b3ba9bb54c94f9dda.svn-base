package com.wonders.dp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nutz.lang.Strings;

import com.wonders.dp.common.Constants;

/**
 * 比较两个地址是否相同的工具类.
 */
public class AddressUtils {
	
	public static boolean isDifAddress(String address, String businessAddress) {
		boolean flag = false;
		if(!Strings.isEmpty(address) && !Strings.isEmpty(businessAddress)){
			String qu = Constants.QU;
			String zhen = Constants.ZHEN;
			String lu = Constants.LU;
			String jie = Constants.JIE;
			String nong = Constants.NONG;
			String hao = Constants.HAO;
			String shi = Constants.SHI;
			boolean f1 = isDifAddressDetail(address, businessAddress, qu);
			if(f1){
				boolean f2 = isDifAddressDetail(address, businessAddress, zhen);
				boolean f3 = isDifAddressDetail(address, businessAddress, lu);
				boolean f4 = isDifAddressDetail(address, businessAddress, jie);
				//如果街道相等  or 街道且路相等  or 镇且路相等
				if(f4 || f3 || (f3 && f2)){
					boolean f5 = isDifAddressDetail(address, businessAddress, nong);//弄
					boolean f6 = isDifAddressDetail(address, businessAddress, hao);//号
					boolean f7 = isDifAddressDetail(address, businessAddress, shi);//室
					if(f6 || f7 || (f5 && f6) || (f5 && f7)){
						flag = true;
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 比较code前两个子是否相同.
	 */
	private static boolean isDifAddressDetail(String address, String businessAddress, String code){
		boolean flag = false;
		int index1 = address.indexOf(code);
		int index2 = businessAddress.indexOf(code);
		
		if(index1 != -1 && index2 != -1){
			String address1 = "";
			String address2 = "";
			if((index1 - 2) != -1 && (index2 - 2) != -1){
				try {
					address1 = address.substring(index1 - 2, index1);
					address2 = businessAddress.substring(index2 - 2, index2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			if(Constants.NONG.equals(code) || Constants.HAO.equals(code) || Constants.SHI.equals(code)){
				int index3 = address.indexOf(code);
				int index4 = businessAddress.indexOf(code);
				if((index3 - 3) > 0 && (index4 - 3) > 0){
					address1 = address.substring(index3 - 3, index3); 
					address2 = businessAddress.substring(index4 - 3, index4);
				}
				String regEx = "[^0-9]";
				Pattern p = Pattern.compile(regEx);
				Matcher m1 = p.matcher(address1); 
				Matcher m2 = p.matcher(address2);
				if(m1.replaceAll("").trim().equals(m2.replaceAll("").trim())){
					flag = true;
				}
			}else if(!Strings.isEmpty(address1) && !Strings.isEmpty(address2)){
				if(address1.equals(address2)){
					flag = true;
				}
			}
		}
		 
		return flag;
	}

}
