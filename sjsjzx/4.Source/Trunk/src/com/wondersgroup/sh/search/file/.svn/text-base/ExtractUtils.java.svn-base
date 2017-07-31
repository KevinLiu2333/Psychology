package com.wondersgroup.sh.search.file;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ExtractUtils {
	private static final Logger logger = Logger.getLogger(ExtractUtils.class);

	public static String extractHtml(String html, String charset) throws ParserException  {
		StringBuffer sb = new StringBuffer("");
		Parser parser = Parser.createParser(html, charset);
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
				if (HtmlExtractor.tagNameSet.contains(tag.getTagName())) {
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
		return new String(sb);
	}
	
	public static void main(String[] args) {
		try {
			InputStream is = new FileInputStream("D:/search/test/txt/鬼吹灯1-100.txt");
			String encoding = null; //"UTF-8";
			String html = IOUtils.toString(is, encoding);
			//logger.info(html);
			String txt = extractHtml(html, encoding);
			logger.info(txt);
		}
		catch(Exception ex) {
			logger.error("Exception.", ex);
		}
	}
}
