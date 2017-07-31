package com.wonders.xml2db.xml.operator;

import java.io.File;

import org.dom4j.Element;

import com.wonders.xml2db.dbc.ConnectionPool;

public class ReadXmlProxy implements IReadXml {

	private ConnectionPool	dbc		= null;
	private IReadXml		readxml	= null;

	public ReadXmlProxy() {
		this.dbc = ConnectionPool.getInstance();// 数据库连接操作
		this.readxml = new ReadXmlImpl(this.dbc.getConnection());
	}

	public Element getXmlRoot() {
		return this.readxml.getXmlRoot();
	}

	public void readXml(Element root) {
		try {
			this.readxml.readXml(root);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbc.closePool();
		}
	}

	@Override
	public void readXmlsByPath(String path) {
		this.readxml.readXmlsByPath(path);
	}

	@Override
	public Element getXmlRoot(File file) {
		return this.readxml.getXmlRoot(file);
	}

}
