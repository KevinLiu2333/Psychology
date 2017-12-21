package com.wonders.wddc.tiles.dic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.nutz.json.Json;
import org.nutz.lang.Files;

public class DicDataUtils {

	/**
	 * 字典池
	 */
	private static Map<Integer, Map<String, String>> dic = new HashMap<Integer, Map<String, String>>();

	private static DicDataUtils dicDataUtils = null;

	private DicDataUtils() {
	}

	public static DicDataUtils getInstance() {
		if (dicDataUtils == null) {
			synchronized (DicDataUtils.class) {
				if (dicDataUtils == null)
					dicDataUtils = new DicDataUtils();
			}
		}
		return dicDataUtils;
	}

	/**
	 * 从指定的字典中取得某个KEY对应的值
	 * @param dicCode 字典编号
	 * @param dicKey KEY键
	 * @return 字典值
	 */
	public String getDicData(Integer dicCode, String dicKey) {
		if (dic.containsKey(dicCode)) {
			Map<String, String> getDictionary = dic.get(dicCode);
			return getDictionary.get(dicKey);
		}
		return null;
	}
	
	/**
	 * 根据字典Code取得对应的字典
	 * 
	 * @param dicCode 字典Code
	 * @return 字典Map
	 */
	public Map<String, String> getDic(Integer dicCode) {
		if (dic.containsKey(dicCode)) {
			Map<String, String> getDictionary = dic.get(dicCode);
			return getDictionary;
		}
		return null;
	}

	/**
	 * 向字典池中加入字典
	 * 
	 * @param dicCode
	 * @param dicMap
	 */
	public void addDic(Integer dicCode, Map<String, String> dicMap) {
		if (dic.containsKey(dicCode)) {
			dic.remove(dicCode);
		}
		dic.put(dicCode, dicMap);
	}
	
	/**
	 * 清空所有字典
	 */
	public void clear() {
		dic.clear();
		dicDataUtils = null;
	}
	
	/**
	 * 将字典以JSON格式输出到指定目录下
	 * 
	 * @param filePath
	 */
	public void toJson(String filePath) {
		if (dic != null) {
			String json = null;
			synchronized (dic) {
				json = Json.toJson(dic);
			}
			Files.write(new File(filePath), json);
		}
	}
	/**
	 * 删除某项字典
	 * @param dicid
	 */
	public void deleteDic(Integer dicid){
		if(dic.containsKey(dicid)){
			dic.remove(dicid);
		}
	}
	
	public void init() {
		dic.clear();
	}
	
	/**
     * 根据传入的字典编号和键值得到描述信息。
     * <p>
     * 
     * @param dicId
     *            字典编号
     * @param itemKey
     *            键值
     * @return 描述信息
     */
    public String getDicItemName(int dicId, Object itemKey) {
        if (itemKey == null) {
            return null;
        }

        Map<String, String> map = getDic(dicId);
        String itemName = map.get(String.valueOf(itemKey));

        if (StringUtils.isEmpty(itemName)) {
            return itemKey.toString();
        } else {
            return itemName;
        }
    }
}
