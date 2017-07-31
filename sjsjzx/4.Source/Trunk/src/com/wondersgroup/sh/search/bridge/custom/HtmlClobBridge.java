package com.wondersgroup.sh.search.bridge.custom;

import java.sql.Clob;

import org.apache.commons.io.IOUtils;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.bridge.StringBridge;
import com.wondersgroup.sh.search.file.ExtractUtils;

public class HtmlClobBridge implements StringBridge {
	public String objectToString(Object object) {
		if( object == null ) 
			return null;
		
		try {
			String content = IOUtils.toString(((Clob)object).getCharacterStream());
			content = ExtractUtils.extractHtml(content, null);
			return (content != null) ? content.trim() : null;
		} 
		catch( Exception e ) {
			throw new SearchException("抽取Clob中的文本出错", e);
		}
	}

}
