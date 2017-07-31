package com.wondersgroup.sh.search.client;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.WdisException.ErrorCode;

public class SearchClient {
	private static final Logger logger = Logger.getLogger(SearchClient.class);
	private static final String dateFormat = "yyyy/MM/dd HH:mm:ss";
	
	private String userQueryString;
	private WdissConfiguration wdissConfiguration;
	
	public SearchClient(String userQueryString) throws Exception {
		this.userQueryString = userQueryString;
		this.wdissConfiguration = WdissConfigurationHelper.getInstance().getConfiguration();
		this.wdissConfiguration.readConfiguration();
	}

	public SearchClient() throws Exception {
		this(null);
	}

	private Site getIndexSite(String indexId) {
		Site site = this.wdissConfiguration.getSiteByIndex(indexId);
		if( site == null ) {
			site = this.wdissConfiguration.getDefaultSite();
			if( site == null ) 
				throw new WdisException(ErrorCode.INDEX_NOT_EXIST, "不能找到" + indexId + "所在的服务器。");
		}
		return site;
	}
	
	public SearchResultSet search(String input, int start, int rows, String indexId) throws Exception {
		Site site = this.getIndexSite(indexId);
		HttpClient httpclient = new DefaultHttpClient();
		URI uri = this.createURI(input, start, rows, indexId, site);
		HttpGet httpget = new HttpGet(uri);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();

		InputStream is = entity.getContent();
		byte[] content = IOUtils.toByteArray(is);
		String json = new String(content, "utf-8");
		
		GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
			.setDateFormat(dateFormat)
			.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		SearchResultSet srs = gson.fromJson(json, SearchResultSet.class);
		logger.debug(srs);
		return srs;
	}
	
	private URI createURI(String input, int start, int rows, String indexId, Site site) throws URISyntaxException {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("sp.q", input));
		qparams.add(new BasicNameValuePair("sp.start", String.valueOf(start)));
		qparams.add(new BasicNameValuePair("sp.rows", String.valueOf(rows)));
		if( StringUtils.isNotBlank(indexId) ) {
			qparams.add(new BasicNameValuePair("sp.indexId", indexId));
		}
		URI uri = URIUtils.createURI("http", site.getHost(), site.getPort(), site.getContextPath() + "/wdiss/search.action", 
				URLEncodedUtils.format(qparams, "GBK"), null);
		return uri;
	}

	public String getUserQueryString() {
		return userQueryString;
	}

	public void setUserQueryString(String userQueryString) {
		this.userQueryString = userQueryString;
	}

	public WdissConfiguration getWdissConfiguration() {
		return wdissConfiguration;
	}
}
