package com.wondersgroup.sh.search.pinyin;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

class ChinesePhraseToPinyinResource {
	private static final Logger logger = Logger.getLogger(ChinesePhraseToPinyinResource.class);
	private static ChinesePhraseToPinyinResource instance;
	private ChineseWordToPinyinResource wordPinyinResource;
	private Map phraseToPinyinMap;

	static final String PinyinSeperator = "|";
	
	private ChinesePhraseToPinyinResource() throws IOException {
		this.wordPinyinResource = ChineseWordToPinyinResource.getInstance();
		initResource();
	}
	
	static synchronized ChinesePhraseToPinyinResource getInstance() throws IOException {
		if( instance == null ) {
			instance = new ChinesePhraseToPinyinResource();
		}
		return instance;
	}
	
	private void initResource() throws IOException {
  	final String resourceName = "/dic/chinese-phrase-pinyin.dic";
		InputStream is = null;
		try {
			is = ResourceHelper.getResourceInputStream(resourceName);
			List lines = IOUtils.readLines(is);
			this.phraseToPinyinMap = new HashMap();
			for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
				String line = (String) iterator.next();
				if( StringUtils.isBlank(line) || line.startsWith("#") )
					continue;
				
				String[] strs = line.split("=");
				String splitedPinyin = this.splitPinyin(strs[0].trim(), strs[1].trim(), PinyinSeperator);
				this.phraseToPinyinMap.put(strs[0].trim(), splitedPinyin);
			}
		}
		finally {
			IOUtils.closeQuietly(is);
		}
  }

	private String splitPinyin(String phrase, String inPinyinStr, String separator) {
		List list = new ArrayList();
		char[] chars = phrase.toCharArray();
		String pinyinStr = new String(inPinyinStr);
		for (int i = 0; i < chars.length; i++) {
			String[] wordPinyins = this.wordPinyinResource.getPinyin(chars[i]);
			if( wordPinyins == null )
				return inPinyinStr;

			for (int j = 0; j < wordPinyins.length; j++) {
				if( pinyinStr.startsWith(wordPinyins[j]) ) {
					list.add(wordPinyins[j]);	
					pinyinStr = pinyinStr.substring(wordPinyins[j].length(), pinyinStr.length());
					break;
				}
			}
		}
		return StringUtils.join(list.iterator(), separator);
	}
	
  String getPinyin(String chinesePhrase) {
  	return (String)this.phraseToPinyinMap.get(chinesePhrase);
  }

  String findPhrasePinyin(final String phrase) {
		String pinyin = this.getPinyin(phrase);
		if( pinyin == null && phrase.length() > 2) {
			String subPhrase = phrase.substring(0, phrase.length() - 1);
			return findPhrasePinyin(subPhrase);
		}
		else {
			return pinyin;
		}
	}
	
  static String[] getWordPinyins(String pinyinStr) {
  	return pinyinStr.split("\\" + PinyinSeperator);
  }
  
	Map getPhraseToPinyinMap() {
		return phraseToPinyinMap;
	}

	public static void main(String[] args) throws IOException {
		ChinesePhraseToPinyinResource resource = ChinesePhraseToPinyinResource.getInstance();
		String pinyin = resource.getPinyin("长城");
		System.out.println(pinyin);
		
		//System.out.println(PinyinHelper.toHanyuPinyinStringArray('Ａ'));
		pinyin = resource.findPhrasePinyin("银行卡");
		System.out.println(pinyin);
	}
}
