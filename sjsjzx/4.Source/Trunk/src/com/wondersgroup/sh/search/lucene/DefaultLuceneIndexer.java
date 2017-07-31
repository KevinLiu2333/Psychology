/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.wondersgroup.sh.search.erase.Eraser;
import com.wondersgroup.sh.search.erase.LuceneEraser;
import com.wondersgroup.sh.search.erase.ParameterizedEraser;
import com.wondersgroup.sh.search.erase.RemoveAllLuceneEraser;
import com.wondersgroup.sh.search.lucene.config.EraserInfo;
import com.wondersgroup.sh.search.lucene.config.FetcherInfo;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.util.AnalyzerHelper;
import com.wondersgroup.sh.search.util.ReflectHelper;

public class DefaultLuceneIndexer implements LuceneIndexer {
	private static final Logger logger = Logger.getLogger(DefaultLuceneIndexer.class);
	protected String indexId;
	protected LuceneConfiguration configuration;
	
	public DefaultLuceneIndexer(LuceneConfiguration configuration, String indexId) {
		this.indexId = indexId;
		this.configuration = configuration;
	}
	
	public static int clearIndex(Directory directory) throws Exception {
		if( !IndexReader.indexExists(directory) ) 
			return 0;
		
		logger.info("开始删除旧索引记录。");
		long start = System.currentTimeMillis();
		IndexReader reader = null;
		int total = 0;
		try {
			reader = IndexReader.open(directory, false);
			total = reader.numDocs();
			for (int i = 0; i < total; i++) {
				reader.deleteDocument(i);
			}
			logger.info("共删除" + total + "个文档。耗时" + (System.currentTimeMillis() - start) + "毫秒");	
			return total;
		} 
		finally {
			try {
				if( reader != null )
					reader.close();
			} 
			catch (IOException e) {
				logger.warn("关闭index reader出错。", e);
			}
		}
	}
	
	private int clearIndex(IndexWriter writer, FetcherInfo fetcherInfo) throws Exception {
		Eraser eraser = this.newEraser(fetcherInfo, writer);
		int count = eraser.clearIndex();
		logger.info("执行数据抽取器" + fetcherInfo.getName() + "定义的删除器，删除" + count + "个文档");
		return count;
	}
	
	public static void deleteIndex(IndexInfo indexInfo) throws Exception {
		logger.info("开始删除索引" + indexInfo.getName() + "。索引目录是" + indexInfo.getIndexPath());
		IndexWriter writer = null;
		try {
			File indexFile = new File(indexInfo.getIndexPath());
			if( !indexFile.exists() ) {
				logger.info("索引目录" + indexInfo.getIndexPath() + "已不存在, 返回。");
				return;
			}
			
			Directory directory = FSDirectory.open(indexFile);
			if( !IndexReader.indexExists(directory) ) {
				logger.info("索引已不存在，返回。");
				return;
			}
			
			if( IndexWriter.isLocked(directory) ) {
				logger.info("索引文件上有锁，释放锁。");
				IndexWriter.unlock(directory);
			}
			Analyzer analyzer = AnalyzerHelper.getInstance().createAnalyzer(indexInfo);
			IndexWriterConfig writerConfig = new IndexWriterConfig(Constants.LUCENE_VERSION, analyzer);
			writer = new IndexWriter(directory, writerConfig);
			writer.deleteAll();
			writer.commit();
			logger.info("删除索引成功。");
		}
		catch(Exception ex) {
			logger.error("删除索引错误。", ex);
			try {
				writer.rollback();
			} 
			catch (IOException e) {
				logger.warn("回滚IndexWriter出错。", e);
			}
			throw ex;
		}
		finally {
			try {
				if( writer != null )
					writer.close();
			} 
			catch (Exception e) {
				logger.warn("关闭IndexWriter出错。", e);
			}
		}
	}
	
	public static void deleteDirectory(IndexInfo indexInfo) throws IOException {
		logger.info("开始删除索引文件。索引目录是" + indexInfo.getIndexPath());
		File indexFile = new File(indexInfo.getIndexPath());
		if( !indexFile.exists() ) {
			logger.info("索引目录" + indexInfo.getIndexPath() + "已不存在，返回。");
			return;
		}
		
		Directory directory = FSDirectory.open(indexFile);
		String[] fileNames = directory.listAll();
		for(String filename : fileNames) {
			logger.info("删除文件" + filename);
			directory.deleteFile(filename);
		}
		FileUtils.deleteDirectory(indexFile);
		logger.info("删除索引文件成功。");
	}
	
