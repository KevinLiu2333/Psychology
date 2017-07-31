/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;

import com.wondersgroup.sh.search.enums.FileFieldEnum;
import com.wondersgroup.sh.search.file.Extractor;
import com.wondersgroup.sh.search.file.FileExtractException;
import com.wondersgroup.sh.search.file.FileExtractorFactory;
import com.wondersgroup.sh.search.file.UnsupportedFileTypeException;
import com.wondersgroup.sh.search.lucene.config.FieldInfo;
import com.wondersgroup.sh.search.lucene.config.FileFetcherInfo;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.util.Base64Utils;
import com.wondersgroup.sh.search.util.PropertyUtils;

public class FileFetcher implements Fetcher {
	private static final Logger logger = Logger.getLogger(FileFetcher.class);
	
	private FileFetcherInfo fetcherInfo;
	private IndexInfo indexInfo;
	
	/**
	 * Instantiates a new file fetcher.
	 * 
	 * @param configuration the configuration
	 * @param fetcherId the fetcher id
	 */
	public FileFetcher(LuceneConfiguration configuration, String fetcherId) {
		this.fetcherInfo = (FileFetcherInfo)configuration.getFetcher(fetcherId);
		this.indexInfo = configuration.getIndex(this.fetcherInfo.getIndexId());
	}

	public int fullWriteIndex(IndexWriter writer) {
		File file = new File(fetcherInfo.getFilePath());
		return this.internalFullIndex(writer, file);
	}
	
	/**
	 * Delete document.
	 * 
	 * @param directory the directory
	 */
	private void deleteDocument(FSDirectory directory) {
		IndexReader reader = null;
		try {
			reader = IndexReader.open(directory, false);
			int total = reader.numDocs();
			for (int i = 0; i < total; i++) {
				Document doc = reader.document(i);
				String path = doc.get(FileFieldEnum.PATH.getName());
				File file = new File(path);
				if (!file.exists()) {
					reader.deleteDocument(i);
					logger.info("索引已删除 " + path);
				}
			}
			reader.close();
		} 
		catch( Exception ex ) {
			throw new LuceneIndexException("删除索引" + indexInfo.getName() + "中的文档出错。", ex);
		}
		finally {
			try {
				if( reader != null ) 
					reader.close();
			} 
			catch (IOException e) {
				logger.warn("close index reader error.", e);
			}
		}		
	}
	
	public void incrementWriteIndex(IndexWriter writer) {
		FSDirectory directory = null;
		try {
			directory = FSDirectory.open(new File(indexInfo.getIndexPath()));
		} catch (IOException e) {
			throw new LuceneIndexException("打开所有目录" + indexInfo.getIndexPath() + "出错。", e);
		}

		// delete document
		this.deleteDocument(directory);
		
		// add or update document
		File file = new File(fetcherInfo.getFilePath());
		this.internalIncrementIndex(directory, writer, file);
	}
	
