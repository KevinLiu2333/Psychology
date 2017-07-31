/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author kchen
 *
 */
public class SearchResult implements Serializable {
	private static final long serialVersionUID = -6300328579841397111L;

	/**
	 * 内容中匹配度最高5个段落，每个段落30个字，段落之间用"..."分隔。如果内容没有匹配到5段，返回的段落就会小于5段。<br>
	 * <li>由SearchEngine设置</li>
	 * <li>并且由SearchEngine进行高亮标记，标记的样式由外部传入设置。
	 */
	@Expose
	private Map matchFragments;

	/**
	 * 所附属的对象，当匹配到类似于附件对象的时候，需要替换为主对象，则在主对象中设置本属性。<br>
	 * 由SearchManager设置
	 */
	private SearchResult dependentRes;

	/**
	 * 业务对象的其他属性<br>
	 * 一般由SearchEngine设置
	 */
	@Expose
	private Map properties = new HashMap();

	public SearchResult(){
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("matchFragments", matchFragments)
			.append("dependentRes", dependentRes)
			.append("properties", properties)
			.toString();
	}
	
	/**
	 * 获取附属资源结果对象
	 * @return 附属资源结果对象
	 */
	public SearchResult getDependentRes() {
		return dependentRes;
	}

	public String getMatchFragment(String key) {
		return matchFragments == null ? null : (String)matchFragments.get(key.toUpperCase());
	}
	
	public void setMatchFragment(String key, String value) {
		if(matchFragments == null) matchFragments = new HashMap();
		matchFragments.put(key.toUpperCase(), value);
	}
	
	/**
	 * 如果是附属资源，将由SearchManager完成特定业务逻辑
	 * @param dependentRes
	 */
	public void setDependentRes(SearchResult dependentRes) {
		this.dependentRes = dependentRes;
	}

	public Map getMatchFragments() {
		return matchFragments;
	}

	public void setMatchFragments(Map matchFragments) {
		this.matchFragments = matchFragments;
	}

	/**
	 * 设置属性
	 * @param key 属性名称，全部被转化为大写
	 * @param value 值
	 */
	public void setProperty( String key , Object value ) {
		if(properties == null) { 
			properties = new HashMap();
		}
  
		properties.put(key.toUpperCase(), value);
	}

	/**
	 * 获取指定属性
	 * @param key 属性名称，全部被转化为大写
	 * @return 属性值
	 */
	public Object getProperty( String key ) { 
		return properties == null ? null : properties.get( key.toUpperCase() ) ;
	}

	public Map getProperties() {
		return properties;
	}

	public void setProperties(Map properties) {
		this.properties = properties;
	}	
}
