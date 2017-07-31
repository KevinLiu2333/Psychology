package com.wonders.tiles.extend.adaptor.injector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.pager.Pager;
import org.nutz.lang.Strings;
import org.nutz.mvc.adaptor.ParamExtractor;
import org.nutz.mvc.adaptor.ParamInjector;
import org.nutz.mvc.adaptor.Params;

import com.wonders.tiles.authority.service.SystemConstants;

public class PagerInjector implements ParamInjector {
	
	private String prefix;

	public PagerInjector(String prefix) {
		this.prefix = prefix;
	}

	public Object get(ServletContext sc, HttpServletRequest req,
			HttpServletResponse resp, Object refer) {
		if (Strings.isEmpty(prefix))
			return null;
		ParamExtractor pe = Params.makeParamExtractor(req, refer);
		int pageNum = 0;
		int numPerPage = 0;
		Pager pager = new Pager();
		pager.setPageSize(SystemConstants.DEFAULT_PAGER_SIZE);
		for (Object name : pe.keys()) {
			String na = (String) name;
			if (na.toLowerCase().indexOf("page") > -1) {
				if (na.equals("pageNum")) {
					Object[] value = pe.extractor(na);
					pageNum = Integer.parseInt((String)value[0]);
					pager.setPageNumber(pageNum);
				} else if (na.equals("numPerPage")) {
					Object[] value = pe.extractor(na);
					numPerPage = Integer.parseInt((String)value[0]);
					pager.setPageSize(numPerPage);
				}
			}
		}
		return pager;
	}

}
