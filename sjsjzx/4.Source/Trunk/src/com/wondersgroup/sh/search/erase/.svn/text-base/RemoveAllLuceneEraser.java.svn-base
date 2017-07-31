package com.wondersgroup.sh.search.erase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;

import com.wondersgroup.sh.search.IndexException;

public class RemoveAllLuceneEraser implements Eraser, LuceneEraser {
	private static final Logger logger = Logger.getLogger(RemoveAllLuceneEraser.class);

	private IndexWriter indexWriter;
	
	public int clearIndex() {
		IndexReader indexReader = null;
		try {
			Directory directory = indexWriter.getDirectory();
			if( !IndexReader.indexExists(directory) )
				return 0;
			
			indexReader = IndexReader.open(directory, false);
			int count = indexReader.numDocs();
			this.indexWriter.deleteAll();
			this.indexWriter.commit();
			return count;
		}
 		catch(Exception e) {
 			try {
				this.indexWriter.rollback();
			} 
 			catch (IOException e1) {
 				logger.warn("回滚IndexWriter出错。", e);
			}
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

	public void setIndexReader(IndexWriter writer) {
		this.indexWriter = writer;
	}
}