	/**
	 * Internal full index.
	 * 
	 * @param writer the writer
	 * @param file the file
	 * 
	 * @return the int
	 */
	private int internalFullIndex(IndexWriter writer,	File file) {
		int count = 0;
		String path = file.getAbsolutePath();
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				count += internalFullIndex(writer, files[i]);
			}
		} 
		else {
			try {
				writer.addDocument(newDocument(file));
				count++;
				logger.info("索引已增加 " + path);
			} 
			catch(CorruptIndexException e) {
				throw new LuceneIndexException("索引文件" + path + "出错。", e);
			} 
			catch(Exception e) {
				logger.error("索引文件" + path + "出错。原因：" + e.getMessage());
			} 
		}
		
		return count;
	}
	
	/**
	 * 索引.
	 * 
	 * @param writer 索引写入器
	 * @param file 需要进行索引的目录或文件
	 * @param directory the directory
	 */
	private void internalIncrementIndex(FSDirectory directory, IndexWriter writer,	File file) {
		String path = file.getAbsolutePath();
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				internalIncrementIndex(directory, writer, files[i]);
			}
		} 
		else {
			try {
				Term term = new Term(FileFieldEnum.PATH.getName(), path);
				TermQuery query = new TermQuery(term);
				IndexSearcher searcher = new IndexSearcher(directory, false);
				
				TopScoreDocCollector collector = TopScoreDocCollector.create(10, false);
				searcher.search(query, collector);
				ScoreDoc[] hits = collector.topDocs().scoreDocs;
				if( collector.getTotalHits() > 0) {
					Document doc = searcher.doc(hits[0].doc);
					String docModifiedTime = doc.get(FileFieldEnum.MODIFIED_TIME.getName());
					String modifiedTime = DateTools.dateToString(new Date(file.lastModified()), DateTools.Resolution.MILLISECOND);
					if( !docModifiedTime.equals(modifiedTime) ) {
						writer.updateDocument(term, newDocument(file));
						logger.info("索引已更新 " + path);
					}
				}
				else {
					writer.addDocument(newDocument(file));
					logger.info("索引已增加 " + path);
				}
			} 
			catch(CorruptIndexException e) {
				throw new LuceneIndexException("索引文件" + path + "出错。", e);
			} 
			catch( UnsupportedFileTypeException e ) {
				logger.warn(e.getMessage());
			}
			catch(Exception e) {
				logger.error("索引文件" + path + "出错。原因：" + e.getMessage());
			} 
		}
	}
	
	/**
	 * New document.
	 * 
	 * @param file the file
	 * 
	 * @return the document
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FileExtractException the file extract exception
	 * @throws UnsupportedFileTypeException the unsupported file type exception
	 */
	private Document newDocument(File file) throws IOException, FileExtractException, UnsupportedFileTypeException {
		String path = file.getAbsolutePath();
		String content;
		String keywords;
		String title;
		InputStream is = new FileInputStream(file);
		try {
			Extractor extractor = FileExtractorFactory.getExtractor(path);
			Map<String,String> extractMap = extractor.mapExtract(is);
			content = extractMap.get("content");
			keywords = extractMap.get("keywords");
			title = extractMap.get("title");
		} 
		finally {
			is.close();
		}

		Document doc = new Document();
		
		// path
		FieldInfo fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.PATH.getName());
		if( fieldInfo != null ) { 
			this.addField(fieldInfo.getName(), path, doc, fieldInfo);
		}
		
		// relative path
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.REL_PATH.getName());
		if( fieldInfo != null ) {
			String relativePath = StringUtils.removeStart(path, fetcherInfo.getFilePath());
			this.addField(fieldInfo.getName(), relativePath, doc, fieldInfo);
		}
		
		// name
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.NAME.getName());
		if( fieldInfo != null ) {
			this.addField(fieldInfo.getName(), file.getName(), doc, fieldInfo);
		}
		
		// title
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.TITLE.getName());
		if( fieldInfo != null ) {
			this.addField(fieldInfo.getName(),  initTitle(file,title), doc, fieldInfo);
		}
		
		// content
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.CONTENT.getName());
		if( fieldInfo != null ) {
			this.addField(fieldInfo.getName(), content, doc, fieldInfo);
		}
		
		// ext
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.EXT.getName());
		if( fieldInfo != null ) {
			String ext = path.substring(path.lastIndexOf(".") + 1);
			this.addField(fieldInfo.getName(), ext.toUpperCase(), doc, fieldInfo);
		}
		
		// modifiedTime
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.MODIFIED_TIME.getName());
		if( fieldInfo != null ) {
			this.addField(fieldInfo.getName(), new Date(file.lastModified()), doc, fieldInfo);
		}
		
		// length
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.LENGTH.getName());
		if( fieldInfo != null ) {
			this.addField(fieldInfo.getName(), new Long(file.length()), doc, fieldInfo);
		}
		//keywords
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.KEYWORDS.getName());
		if( fieldInfo != null && keywords!=null) {
			this.addField(fieldInfo.getName(), keywords, doc, fieldInfo);
		}
		//url
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.URL.getName());
		if( fieldInfo != null ) {
			this.addField(fieldInfo.getName(), initUrl(file), doc, fieldInfo);
		}
		//fileType
		fieldInfo = indexInfo.getDocument().getField(FileFieldEnum.FILETYPE.getName());
		if( fieldInfo != null ) {
			this.addField(fieldInfo.getName(), initFileType(file), doc, fieldInfo);
		}
		return doc;
	}
	/**
	 * Adds the field.
	 * 
	 * @param name the name
	 * @param value the value
	 * @param document the document
	 * @param fieldInfo the field info
	 */
	private void addField(String name, Object value, Document document, FieldInfo fieldInfo) {
		LuceneUtils.addField(name, value, document, fieldInfo);
	}
	/**
	 * 生成链接地址.
	 * @return
	 */
	private String initUrl(File file){
		String url = PropertyUtils.getProperty("no.such.url");
		try{
			String path = file.getAbsolutePath().replaceAll("\\\\", "/");
			String fileName = file.getName();
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			if(isWebFile(ext)){
				url = path.replaceAll(PropertyUtils.getProperty("gtmh.app.path"),PropertyUtils.getProperty("gtmh.website.url"));
			}else if(PropertyUtils.getProperty("fileType.194File").equals(initFileType(file))){
				String fid = fileName.substring(0, fileName.lastIndexOf("."));
				url = PropertyUtils.getProperty("194.filepath")+fid;
			}else if(PropertyUtils.getProperty("fileType.192File").equals(initFileType(file))){
				url = Base64Utils.encode(path) ;
				url = Base64Utils.encode(url) ;
			}
			return url;
		}catch(Exception e){
			e.printStackTrace();
			return url;
		}
	}
	/**
	 * 生成文件名.
	 * @param file
	 * @param title
	 * @return
	 */
	private String initTitle(File file,String title){
		try{
			if(StringUtils.isNotBlank(title)){
				return title;
			}	
				String fileName = file.getName();
				String fileId =  fileName.substring(0, fileName.lastIndexOf("."));
				String dbTitle = getDbTitle(fileId);
			if(StringUtils.isNotBlank(dbTitle)){
				return dbTitle;
			}else{
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 生成文件类型.
	 * @param file
	 * @return
	 */
	private String initFileType(File file){
		try{
			String path = file.getAbsolutePath().replaceAll("\\\\", "/");
			String ext = path.substring(path.lastIndexOf(".") + 1);
			if(isWebFile(ext) && path.startsWith(PropertyUtils.getProperty("gtmh.app.path")+PropertyUtils.getProperty("gtmh.webFile.url")+"")){
				return PropertyUtils.getProperty("fileType.webFile");
			}else if(path.startsWith(PropertyUtils.getProperty("gtmh.app.path")+PropertyUtils.getProperty("gtmh.webFile.url")+"")){
				return PropertyUtils.getProperty("fileType.192File");
			}else if(path.startsWith(PropertyUtils.getProperty("gtmh.app.path")+PropertyUtils.getProperty("gtmh.upFile.url")+"")){
				return PropertyUtils.getProperty("fileType.192File");
			}else{
				return PropertyUtils.getProperty("fileType.194File");
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 根据文件后缀判断是否网页文件.
	 * @param ext
	 * @return
	 */
	private boolean isWebFile(String ext){
		if(StringUtils.isBlank(ext)){
			return false;
		}else{
			ext = ext.toLowerCase();
			if("htm".equals(ext)||"html".equals(ext)||"shtml".equals(ext)||"jsp".equals(ext)||"php".equals(ext)||"asp".equals(ext)){
				return true;
			}else{
				return false;
			}
		}
	}
	/**
	 * 根据id从数据库获取文件名.
	 * @param id
	 * @return
	 */
	private String getDbTitle(String id){
		Connection conn = null;
		Statement smt = null;
		try{
			String sql = "select fileNameLocal from xdb_files where fid='"+id+"'";
			conn =getConnection();
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while(rs.next()){
				return rs.getString("fileNameLocal");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (smt != null)
					smt.close();
				if (conn != null)
					conn.close();
			} catch (Exception sqlEx) {
			}
		}
		return "";
	}
	/**
	 * 连接Mysql数据库
	 * @return
	 */
	public static Connection getConnection() {
		Connection mysqlConn = null;
		try {
			String mysqlDriver = PropertyUtils.getProperty("mysql.driver");
			String mysqlUrl = PropertyUtils.getProperty("mysql.url");
			Class.forName(mysqlDriver);
			mysqlConn = DriverManager.getConnection(mysqlUrl);
			return	mysqlConn;
		} catch (Exception e) {
			e.printStackTrace();
			return mysqlConn;
		}
	}
	
	
	
	
}
