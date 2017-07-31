package com.wondersgroup.sh.search.client;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Site implements Serializable {
	private static final long serialVersionUID = -4312342456574960919L;
	
	public static final int DEFAULT_PORT = 80;

	private String id;
	private String host;
	private int port;
	private String contextPath;
	private List<String> indexes;
	private String name;
	
	public Site() {
	}

	public Site(String id, String host, int port, String contextPath, List<String> indexes) {
		this.id = id;
		this.host = host;
		this.port = port;
		this.contextPath = contextPath;
		this.indexes = indexes;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public boolean containsIndex(String inIndex) {
		for(String index : indexes) {
			if(index.equalsIgnoreCase(inIndex)) {
				return true;
			}
		}
		return false;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public List<String> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<String> indexes) {
		this.indexes = indexes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
