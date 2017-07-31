/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.file;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.util.NodeList;

import com.wondersgroup.sh.search.util.StreamHelper;

/**
 * 取得 HTML 文件正文内容。使用htmlparser 1.5 实现。<br>
 */

public class HtmlExtractor extends AbstractExtractor {//modified by fengjia
	public static final Set tagNameSet = new HashSet();
	static {
		tagNameSet.add("SCRIPT");
		tagNameSet.add("STYLE");
	}

	//@SuppressWarnings("serial")
	public String extract(InputStream stream) throws FileExtractException {
		try {
			byte[] by = StreamHelper.toByteArray(stream);
			String content = new String(by, "UTF-8");
			String pattern = "<meta .*?content=\".*?charset=(.*?)\"";
			Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(content);
			String charset = "UTF-8";
			if (m.find()) {
				charset = m.group(1);
			}
			if (!"UTF-8".equalsIgnoreCase(charset) && !"UTF8".equalsIgnoreCase(charset)) {
				content = new String(by, charset);
			}

			StringBuffer sb = new StringBuffer("");
			Parser parser = Parser.createParser(content, charset);
			boolean extract = true;
			NodeList nl = parser.extractAllNodesThatMatch(new NodeFilter() {
				public boolean accept(Node node) {
					return true;
				}
			});
			
			for (int i = 0; i < nl.size(); i++) {
				Node node = nl.elementAt(i);
				if (node instanceof Tag) {
					Tag tag = (Tag) node;
					if (tagNameSet.contains(tag.getTagName())) {
						extract = tag.isEndTag();
					}
				} else if (node instanceof TextNode) {
					if (extract) {
						sb.append(node.toPlainTextString().trim() + " ");
					} else {
						extract = true;
					}
				}
			}
			return  standardStr(new String(sb));
		}
		catch(Throwable ex) {
			throw new FileExtractException(ex);
		}
	}
	/**
	 * 取得map形式的文件内容，对于网页文件，存content,keywords,title.
	 * @author fengjia
	 */
	public Map<String,String> mapExtract(InputStream stream) throws FileExtractException {
		try {
			byte[] by = StreamHelper.toByteArray(stream);
			String content = new String(by, "UTF-8");
			String pattern = "<meta .*?content=\".*?charset=(.*?)\"";
			Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(content);
			String charset = "UTF-8";
			if (m.find()) {
				charset = m.group(1);
			}
			if (!"UTF-8".equalsIgnoreCase(charset) && !"UTF8".equalsIgnoreCase(charset)) {
				content = new String(by, charset);
			}

			StringBuffer sbCon = new StringBuffer("");
			StringBuffer sbTit = new StringBuffer("");
			StringBuffer sbKey = new StringBuffer("");
			String extractFlag = "c";//n:都不存;c:sbCon;t:sbTit;k:sbKey
			Map<String,String> extractMap = new HashMap<String,String>();
			Parser parser = Parser.createParser(content, charset);
			NodeList nl = parser.extractAllNodesThatMatch(new NodeFilter() {
				public boolean accept(Node node) {
					return true;
				}
			});
			for (int i = 0; i < nl.size(); i++) {
				Node node = nl.elementAt(i);
				if (node instanceof Tag) {
					Tag tag = (Tag) node;
					if (tagNameSet.contains(tag.getTagName())) {
						if(tag.isEndTag()){
							extractFlag = "c";
						}else{
							extractFlag = "n";
						}
					}else{
						if("TITLE".equals(tag.getTagName())){
							if(tag.isEndTag()){
								extractFlag = "c";
							}else{
								extractFlag = "t";
							}
						}else if("META".equals(tag.getTagName())){
							MetaTag metaTag = (MetaTag)tag;
							if("keywords".equals(metaTag.getMetaTagName())){
								sbKey.append(metaTag.getMetaContent().trim() + " ");
							}else if("description".equals(metaTag.getMetaTagName())){
								sbCon.append(metaTag.getMetaContent().trim() + " ");
							}
						}
					}
				} else if (node instanceof TextNode) {
					if ("c".equals(extractFlag)) {
						sbCon.append(node.toPlainTextString().trim() + " ");
					}else if("t".equals(extractFlag)){
						sbTit.append(node.toPlainTextString().trim() + " ");
					}
				}
			}
			extractMap.put("content", standardStr(new String(sbCon)));
			extractMap.put("title", standardStr(new String(sbTit)));
			extractMap.put("keywords", standardStr(new String(sbKey)));
			return extractMap;
		}
		catch(Throwable ex) {
			throw new FileExtractException(ex);
		}
	}
}
