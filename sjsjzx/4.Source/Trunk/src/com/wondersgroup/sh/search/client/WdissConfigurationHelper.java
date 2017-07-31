package com.wondersgroup.sh.search.client;


public class WdissConfigurationHelper {
	private WdissConfiguration configuration;
	
	private static WdissConfigurationHelper instance;
	private static final String configFilename = "wdiss.cfg.xml";  
	
	private WdissConfigurationHelper() throws Exception {
		this.load(configFilename);
	}
	
	public static synchronized WdissConfigurationHelper getInstance() throws Exception {
		if( instance == null ) {
			instance = new WdissConfigurationHelper();
		}
		return instance;
	}
	
	private synchronized void load(String configFilename) throws Exception {
		this.configuration = new WdissConfiguration(configFilename);
		this.configuration.readConfiguration();
	}
	
	
	public WdissConfiguration getConfiguration() {
		return this.configuration;
	}
}
