package com.wondersgroup.sh.search.bridge.custom;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.bridge.StringBridge;
import com.wondersgroup.sh.search.file.ExtractUtils;

public class HtmlStringBridge implements StringBridge {
	public String objectToString(Object object) {
		if( object == null ) 
			return null;
		
		try {
			String content = ExtractUtils.extractHtml((String)object, null);
			return (content != null) ? content.trim() : null;
		} 
		catch( Exception e ) {
			throw new SearchException("抽取String中的文本出错", e);
		}
	}
}
