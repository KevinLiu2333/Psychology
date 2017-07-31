package com.wondersgroup.sh.search.pinyin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

class ChineseWordToPinyinResource {
	private static ChineseWordToPinyinResource instance;
	private MultiValueMap wordToPinyinMap;
	
	private ChineseWordToPinyinResource() throws IOException {
		initResource();
	}
	
	static synchronized ChineseWordToPinyinResource getInstance() throws IOException {
		if( instance == null ) {
			instance = new ChineseWordToPinyinResource();
		}
		return instance;
	}
	
	private void initResource() throws IOException {
  	final String resourceName = "/dic/chinese-word-pinyin.dic";
		InputStream is = null;
		try {
			is = ResourceHelper.getResourceInputStream(resourceName);
			List lines = IOUtils.readLines(is);
			this.wordToPinyinMap = new MultiValueMap();
			for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
				String line = (String) iterator.next();
				if( StringUtils.isBlank(line) )
					continue;
				
				String[] strs = line.split("=");
				this.wordToPinyinMap.put(strs[0].charAt(0), strs[1].trim());
			}
		}
		finally {
			IOUtils.closeQuietly(is);
		}
  }

	String[] getPinyin(char chineseWord) {
		List list = (List)this.wordToPinyinMap.get(chineseWord);
		if( list != null )
			return (String[])list.toArray(new String[list.size()]);
		else
			return null;
	}
	
	public MultiValueMap getWordToPinyinMap() {
		return wordToPinyinMap;
	}

	public static void main(String[] args) throws IOException {
		ChineseWordToPinyinResource resource = ChineseWordToPinyinResource.getInstance();
		String[] pinyins = resource.getPinyin('长');
		System.out.println(Arrays.asList(pinyins));
	}
}
