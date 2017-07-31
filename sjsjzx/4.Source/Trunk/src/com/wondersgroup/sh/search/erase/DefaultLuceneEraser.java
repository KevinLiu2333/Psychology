package com.wondersgroup.sh.search.erase;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;

import com.wondersgroup.sh.search.IndexException;

public class DefaultLuceneEraser implements Eraser, ParameterizedEraser, LuceneEraser {
	private static final Logger logger = Logger.getLogger(DefaultLuceneEraser.class);

	private String[] fields;
	private String[] fieldValues;
	private IndexWriter indexWriter;
	
	public int clearIndex() {
		IndexReader indexReader = null;
		try {
			Directory directory = indexWriter.getDirectory();
			indexReader = IndexReader.open(directory, false);
			int count = 0;
			for (int i = 0; i < fields.length; i++) {
				Term term = new Term(fields[i], fieldValues[i]);
				//logger.debug("term=" + term.toString());
				count += indexReader.docFreq(term);
				indexWriter.deleteDocuments(term);
			}
			
			return count;
		}
 		catch(Exception e) {
			throw new IndexException("删除索引出错。", e);
		}
 		finally {
 			try {
	 			if( indexReader != null ) {
	 				indexReader.close();
	 			}
 			}
			catch (IOException e) {
				logger.warn("关闭index reader出错。", e);
			}
 		}
	}

	public void setIndexReader(IndexWriter indexWriter) {
		this.indexWriter = indexWriter;
	}
	
	public void setParameterValues(Map parameters) {
		if ( parameters.containsKey( "fields" ) ) {
			fields = ((String)parameters.get( "fields" )).split("[,;]");
		}
		if ( parameters.containsKey( "values" ) ) {
			fieldValues = ((String)parameters.get( "values" )).split("[,;]");
		}
	}
}
