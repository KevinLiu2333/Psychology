/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.util.Version;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.analysis.CJKDigitAnalyzer;
import com.wondersgroup.sh.search.bridge.BridgeFactory;
import com.wondersgroup.sh.search.bridge.FieldBridge;
import com.wondersgroup.sh.search.lucene.config.FieldInfo;

public class LuceneUtils {
	public static void addField(String name, Object value, Document document, FieldInfo fieldInfo) {
		FieldBridge fieldBridge = BridgeFactory.getBridgeByClass(value.getClass(), fieldInfo, null);
		fieldBridge.set(name, value, document, fieldInfo);
	}
	
	public static String objectToString(FieldInfo fieldInfo, Object obj) {
		FieldBridge fieldBridge = BridgeFactory.getBridgeByClass(obj.getClass(), fieldInfo, null);
		String value = BridgeFactory.extractTwoWayType(fieldBridge).objectToString(obj);
		if( value == null )	value = "";		
		return value;
	}
	
	public static String[] tokenize(Analyzer analyzer, String input) throws IOException {
  	TokenStream ts = null;
  	try {
  		List<String> tokens = new ArrayList<String>();
  		ts = analyzer.tokenStream(null, new StringReader(input));
  		CharTermAttribute token = (CharTermAttribute)ts.addAttribute(CharTermAttribute.class);
  		while( ts.incrementToken() ) {
  			tokens.add(new String(token.buffer(), 0, token.length()));
  		}
  		return (String[])tokens.toArray(new String[tokens.size()]);
  	}
  	finally {
  		try {
	  		if( ts != null ) {
	  			ts.close();
	  		}
  		}
	  	catch(IOException ex) {
	  	}
  	}		
	}
	
  public static final Version parseLuceneVersionString(final String matchVersion) {
    String parsedMatchVersion = matchVersion.toUpperCase(Locale.ENGLISH);
    
    // be lenient with the supplied version parameter
    parsedMatchVersion = parsedMatchVersion.replaceFirst("^(\\d)\\.(\\d)$", "LUCENE_$1$2");
    
    final Version version;
    try {
      version = Version.valueOf(parsedMatchVersion);
    } 
    catch (IllegalArgumentException iae) {
      throw new WdisException(WdisException.ErrorCode.SERVER_ERROR, 
      	"Invalid luceneMatchVersion '" + matchVersion +
        "', valid values are: " + Arrays.toString(Version.values()) +
        " or a string in format 'V.V'", iae, false);    
    }
    
    return version;
  }	
  
  public static void main(String[] args) throws IOException {
  	Analyzer analyzer = new CJKDigitAnalyzer(Constants.LUCENE_VERSION);
  	String input = "黄振中报道：我国民间,３１０１０５310105197107083611,本报北京１月３日讯  记者谢联辉Bill Chen,jfaskldjfasd;dfasfewfads"; 
  		/*"310105197107083611,本报北京１月３日讯  记者谢联辉、黄振中报道：我国民间首次江河源环境保护工程——“保护长江源,爱我大自然”活动今天拉开序幕。" +
  			"针对长江河源区草场退化、荒原沙化和野生动物被大量捕杀等触目惊心的事实，广大环保工作人员和新闻界人士呼吁";*/
  	String[] tokens = tokenize(analyzer, input);
  	System.out.println(Arrays.asList(tokens));
  }
}
