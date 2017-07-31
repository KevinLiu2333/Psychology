/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

/**
 * Defined projection constants.
 */
public interface ProjectionConstants {
	
	/** The Lucene document returned by a search. */
	public String DOCUMENT = "__Lucene_Document";

	/** The legacy document's score from a search. */
	public String SCORE = "__Lucene_Score";

	/** Lucene Document id Experimental: If you use this feature, please speak up in the forum <p/> Expert: Lucene document id can change overtime between 2 different IndexReader opening. */
	public String DOCUMENT_ID = "__Lucene_DocumentId";
	
	/** Lucene {@link org.apache.lucene.search.Explanation} object describing the score computation for the matching object/document This feature is relatively expensive, do not use unless you return a limited amount of objects (using pagination) To retrieve explanation of a single result, consider retrieving {@link #DOCUMENT_ID} and using fullTextQuery.explain(int) */
	public String EXPLANATION = "__Lucene_Explanation";
}
