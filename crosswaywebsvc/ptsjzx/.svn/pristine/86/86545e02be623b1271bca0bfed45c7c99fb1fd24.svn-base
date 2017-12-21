package com.wonders.sjtb.utils;

import java.util.HashMap;
import java.util.Map;

import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 *社会信用代码证 检验 
 */
public class CreditCodeChecker {
	private static Log log=Logs.get();
    public static String isCreditCode = "true";  
    static String error_CreditCode = "社会信用代码有误";  
    static String error_CreditCode_min = "社会信用代码不足18位，请核对后再输！";  
    static String error_CreditCode_max = "社会信用代码大于18位，请核对后再输！";  
    static String error_CreditCode_empty ="社会信用代码不能为空！";  
    static int[] power = {1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28};  
    static char[] code = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','T','U','W','X','Y'};  
    public static String check_creditCode(String creditCode){
    	Map<String,Integer> datas =new HashMap<String,Integer>();
    	initDatas(code.length,datas);  
    	char[] pre17s=pre17(creditCode);
    	String flag="";
    	try{
    		 flag=isCreditCode(creditCode,datas,pre17s);
    	}catch(Exception e){
    		 flag="flase";
    	}
		return flag;
    }
    /** 
     * 判断是否是一个有效的社会信用代码 
     * @param creditCode 
     * @return 
     */  
    static String isCreditCode(String creditCode,Map<String,Integer> datas,char[] pre17s){  
        if("".equals(creditCode)||" ".equals(creditCode)){  
        	log.info("社会信用代码："+creditCode+" 校验结果：" +error_CreditCode_empty);  
            return error_CreditCode_empty;  
        }else if(creditCode.length()<18){  
        	log.info("社会信用代码："+creditCode+" 校验结果：" +error_CreditCode_min);  
            return  error_CreditCode_min;  
        }else if(creditCode.length()>18){  
        	log.info("社会信用代码："+creditCode+" 校验结果：" +error_CreditCode_max);  
            return  error_CreditCode_max;  
        }else{  
            int sum =  sum(pre17s,datas);  
            int temp = sum%31;  
            temp = temp==0?31:temp;
            log.info("社会信用代码："+creditCode+" 校验结果：" + code[31-temp]+" "+(creditCode.substring(17,18).equals(code[31-temp]+"")?"正确":error_CreditCode));  
            return creditCode.substring(17,18).equals(code[31-temp]+"")?isCreditCode:error_CreditCode;  
        }  
    }  
  
    /** 
     * @param chars 
     * @return 
     */  
    private static int sum(char[] chars,Map<String,Integer> datas){  
        int sum = 0;  
        for(int i=0;i<chars.length;i++){  
            int code = datas.get(chars[i]+"");  
            sum+=power[i]*code;  
        }  
        return sum;  
  
    }  
  
    /** 
     * 获取前17位字符 
     * @param creditCode 
     */  
    static  char[]  pre17(String creditCode){  
    	if(creditCode.length()>17){
    		String pre17 = creditCode.substring(0,17);  
            return  pre17.toCharArray();  
    	}else {
			return null;
		}
        
    }  
  
    /** 
     * 初始化数据 
     * @param count 
     */  
    static void  initDatas(int count,Map<String,Integer> datas){  
        for(int i=0;i<code.length;i++){  
            datas.put(code[i]+"",i);  
        }  
        System.out.println();  
    }
}