	public int fullIndex() {
		IndexInfo indexInfo = this.configuration.getIndex(this.indexId);
		if( indexInfo.isAbstractIndex() ) 
			return 0;
		
		FetcherInfo[] fetcherInfos = this.getFetchersByIndex(null);
		if( fetcherInfos == null || fetcherInfos.length == 0 ) 
			throw new LuceneSearchException("没有设置索引" + indexInfo.getName() + "所对应的资料库，请设置资料库。");
		
		return this.fullIndex(indexInfo, fetcherInfos, true);
	}
	
	private FetcherInfo[] getFetchersByIndex(String[] fetcherIds) {
		FetcherInfo[] fetchers = this.configuration.getFetchersByIndex(this.indexId);
		if( fetcherIds == null || fetcherIds.length == 0 ) {
			return fetchers;
		}

		List<FetcherInfo> retList = new ArrayList<FetcherInfo>();
		for(String fetcherId : fetcherIds) {
			for(FetcherInfo fetcher : fetchers) {
				if( fetcher.getId().equals(fetcherId) ) {
					retList.add(fetcher);
					break;
				}
			}
		}
		return retList.toArray(new FetcherInfo[retList.size()]);
	}
	
	private Eraser newEraser(FetcherInfo fetcherInfo, IndexWriter writer) throws Exception {
		Eraser eraser = null;
		
		EraserInfo eraserInfo = fetcherInfo.getEraser();
		if( eraserInfo != null ) {
			eraser = (Eraser)ReflectHelper.classForName(eraserInfo.getImplClass()).newInstance();
		}
		else {
			eraser = new RemoveAllLuceneEraser();
		}
		
		if( eraser instanceof ParameterizedEraser && eraserInfo.getParams() != null ) {
			((ParameterizedEraser)eraser).setParameterValues(eraserInfo.getParams());
		}
		if( eraser instanceof LuceneEraser ) {
			((LuceneEraser)eraser).setIndexReader(writer);
		}
		return eraser;
	}
	
