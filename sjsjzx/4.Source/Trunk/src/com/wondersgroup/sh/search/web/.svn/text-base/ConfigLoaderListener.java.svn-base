/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.wondersgroup.sh.search.web.ContextLoader;
/**
 * The listener interface for receiving configLoader events.
 * The class that is interested in processing a configLoader
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addConfigLoaderListener<code> method. When
 * the configLoader event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ConfigLoaderEvent
 */
public class ConfigLoaderListener implements ServletContextListener {
	
	/** The context loader. */
	private ContextLoader contextLoader;

	/**
	 * Initialize the root web application context.
	 * 
	 * @param event the event
	 */
	public void contextInitialized(ServletContextEvent event) {
		this.contextLoader = createContextLoader();
		this.contextLoader.initConfiguration(event.getServletContext());
	}

	/**
	 * Create the ContextLoader to use. Can be overridden in subclasses.
	 * 
	 * @return the new ContextLoader
	 */
	protected ContextLoader createContextLoader() {
		return new ContextLoader();
	}

	/**
	 * Return the ContextLoader used by this listener.
	 * 
	 * @return the context loader
	 */
	public ContextLoader getContextLoader() {
		return contextLoader;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
