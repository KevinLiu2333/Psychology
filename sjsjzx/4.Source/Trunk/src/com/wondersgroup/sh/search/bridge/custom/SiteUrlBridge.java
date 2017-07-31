package com.wondersgroup.sh.search.bridge.custom;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.bridge.StringBridge;

public class SiteUrlBridge implements StringBridge {
	public String objectToString(Object object) {
		if( object == null ) 
			return null;
		
		try {
			String url = (String)object;
			String[] strs = url.split("/");
			return strs.length >= 2 ? strs[1] : "";
		} 
		catch( Exception e ) {
			throw new SearchException("抽取URL出错!", e);
		}
	}

	public static void main(String[] args) throws Exception {
		SiteUrlBridge bridge = new SiteUrlBridge();
		System.out.println(bridge.objectToString("/aaaa"));
	}
}
