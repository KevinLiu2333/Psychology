/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.test;

import java.io.Reader;

import jeasy.analysis.MMAnalyzer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cjk.CJKTokenizer;

import com.wondersgroup.sh.search.analysis.CJKDigitAnalyzer;
import com.wondersgroup.sh.search.analysis.CJKDigitFilter;
import com.wondersgroup.sh.search.analysis.CJKDigitLengthAnalyzerFactory;
import com.wondersgroup.sh.search.lucene.Constants;
import com.wondersgroup.sh.search.lucene.LuceneUtils;

public class TokenizerTester {
	private static final Logger logger = Logger.getLogger(TokenizerTester.class);

  public String tokenize(Analyzer analyzer, String input, char separator) throws Exception {
  	String[] terms = LuceneUtils.tokenize(analyzer, input);
  	return StringUtils.join(terms, separator);
  }
  
  public String mmSegment(String input) throws Exception {
  	return new MMAnalyzer().segment(input, "|");
  }
  
  public static void main(String[] args) {
  	TokenizerTester tester = new TokenizerTester();
  	String input = null;

  	input = "喜欢自驾出行1234567890,24178888的快感，而且越玩心越野，从300km范围逐步扩展，可是有一个问题也困扰着我，" +
			"那就是自驾出行住宿的问题，特别是长途出行，由于路况、天气、人员等原因，并不能按照设计好的计划实施，" +
			"特别是晚上到一个生地方，找到合意的住宿确实比较费劲，想请教一下，高速上的服务区住宿怎么样？" +
			"安全么？干净么？价格怎么样？";

  	//input = "\"迎世博600天行动计划\"北蔡绿川地区污水纳管工程";
  	//input = "中华人民共和国";
  	//input = "The quick brown fox jumped over the lazy dogs";
  	//input = "XY&Z Corporation - xyz@example.com";
  	//input = "churujingdengji";
  	//input = "churujingdengji$1234";
  	input = "人:noshame(无泪),信区:FDU_E.S.E.标题:APELL简介及在环境影响评价中的应用发信站:日月光华站(WedNov1510:24:042000)," +
  			"转信（北京大学环境工程研究所北京100080）张辉（国家环保局环境工程评估中心北京100012）摘要：" +
  			"预防和应,130106199911193611,24178888河北省石家庄市郊区";
  	//input = "沪浦府土[2011]396号";
  	Analyzer analyzer = new CJKAnalyzer(Constants.LUCENE_VERSION);
  	//analyzer = new CJKDigitLengthAnalyzerFactory().create();
  	try {
  		String word = tester.tokenize(analyzer, input, '/');
  		logger.info(word);
  	}
  	catch(Exception ex) {
  		logger.error("tokenize error.", ex);
  	}
  }
}
