package com.klsw.wk.hades.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * SiteMesh 过滤器
 * @author liulixi
 * @version 2017年6月16日10:20:50
 */
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {
	@Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/admin/*", "/decorator/_index.html");
    }
}
