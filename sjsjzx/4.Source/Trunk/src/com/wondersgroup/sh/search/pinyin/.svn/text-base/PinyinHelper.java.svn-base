package com.wondersgroup.sh.search.pinyin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class PinyinHelper {
	private static PinyinHelper instance;
	private ChinesePhraseToPinyinResource phrasePinyinResource;
	private ChineseWordToPinyinResource	wordPinyinResource;
	
	private PinyinHelper() throws IOException {
		this.phrasePinyinResource = ChinesePhraseToPinyinResource.getInstance();
		this.wordPinyinResource = ChineseWordToPinyinResource.getInstance();
	}

	public static PinyinHelper getInstance() throws IOException {
		if( instance == null ) {
			instance = new PinyinHelper();
		}
		return instance;
	}
	
	public String[] getPinyin(char c) {
		return this.wordPinyinResource.getPinyin(c);
	}
	
	private boolean isChineseWord(char ch) {
		return Character.toString(ch).matches("[\\u4E00-\\u9FA5]+");
	}
	
	private List splitPhrase(String src) {
		List retList = new ArrayList();
		char[] words = src.toCharArray();
		String str = "";
		boolean chineseMode = true;
		char c;
		for (int i = 0; i < words.length; i++) {
			c = words[i];
			if( this.isChineseWord(c) ) {
				if( !chineseMode ) {
					if( StringUtils.isNotEmpty(str) ) {
						retList.add(str);
						str = "";
					}
					chineseMode = true;
				}
				str += c;
			}
			else {
				if( chineseMode ) {
					if( StringUtils.isNotEmpty(str) ) {
						retList.add(str);
						str = "";
					}
					chineseMode = false;
				}
				str += c;
			}
		}
		
		if(StringUtils.isNotEmpty(str)) 
			retList.add(str);
		
		return retList;
	}
	
	private boolean isChineseString(String str) {
		return this.isChineseWord(str.charAt(0));
	}
	
	private List findPinyin(final String inPhrase) {
		List retList = new ArrayList();
		String phrase = new String(inPhrase);
		while( true ) {
			String pinyin = this.phrasePinyinResource.findPhrasePinyin(phrase);
			
			if( pinyin == null ) {
				String[] pinyins = this.wordPinyinResource.getPinyin(phrase.charAt(0));
				retList.add(pinyins != null ? pinyins[0] : phrase.charAt(0));
				phrase = phrase.substring(1, phrase.length());
			}
			else {
				String[] strs = ChinesePhraseToPinyinResource.getWordPinyins(pinyin);
				retList.addAll(Arrays.asList(strs));
				phrase = phrase.substring(strs.length, phrase.length());
			}
			
			if(StringUtils.isEmpty(phrase))
				break;
		}
		return retList;
	}
	
  public String getPinyin(String input, String separator) {
  	if( StringUtils.isBlank(input) ) 
  		return "";
  	
  	String src = input.replaceAll("\\s+", "");
  	List phrases = this.splitPhrase(src);
  	List list = new ArrayList();
		for (Iterator iterator = phrases.iterator(); iterator.hasNext();) {
			String phrase = (String) iterator.next();
			if( !this.isChineseString(phrase) ) 
				list.add(phrase);
			else
				list.addAll(this.findPinyin(phrase));
		}
  	
		return StringUtils.join(list.iterator(), separator);
	}      
    
  public String getPinyinHeadChar(String input, String separator) {
  	String pinyin = this.getPinyin(input, ChinesePhraseToPinyinResource.PinyinSeperator);
  	String[] strs = ChinesePhraseToPinyinResource.getWordPinyins(pinyin);
  	String retStr = "";
  	boolean first = true;
  	for (int i = 0; i < strs.length; i++) {
			if( first ) { 
				retStr += strs[i].charAt(0);
				first = false;
			}
			else {
				retStr += separator + strs[i].charAt(0);
			}
		}
  	return retStr;
  }
  
	public static void main(String[] args) throws Exception {      
		PinyinHelper helper = PinyinHelper.getInstance();
		String input = "长城银行卡人行道甲Ａ路人吕铝路";
		input = "中国共产主义青年团";
		// 得到带声调的全拼
		String pinyin = helper.getPinyin(input, "|");
		System.out.println(pinyin);
		// 转成没有声调的全拼
		pinyin = PinyinFormatter.convertToneMark2Unmarked(pinyin);
		System.out.println(pinyin);
		// 得到拼音首字母
		pinyin = helper.getPinyinHeadChar(input, "|");
		System.out.println(pinyin);
		// 得到单个字的所有拼音
		String[] pinyins = helper.getPinyin('行');
		System.out.println(Arrays.asList(pinyins));
	}      
}
