package com.wonders.tiles.autocrud.cacher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.tiles.autocrud.entity.AppColumn;
import com.wonders.tiles.autocrud.entity.AppTable;
import com.wonders.tiles.autocrud.service.AutoCrudService;
import com.wonders.util.ListMapUtil;

/**
 * 单表CRUD操作的缓存类
 * 
 * 作用：缓存表和字段的配置信息，提高页面处理速度
 * 
 * @since 2009-08-25
 */
@IocBean
public class AutoCrudCache {
	/** logger */
	private static Log logger = Logs.get();

	private List<AppTable> appTableList = new ArrayList<AppTable>();

	@Inject
	private AutoCrudService autoCrudService;
	
	/**
	 * 装载表及字段信息，在系统起动时加载，形式[{table1[{column1}{column2}{column3}]}{table2...}{
	 * table3...}]
	 */
	@SuppressWarnings("unchecked")
	public void load() {

		List<AppTable> tmpTableList = autoCrudService.getTableList();
		List<AppColumn> tmpColumnList = autoCrudService.getColumnList();

		logger.info("------表CRUD操作，加载表" + tmpTableList.size() + "张------");
		logger.info("------表CRUD操作，加载字段" + tmpColumnList.size() + "个------");

		Iterator<AppTable> iter = tmpTableList.iterator();
		appTableList.clear();
		while (iter.hasNext()) {
			AppTable table = iter.next();

			// 获得该表下的字段列表
			table.setAppColumnList(ListMapUtil.getSubListFromListById(tmpColumnList, "getTableId", table.getTableId()));

			appTableList.add(table);
		}
	}

	public List<AppTable> getAppTableList() {
		return appTableList;
	}
}

