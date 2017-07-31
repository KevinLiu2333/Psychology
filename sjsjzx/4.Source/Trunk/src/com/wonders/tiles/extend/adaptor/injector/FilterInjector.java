package com.wonders.tiles.extend.adaptor.injector;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.lang.Strings;
import org.nutz.mvc.adaptor.ParamExtractor;
import org.nutz.mvc.adaptor.ParamInjector;
import org.nutz.mvc.adaptor.Params;

import com.wonders.tiles.extend.adaptor.util.ConUtils;

public class FilterInjector implements ParamInjector {
	
	private String prefix;

	public FilterInjector(String prefix) {
		this.prefix = prefix;
	}

	public Object get(ServletContext sc, HttpServletRequest req, HttpServletResponse resp, Object refer) {
		if (Strings.isEmpty(prefix))
			return null;
		ParamExtractor pe = Params.makeParamExtractor(req, refer);
		Criteria cri = Cnd.cri();
		Map<String, Object> filter = new HashMap<String, Object>();
		String sort = null;
		String order = null;
		for (Object name : pe.keys()) {
			String na = (String) name;
			if (na.startsWith(prefix)) {
				SqlExpressionGroup expressionGroup = null;
				String[] colName = na.split("_");
				Object[] value = pe.extractor(na);
				if (value instanceof String[] && !Strings.isEmpty(value[0].toString())) {
					if (colName[1].equals("str")) {
						expressionGroup = ConUtils.buildStrCondition(colName[2], colName[3], value[0]);
					} else if (colName[1].equals("num")) {
						expressionGroup = ConUtils.buildNumCondition(colName[2], colName[3], value[0]);
					} else if (colName[1].equals("dt")) {
						expressionGroup = ConUtils.buildDtCondition(colName[2], colName[3], value[0]);
					}
					filter.put(na, value[0]);
					cri.where().and(expressionGroup);
				}
			}
			Object[] value1 = pe.extractor(na);
			if (na.equals("sort")) {
				sort = (String)value1[0];
			} else if (na.equals("order")) {
				order = (String)value1[0];
			}
		}
		if (!Strings.isEmpty(sort)) {
			ConUtils.setOrderBy(cri, sort, order);
		}
		req.setAttribute("filter", filter);
		return cri;
	}
}