	private int fullIndex(IndexInfo indexInfo, FetcherInfo fetcherInfo, boolean all) {
		logger.info("开始执行数据抽取器" + fetcherInfo.getName());
		long start = System.currentTimeMillis();
		IndexWriter writer = null;
		
		try {
			Directory directory = FSDirectory.open(new File(indexInfo.getIndexPath()));
			Analyzer analyzer = AnalyzerHelper.getInstance().createAnalyzer(indexInfo);
			boolean create = !IndexReader.indexExists(directory);
			writer = new IndexWriter(directory, analyzer, create, IndexWriter.MaxFieldLength.UNLIMITED);
			if( !create && !all ) {
				this.clearIndex(writer, fetcherInfo);
			}
			
			// to improve index performance
			writer.setRAMBufferSizeMB(Constants.INDEX_RAM_BUFFER_SIZE);
			//writer.setUseCompoundFile(false);
			// create index
			Fetcher fetcher = fetcherInfo.newFetcherInstance(configuration);
			int	count = fetcher.fullWriteIndex(writer);
			writer.optimize();
			writer.commit();
			logger.info("共创建" + count + "个文档。耗时 " + (System.currentTimeMillis() - start) + " 毫秒");	
			return count;
		} 
		catch( Exception ex ) {
			try {
				if( writer != null ) {
					writer.rollback();
				}
			}
			catch(IOException ioex) {
				logger.error("回滚索引事务失败!", ioex);
			}
			throw new LuceneIndexException("更新索引失败。", ex);
		} 
		finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} 
			catch (IOException ex) {
				logger.error("关闭索引文件失败", ex);
			}
		}
	}

	private int fullIndex(IndexInfo indexInfo, FetcherInfo[] fetcherInfos, boolean all) {
		logger.info("开始对索引库:'" + indexInfo.getName() + "'执行完全索引。");
		long start = System.currentTimeMillis();
		int count = 0;
		
		try {
			FSDirectory directory = FSDirectory.open(new File(indexInfo.getIndexPath()));
			if( IndexReader.indexExists(directory) && all ) {
				clearIndex(directory);
			}
		}
		catch(Exception ex) {
			throw new LuceneIndexException("初始化索引错误。", ex);
		}
		
		for (int i = 0; i < fetcherInfos.length; i++) {
			FetcherInfo fetcherInfo = fetcherInfos[i];
			try {
				count += this.fullIndex(indexInfo, fetcherInfos[i], all);
			}
			catch(Exception ex) {
				logger.error("执行数据抽取器" + fetcherInfo.getName() + "错误。", ex);
			}
		}
		logger.info("完全索引执行完成。共创建" + count + "个文档");
		logger.info("总共耗时 " + (System.currentTimeMillis() - start) + " 毫秒");		
		return count;
	}
	
	public int fullIndex(String[] fetcherIds) {
		if( fetcherIds == null || fetcherIds.length == 0 )
			return 0;
		
		IndexInfo indexInfo = this.configuration.getIndex(this.indexId);
		if( indexInfo.isAbstractIndex() ) 
			return 0;
		
		FetcherInfo[] fetcherInfos = this.getFetchersByIndex(fetcherIds);
		if( fetcherInfos == null || fetcherInfos.length == 0 ) 
			throw new LuceneSearchException("没有设置索引" + indexInfo.getName() + "所对应的资料库，请设置资料库。");

		if( fetcherInfos.length == 1) 
			return this.fullIndex(indexInfo, fetcherInfos[0], false);
		else
			return this.fullIndex(indexInfo, fetcherInfos, false);
	}
	
	public void incrementIndex() {
		IndexInfo indexInfo = this.configuration.getIndex(this.indexId);
		if( indexInfo.isAbstractIndex() ) 
			return;
		
		FetcherInfo[] fetcherInfos = this.getFetchersByIndex(null);
		if( fetcherInfos == null || fetcherInfos.length == 0 ) 
			throw new LuceneSearchException("没有设置索引" + indexInfo.getName() + "所对应的资料库，请设置资料库。");
		
		this.incrementIndex(indexInfo, fetcherInfos);
	}

	private void incrementIndex(IndexInfo indexInfo, FetcherInfo fetcherInfo) {
		long start = System.currentTimeMillis();
		IndexWriter writer = null;

		try {
			logger.info("执行数据抽取器:" + fetcherInfo.getName());
			Directory directory = FSDirectory.open(new File(indexInfo.getIndexPath()));
			Analyzer analyzer = AnalyzerHelper.getInstance().createAnalyzer(indexInfo);
			boolean create = !IndexReader.indexExists(directory);
			writer = new IndexWriter(directory, analyzer, create, IndexWriter.MaxFieldLength.UNLIMITED);
			Fetcher fetcher = fetcherInfo.newFetcherInstance(configuration);
			fetcher.incrementWriteIndex(writer);
			writer.optimize();
			writer.commit();
			logger.info("耗时 " + (System.currentTimeMillis() - start) + " 毫秒");		
		} 
		catch( Exception ex ) {
			try {
				if( writer != null ) {
					writer.rollback();
				}
			}
			catch(IOException ioex) {
				logger.error("回滚索引事务失败!", ioex);
			}
			throw new LuceneIndexException("更新索引失败。", ex);
		} 
		finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} 
			catch (IOException ex) {
				logger.error("关闭索引文件失败", ex);
			}
		}		
	}
	
	private void incrementIndex(IndexInfo indexInfo, FetcherInfo[] fetcherInfos) {
		logger.info("开始对索引库:'" + indexInfo.getName() + "'执行增量索引。");
		long start = System.currentTimeMillis();
		for (int i = 0; i < fetcherInfos.length; i++) {
			FetcherInfo fetcherInfo = fetcherInfos[i];
			try {
				this.incrementIndex(indexInfo, fetcherInfo);
			}
			catch(Exception ex) {
				logger.error("执行数据抽取器" + fetcherInfo.getName() + "错误。", ex);
			}
		}
		logger.info("增量索引完成，共耗时 " + (System.currentTimeMillis() - start) + " 毫秒");		
	}
	
	public void incrementIndex(String[] fetcherIds) {
		if( fetcherIds == null || fetcherIds.length == 0 )
			return;
		
		IndexInfo indexInfo = this.configuration.getIndex(this.indexId);
		if( indexInfo.isAbstractIndex() ) 
			return;
		
		FetcherInfo[] fetcherInfos = this.getFetchersByIndex(fetcherIds);
		if( fetcherInfos == null || fetcherInfos.length == 0 ) 
			throw new LuceneSearchException("没有设置索引" + indexInfo.getName() + "所对应的资料库，请设置资料库。");
		
		if( fetcherInfos.length == 1 )
			this.incrementIndex(indexInfo, fetcherInfos[0]);
		else
			this.incrementIndex(indexInfo, fetcherInfos);
	}

	public LuceneConfiguration getConfiguration() {
		return configuration;
	}

	public String getIndexId() {
		return indexId;
	}
}
