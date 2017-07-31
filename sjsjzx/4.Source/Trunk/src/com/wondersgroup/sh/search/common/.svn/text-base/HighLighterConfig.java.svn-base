package com.wondersgroup.sh.search.common;

import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

public class HighLighterConfig {
	public static final int FRAGMENT_SIZE = 40;
	public static final int MAX_FRAGMENTS = 4;
	public static final String FRAGMENT_SEPARATOR = "...";
	public static final Formatter DEFAULT_FORMATTER = new SimpleHTMLFormatter("<FONT COLOR=RED>", "</FONT>");
	
	private int fragmentSize = FRAGMENT_SIZE;
	private int maxFragments = MAX_FRAGMENTS;
	private String separator = FRAGMENT_SEPARATOR;
	private Formatter formatter = DEFAULT_FORMATTER;
	
	public HighLighterConfig() {
	}

	public HighLighterConfig(int fragmentSize, int maxFragments, String separator, Formatter formatter) {
		this.fragmentSize = fragmentSize;
		this.maxFragments = maxFragments;
		this.separator = separator;
		this.formatter = formatter;
	}

	public HighLighterConfig(Formatter formatter) {
		this.formatter = formatter;
	}

	public static HighLighterConfig createDefault() {
		return new HighLighterConfig();
	}
	
	public int getFragmentSize() {
		return fragmentSize;
	}

	public void setFragmentSize(int fragmentSize) {
		this.fragmentSize = fragmentSize;
	}

	public int getMaxFragments() {
		return maxFragments;
	}

	public void setMaxFragments(int maxFragments) {
		this.maxFragments = maxFragments;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public Formatter getFormatter() {
		return formatter;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}
}
