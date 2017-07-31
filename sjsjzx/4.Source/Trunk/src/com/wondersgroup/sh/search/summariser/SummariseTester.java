package com.wondersgroup.sh.search.summariser;

import java.io.FileInputStream;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class SummariseTester {
	private static final Logger logger = Logger.getLogger(SummariseTester.class);

	public void testSummarise() throws Exception {
		//String input = "Classifier4J is a java package for working with text. Classifier4J includes a summariser. A Summariser allows the summary of text. A Summariser is really cool. I don't think there are any other java summarisers.";
		String fileName = "c:/11.txt";
		String input = IOUtils.toString(new FileInputStream(fileName));
		ISummariser summariser = new SimpleSummariser(new PaodingAnalyzer());
		String result = summariser.summarise(input, 5);
		logger.info(result);
	}
	
	public static void main(String[] args) {
		try {
			SummariseTester tester = new SummariseTester();
			tester.testSummarise();
		}
		catch(Exception ex) {
			logger.error(ex);
		}
	}
}
