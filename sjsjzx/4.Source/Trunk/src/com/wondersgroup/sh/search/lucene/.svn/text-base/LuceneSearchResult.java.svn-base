/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.wondersgroup.sh.search.SearchResult;

/**
 * The Class LuceneSearchResult.
 */
public class LuceneSearchResult extends SearchResult {
	private static final long serialVersionUID = -5256689520494628540L;

	/** The score. */
	protected float score;

	/**
	 * Instantiates a new lucene search result.
	 */
	public LuceneSearchResult() {
		super();
	}

	/**
	 * Instantiates a new lucene search result.
	 * 
	 * @param score the score
	 */
	public LuceneSearchResult(float score) {
		super();
		this.score = score;
	}

	/* (non-Javadoc)
	 * @see com.wondersgroup.sh.search.SearchResult#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("score", score)
			.appendSuper(super.toString())
			.toString();
	}
	
	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public float getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 * 
	 * @param score the new score
	 */
	public void setScore(float score) {
		this.score = score;
	}
}
