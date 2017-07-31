package com.wondersgroup.sh.search.test;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.apache.lucene.util.LuceneTestCase;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.wondersgroup.sh.search.analysis.CJKDigitFilter;
import com.wondersgroup.sh.search.analysis.WordDelimiterFilter;;

public class AnalyzerTester {
	//@Test
	public void testWordDelimiterFilter() throws IOException {
		final CharArraySet protWords = new CharArraySet(LuceneTestCase.TEST_VERSION_CURRENT, new HashSet<String>(Arrays.asList("NUTCH")), false);
  
	  /* analyzer that uses whitespace + wdf */
	  /* @param generateWordParts If 1, causes parts of words to be generated: "PowerShot" => "Power" "Shot"
	   * @param generateNumberParts If 1, causes number subwords to be generated: "500-42" => "500" "42"
	   * @param catenateWords  1, causes maximum runs of word parts to be catenated: "wi-fi" => "wifi"
	   * @param catenateNumbers If 1, causes maximum runs of number parts to be catenated: "500-42" => "50042"
	   * @param catenateAll If 1, causes all subword parts to be catenated: "wi-fi-4000" => "wifi4000"
	   * @param splitOnCaseChange 1, causes "PowerShot" to be two tokens; ("Power-Shot" remains two parts regards)
	   * @param preserveOriginal If 1, includes original words in subwords: "500-42" => "500" "42" "500-42"
	   * @param splitOnNumerics 1, causes "j2se" to be three tokens; "j" "2" "se"
	   * @param stemEnglishPossessive If 1, causes trailing "'s" to be removed for each subword: "O'Neil's" => "O", "Neil"
		 */
	  Analyzer a = new Analyzer() {
	    @Override
	    public TokenStream tokenStream(String field, Reader reader) {
	      return new WordDelimiterFilter (
	          new MockTokenizer(reader, MockTokenizer.WHITESPACE, false),
	          1,	// generateWordParts
	          1, 	// generateNumberParts
	          0, 	// catenateWords
	          0, 	// catenateNumbers
	          0, 	// catenateAll
	          1, 	// splitOnCaseChange
	          0, 	// preserveOriginal
	          1, 	// splitOnNumerics
	          1, 	// stemEnglishPossessive
	          protWords);
	    }
	  };
	  
	  BaseTokenStreamTestCase.assertAnalyzesTo(a, "PowerShot", new String[]{"Power", "Shot"});
	}
	
	@Test
	public void testCJKDigitFilter() throws IOException {
		Analyzer a = new Analyzer() {
			@Override
			public TokenStream tokenStream(String field, Reader reader) {
	      return new CJKDigitFilter(new CJKTokenizer(reader), 8);
			}
		};
																						//   1 234567890		
		BaseTokenStreamTestCase.assertAnalyzesTo(a, "喜欢1234567890aa024178888真好看", 
				new String[]{
					"喜欢", 
					"12345678", 
					"23456789",
					"34567890",
					"aa",
					"02417888",
					"24178888",
					"真好",
					"好看"	},
				new int[]{0, 2,  3,  4,  12, 14, 15, 23, 24},
				new int[]{2, 10, 11, 12, 14, 22, 23, 25, 26}	
		);
		
		BaseTokenStreamTestCase.assertAnalyzesTo(a, "喜欢1234567890,024178888真好看", 
				new String[]{
					"喜欢", 
					"12345678", 
					"23456789",
					"34567890",
					"02417888",
					"24178888",
					"真好",
					"好看"	},
				new int[]{0, 2,  3,  4,  13, 14, 22, 23},
				new int[]{2, 10, 11, 12, 21, 22, 24, 25}	
		);

		BaseTokenStreamTestCase.assertAnalyzesTo(a, "喜欢1234567890,024178888真好看", 
				new String[]{
					"喜欢", 
					"12345678", 
					"23456789",
					"34567890",
					"02417888",
					"24178888",
					"真好",
					"好看"	},
				new int[]{0, 2,  3,  4,  13, 14, 22, 23},
				new int[]{2, 10, 11, 12, 21, 22, 24, 25}	
		);
	}
	
	//@Test
	public void testCJKAnalyzer() throws IOException {
		Analyzer a = new CJKAnalyzer(LuceneTestCase.TEST_VERSION_CURRENT);
		BaseTokenStreamTestCase.assertAnalyzesTo(a, "沪浦府土[2011]396号", new String[]{"陈侃", "aaa", "123"});
	}
}
