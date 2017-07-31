package com.wondersgroup.sh.search.test;

import org.apache.log4j.Logger;

import org.junit.Test;

import com.wondersgroup.sh.search.client.WdissConfiguration;

public class WdissConfigurationTester {
	private static final Logger logger = Logger.getLogger(WdissConfigurationTester.class);

	@Test
	public void testReadConfiguration() {
		WdissConfiguration configuration = new WdissConfiguration("wdiss.cfg.xml");
		configuration.readConfiguration();
		logger.info(configuration.getSites());
		logger.info(configuration.getDefaultSite());
	}
}
