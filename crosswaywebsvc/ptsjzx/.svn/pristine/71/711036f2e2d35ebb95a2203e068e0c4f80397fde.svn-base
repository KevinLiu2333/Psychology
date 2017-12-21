package com.wonders.tiles.extend.adaptor;

import java.lang.reflect.Type;

import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.adaptor.ParamInjector;
import org.nutz.mvc.annotation.Param;

import com.wonders.tiles.extend.adaptor.injector.FilterInjector;
import com.wonders.tiles.extend.adaptor.injector.PagerInjector;

public class SearchTableAdaptor extends PairAdaptor {
	
	private static final String SEARCH_FILTER = "filter_";
	
	private static final String TABLE_PAGER = "pager_";

//	private static final Log log = Logs.get();

	protected ParamInjector evalInjector(Type type, Param param) {
		Class<?> clazz = Lang.getTypeClass(type);
		if ((param == null && clazz.equals(Criteria.class)) || (param != null && SEARCH_FILTER.equals(param.value()))) {
				return new FilterInjector(param == null? SEARCH_FILTER :Strings.isEmpty(param.value()) ? SEARCH_FILTER:param.value());
		} else if ((param == null && clazz.equals(Pager.class)) || (param != null && TABLE_PAGER.equals(param.value()))) {
			return new PagerInjector(param == null? TABLE_PAGER :Strings.isEmpty(param.value()) ? TABLE_PAGER:param.value());
		}
		return super.evalInjector(type, param);
	}

}
