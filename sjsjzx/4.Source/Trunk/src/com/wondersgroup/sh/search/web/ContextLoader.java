/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.web;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.util.FileChangeListener;
import com.wondersgroup.sh.search.util.FileMonitor;

public class ContextLoader {
	private static final Logger logger = Logger.getLogger(ContextLoader.class);
	public static final String CONFIG_LOCATION_PARAM = "configLocation";

	private LuceneConfiguration configuration;
	private ServletContext servletContext;
	
	public LuceneConfiguration initConfiguration(ServletContext servletContext) {
		this.servletContext = servletContext;
		long startTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("上海万达全文检索配置: 开始初始化");
		}
		servletContext.log("Loading WDIS configuration");
		
		try {
			String configFileName = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
			if( configFileName != null ) {
				this.configuration = new LuceneConfiguration(configFileName);
				this.configuration.readConfiguration();
				servletContext.setAttribute(LuceneConfiguration.ROOT_WEB_APPLICATION_CONFIG_ATTRIBUTE, this.configuration);
				if (logger.isDebugEnabled()) {
					logger.debug("Published root WDIS　configuration [" + this.configuration + "] as ServletContext attribute with name [" +
							LuceneConfiguration.ROOT_WEB_APPLICATION_CONFIG_ATTRIBUTE + "]");
				}
				if (logger.isInfoEnabled()) {
					long elapsedTime = System.currentTimeMillis() - startTime;
					logger.info("上海万达全文检索配置: 初始化完成时间" + elapsedTime + "毫秒");
				}

				logger.info("开始监视全文检索配置文件" + configFileName + "。时间间隔10秒中。");
				FileMonitor fileMonitor = FileMonitor.getInstance();
				fileMonitor.addFileChangeListener(new SearchConfigFileChangeListener(), configFileName, 10000);
			}
			
			return configuration;
		}
		catch(Exception ex) {
			String errMsg = "上海万达全文检索配置初始化失败。";
			logger.error(errMsg, ex);
			servletContext.setAttribute(LuceneConfiguration.ROOT_WEB_APPLICATION_CONFIG_ATTRIBUTE, ex);
			throw new SearchException(errMsg, ex);
		}
	}

	// 必须把Web Context中reloadable功能禁止掉。
	class SearchConfigFileChangeListener implements FileChangeListener {
		public void fileChanged(String fileName) {
			logger.info("全文检索配置文件" + fileName + "修改过了，重新读取文件。");
			try {
				configuration.refresh();
				servletContext.setAttribute(LuceneConfiguration.ROOT_WEB_APPLICATION_CONFIG_ATTRIBUTE, configuration);
			} 
			catch (Exception ex) {
				String errMsg = "刷新上海万达全文检索配置错误，继续使用以前的配置。";
				logger.error(errMsg, ex);
				throw new SearchException(errMsg, ex);
			}
		}
	}
} 
