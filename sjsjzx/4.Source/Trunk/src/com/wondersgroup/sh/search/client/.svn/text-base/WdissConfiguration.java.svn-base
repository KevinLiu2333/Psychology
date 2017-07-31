package com.wondersgroup.sh.search.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.WdisException.ErrorCode;

public class WdissConfiguration {
	private static final String defaultNodeName = "default";
	private String configFileName;
	private List<Site> sites;
	private Site defaultSite;
	private HierarchicalConfiguration configuration;
	
	public WdissConfiguration(String configFileName) {
		this.configFileName = configFileName;
	}

	public synchronized void refresh() throws Exception {
		this.readConfiguration();
	}

	public synchronized void readConfiguration() {
		try {
			this.configuration = new XMLConfiguration(this.configFileName);
			this.readSiteConfiguration();
			this.readDefaultSiteConfiguration();			
		}
		catch(Exception ex) {
			throw new WdisException(ErrorCode.READ_CONFIG_ERROR, "读取配置文件错误：" + this.configFileName, ex);
		}
	}

	private void readSiteConfiguration() {
		this.sites = new ArrayList<Site>();
		List<HierarchicalConfiguration> siteNodes = this.configuration.configurationsAt("site");
		for(HierarchicalConfiguration siteNode : siteNodes) {
			String id = siteNode.getString("[@id]");
			String name = siteNode.getString("[@name]", id);
			String host = siteNode.getString("[@host]");
			String port = siteNode.getString("[@port]", String.valueOf(Site.DEFAULT_PORT));
			String contextPath = siteNode.getString("[@contextPath]");
			List<String> indexes = Arrays.asList(siteNode.getStringArray("indexes.value"));	
			
			Site site = new Site(id, host, Integer.valueOf(port), contextPath, indexes);
			site.setName(name);
			this.sites.add(site);
		}
	}

	private void readDefaultSiteConfiguration() {
		
		String defaultId = this.configuration.getString(defaultNodeName + "[@id]");
		if( StringUtils.isNotBlank(defaultId) ) {
			this.defaultSite = new Site();
			this.defaultSite.setId(defaultId);
			this.defaultSite.setName(this.configuration.getString(defaultNodeName + "[@name]", defaultId));
			this.defaultSite.setHost(this.configuration.getString(defaultNodeName + "[@host]"));
			this.defaultSite.setPort(this.configuration.getInt(defaultNodeName + "[@port]", Site.DEFAULT_PORT));
			this.defaultSite.setContextPath(this.configuration.getString(defaultNodeName + "[@contextPath]"));
		}
	}
	
	public synchronized Site getSiteByIndex(String indexId) {
		for(Site site : this.sites) {
			if( site.containsIndex(indexId) ) {
				return site;
			}
		}
		return null;
	}
	
	public synchronized List<String> getAllIndexIds() {
		List<String> retList = new ArrayList<String>();
		for(Site site : this.sites) {
			retList.addAll(site.getIndexes());
		}
		return retList;
	}
	
	public String getConfigFileName() {
		return configFileName;
	}

	public List<Site> getSites() {
		return sites;
	}

	public HierarchicalConfiguration getConfiguration() {
		return configuration;
	}

	public Site getDefaultSite() {
		return defaultSite;
	}
}
