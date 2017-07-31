package com.wondersgroup.cmc.utils.pagequery;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.wssip.commons.dao.CommonJdbcDao;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class JdbcDaoUtils extends CommonJdbcDaoUtils{
	
	private static Log log = LogFactory.getLog("jdbcDaoUtil.log");
	
	public JdbcDaoUtils(CommonJdbcDao commonJdbcDAO) {
		super(commonJdbcDAO);
	}
	
	
	//************************CMC miniUI DataGrid Query******************************//
	
	/**
	 * miniUI分页查询
	 * 
	 * @param querySQL   查询SQL
	 * @param queryArg   查询SQL参数
	 * @param pageIndex  页码，传入参数 ，从0开始， 0为第一页
	 * @param pageSize   每页记录数,传入参数
	 * @param target     记录对象
	 * @return
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DataGrid queryForDataGrid(String querySQL, Object[] queryArgs,  int pageIndex, int pageSize, Class target){
		DataGrid dataGrid = new DataGrid();
		
		//开始时间
		long starttime = System.currentTimeMillis();
		String sql = DialectFactory.getDefaultDialectInstance().createPagesql(pageIndex, pageSize, querySQL);
		List result = query(sql, target, queryArgs);
		
		// 计算总数
		String[] ss = querySQL.toUpperCase().split("ORDER BY");
		String countSQL = "select count(*) from  ( " + querySQL.substring(0, ss[0].length()) + ") t ";
		
		Integer totalCount = queryObject(countSQL, Integer.class, queryArgs);
		
		
		//Log
		long costtime = System.currentTimeMillis() - starttime;
		
		JdbcDaoLogUtils.doLog(log, costtime, "queryForDataGrid", querySQL , JdbcDaoLogUtils.arrayToString(queryArgs));
		
		dataGrid.setData(result);
		dataGrid.setPageIndex(pageIndex);
		dataGrid.setPageSize(pageSize);
		dataGrid.setTotal(totalCount.intValue());
					
		return dataGrid;
	}
	
}
