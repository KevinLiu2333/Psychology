package com.wonders.wddc.tiles.tools;

import java.util.Date;

import org.apache.commons.lang.StringUtils;


public class CardUtils {

    /**
     * 通过身份证号码得到性别代码.
     * 01=男|02=女
     * @param cardNum
     * @return
     */
    public static String getSexCode(String cardNum) {
        String result = null;
        if(StringUtils.isNotEmpty(cardNum)){
            if(cardNum.length() == 18){
                int gender = Integer.parseInt(cardNum.substring(16, 17));
                if(gender%2==1){//倒数第二位奇数为男,偶数为女.
                    result = "1";
                }else{
                    result = "2";
                }
            }else if(cardNum.length() == 15){
                int gender = Integer.parseInt(cardNum.substring(14, 15));
                if(gender%2==1){//倒数第二位奇数为男,偶数为女.
                    result = "1";
                }else{
                    result = "2";
                }
            }
        }
        return result;
    }
    
    /**
     * 通过身份证号码得到性别描述.
     * @param cardNum
     * @return
     */
    public static String getSexDisplay(String cardNum){
        String result = getSexCode(cardNum);
        if("1".equals(result)){
            return "男";
        }else if("2".equals(result)){
            return "女";
        } 
        return result;
    }
    
    /**
     * 得到年龄.
     * @param cardNum
     * @return
     */
    public static Long getAge(String cardNum) {
        Long result = null;
        if(StringUtils.isNotEmpty(cardNum)){
            Long year = null;
            if(cardNum.length() == 18){
                year = Long.valueOf(cardNum.substring(6, 10));
             }else if(cardNum.length() == 15){
                long birthYear = Long.parseLong(cardNum.substring(6, 8));
                year = Long.valueOf(1900+birthYear);
            }
            long curYear = Long.parseLong(DateUtils.getYear());
            result = Long.valueOf(curYear-year);
        }
        return result;
    }
    
    /**
     * 得到生日.
     * @param cardNum
     * @return
     */
    public static Date getBirthday(String cardNum) {
        String birthdayStr = null;
        if(StringUtils.isNotEmpty(cardNum)){
            if(cardNum.length() == 18){
            	birthdayStr = cardNum.substring(6, 14);
            }else if(cardNum.length() == 15){
            	 birthdayStr = String.valueOf(19000000+Long.valueOf(cardNum.substring(6, 12)));
            }
        }
        Date birthday = DateUtils.string2Date(birthdayStr, "yyyyMMdd");
        return birthday;
    }
}
