package com.wonders.admin.dao.unifiedmonitoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 报警列表接口的实现类.
 * 
 * @author Gray
 *
 */
@IocBean
public class AlarmListDaoImpl implements AlarmListDao {

	// 注入NutzDao
	@Inject
	private Dao dao;

	/**
	 * 通过页面值,和提取数量,从数据库中提取报警列表分页.
	 * 
	 * @param pageNum
	 *            页面值.
	 * @param quantity
	 *            提取数量.
	 * @return List<List> 查询到的结果.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<List> getPage(int pageNum, int quantity) {
		// 创建sql语句
		Sql sql = Sqls.create(
				"SELECT * FROM (SELECT ROWNUM as rn ,tal.id,tdtv.threshold_name,tal.alarm_time,tal.alarm_details,tal.alarm_level,tal.processing_instructions FROM test_alarm_list tal,test_dic_threshold tdtv WHERE tal.threshold_id=tdtv.id) WHERE rn BETWEEN 10 AND 20");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet result, Sql sql) throws SQLException {
				// 创建容器
				List<List<Object>> lists = new ArrayList<List<Object>>();
				System.out.println(result);
				// 判定是否为空
				if (result != null) {// 如果不为空
					// 循环获取查询到的值
					while (result.next()) {
						// 创建list
						List<Object> list = new ArrayList<Object>();
						// 将数值放入list
						list.add(result.getLong(2));
						list.add(result.getString(3));
						list.add(result.getDate(4));
						list.add(result.getString(5));
						list.add(result.getInt(6));
						list.add(result.getString(7));
						// 将list放入容器中
						lists.add(list);
					}
				}
				// 返回结果
				return lists;
			}
		});
		// 执行sql
		dao.execute(sql);
		// 返回结果
		return sql.getList(List.class);
	}

	/**
	 * 用于查询数据总量.
	 * 
	 * @return Integer 数据总量.
	 */
	@Override
	public Integer getPageNumber() {
		// sql语句
		Sql sql = Sqls.create("SELECT COUNT(*) FROM TEST_ALARM_LIST");
		// 设置回调函数
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet result, Sql sql) throws SQLException {
				// 移动指针
				result.next();
				// 返回第一个值
				return result.getInt(1);
			}
		});
		// 执行sql
		dao.execute(sql);
		// 获取记录数量
		Integer count = sql.getObject(Integer.class);
		// 返回值
		return count;
	}

}
