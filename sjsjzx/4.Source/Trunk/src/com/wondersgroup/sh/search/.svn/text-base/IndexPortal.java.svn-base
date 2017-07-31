/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search;

import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.lucene.DefaultLuceneIndexer;
import com.wondersgroup.sh.search.lucene.LuceneIndexer;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;

/**
 * 可以在命令行中执行索引的Portal。
 * 
 * @author kchen
 */
public class IndexPortal {
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(IndexPortal.class);

  private static final String USAGE = "[-h] [-configfile <索引配置文件>] [-index <索引编号>] [-full <以逗号分隔的数据抽取器编号列表>] " + 
		"[-incre <以逗号分隔的数据抽取器编号列表>]";
  private static final String HEADER = "万达智能检索系统索引构造软件, Copyright 2009 万达信息股份有限公司.";
  private static final String FOOTER = "要得到更多帮助信息, 请联系kchen@wondersgroup.com";	
  private static final String SEPARATOR = ",;#";
  private static IndexPortal instance = new IndexPortal();
  
	/**
	 * Instantiates a new index portal.
	 */
	private IndexPortal() {
	}
	
	public static IndexPortal getInstance() {
		return instance;
	}

	public void fullIndex(LuceneConfiguration configuration, String indexId, String[] fetcherIds) throws Exception {
		LuceneIndexer indexer = new DefaultLuceneIndexer(configuration, indexId);
		if( fetcherIds == null ) {
			indexer.fullIndex();
		}
		else {
			indexer.fullIndex(fetcherIds);
		}
	}
	
	public void fullIndex(LuceneConfiguration configuration, String[] fetcherIds) throws Exception {
		List<IndexInfo> indexes = configuration.getIndexes();
		for(IndexInfo index : indexes) {
			this.fullIndex(configuration, index.getId(), fetcherIds);
		}
	}
	
	public void fullIndex(LuceneConfiguration configuration, String[] indexIds, String[] fetcherIds) throws Exception {
		for(String indexId : indexIds) {
			this.fullIndex(configuration, indexId, fetcherIds);
		}
	}
	
	public void incrementIndex(LuceneConfiguration configuration, String indexId, String[] fetcherIds) throws Exception {
		LuceneIndexer indexer = new DefaultLuceneIndexer(configuration, indexId);
		if( fetcherIds == null ) {
			indexer.incrementIndex();
		}
		else {
			indexer.incrementIndex(fetcherIds);
		}
	}
	
	public void incrementIndex(LuceneConfiguration configuration, String[] fetcherIds) throws Exception {
		List<IndexInfo> indexes = configuration.getIndexes();
		for(IndexInfo index : indexes) {
			this.incrementIndex(configuration, index.getId(), fetcherIds);
		}
	}
	
	public void incrementIndex(LuceneConfiguration configuration, String[] indexIds, String[] fetcherIds) throws Exception {
		for(String indexId : indexIds) {
			this.incrementIndex(configuration, indexId, fetcherIds);
		}
	}
	
  private static void printUsage(Options options) {
    HelpFormatter helpFormatter = new HelpFormatter( );
    helpFormatter.printHelp( USAGE, HEADER, options, FOOTER );
  }
  
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			IndexPortal portal = IndexPortal.getInstance();
			CommandLineParser parser = new BasicParser();
			Option configfile = OptionBuilder.hasArg().withDescription("索引配置文件").isRequired().create("configfile");
			Option index = OptionBuilder.hasArg().withDescription("索引配置文件").isRequired(false).create("index");
			Option full = OptionBuilder.hasOptionalArg().withDescription("完全索引").isRequired(false).create("full");
			Option incre = OptionBuilder.hasOptionalArg().withDescription("增量索引，可以带以逗号分隔的数据抽取器编号列表做为参数").isRequired(false).create("incre");

			Options options = new Options( );
			options.addOption("h", false, "打印使用帮助信息");
			options.addOption(configfile);
			options.addOption(index);
			options.addOption(full);
			options.addOption(incre);

			CommandLine commandLine = parser.parse( options, args );
			if( commandLine.hasOption("h") ) {
				printUsage(options);
				System.exit(0);
			}

			String configFileName = null;
			String[] indexIds = null;
			boolean needFullIndex = false;
			boolean needIncreIndex = false;
			String[] fullFetcherIds = null;
			String[] increFetcherIds = null;

			// config file
			if( commandLine.hasOption("configfile") ) {
				configFileName = commandLine.getOptionValue("configfile");
			}
			if( StringUtils.isBlank(configFileName) ) {
				System.err.println("请指定索引配置文件");
				printUsage(options);
				System.exit(1);
			}

			// index id
			if( commandLine.hasOption("index") ) {
				String indexIdArg = commandLine.getOptionValue("index");
				indexIds = indexIdArg.split("[" + SEPARATOR + "]");
			}
			
			// full index
			if( commandLine.hasOption("full") ) {
				needFullIndex = true;
				String fetcherIdArg = commandLine.getOptionValue("full");
				if( StringUtils.isNotBlank(fetcherIdArg) ) {
					fullFetcherIds = fetcherIdArg.split("[" + SEPARATOR + "]");
				}
			}
			
			if( commandLine.hasOption("incre") ) {
				needIncreIndex = true;
				String fetcherIdArg = commandLine.getOptionValue("incre");
				if( StringUtils.isNotBlank(fetcherIdArg) ) {
					increFetcherIds = fetcherIdArg.split(",");
				}      	
			}

			if( !needFullIndex && !needIncreIndex )
				needFullIndex = true;

			LuceneConfiguration configuration = new LuceneConfiguration(configFileName);
			configuration.readConfiguration();
			if( needFullIndex ) {
				if( !isAllIndex(indexIds) ) 
					portal.fullIndex(configuration, indexIds, fullFetcherIds);
				else 
					portal.fullIndex(configuration, fullFetcherIds);
			}
			
			if( needIncreIndex ) {
				if( !isAllIndex(indexIds) )
					portal.incrementIndex(configuration, indexIds, increFetcherIds);
				else 
					portal.incrementIndex(configuration, increFetcherIds);
			}
			System.exit(0);
		}
		catch(Exception ex) {
			logger.error("创建索引出错。", ex);
			ex.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	private static boolean isAllIndex(String[] indexIds) {
		return indexIds == null || indexIds.length == 0 || "AlLInDex".equals(indexIds[0]);
	}
}
