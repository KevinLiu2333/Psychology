package com.wonders.admin.dao.unifiedmonitoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.admin.entity.unifiedmonitoring.ThresholdSetBean;

/**
 * 实现了阀值设置Dao接口.
 * 
 * @author Gray
 *
 */
@IocBean
public class ThresholdSetDaoImpl implements ThresholdSetDao {

	// 注入NutzDao
	@Inject
	private Dao dao;

	/**
	 * 将数据库中阀值表的所有内容提取出来,其中阀值Id被去除改为阀值名称.
	 * 
	 * @return List<List<Object>> 所有的值.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<List> getNeedAll() {
		// 创建sql语句
		Sql sql = Sqls.create(
				"SELECT tts.id,tdtv.threshold_name,tts.threshold,tdtv.threshold_unit,tts.alarm_level,tts.user_name,tts.update_time  FROM test_threshold_set tts,test_dic_threshold tdtv WHERE tts.threshold_id=tdtv.id");
		// 设置sql回调
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet result, Sql sql) throws SQLException {
				// 创建容器
				List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
				// 判定是否为空
				if (result != null) {// 如果不为空
					// 循环获取查询到的值
					while (result.next()) {
						// 创建list
						Map<String,Object> map = new LinkedHashMap<String,Object>();
						// 将数值放入list
						map.put("Id",result.getInt(1));//Id
						map.put("thresholdName",result.getString(2));//阀值名称
						map.put("threshold",result.getString(3));//阀值
						map.put("threshold_unit",result.getString(4));//阀值单位
						map.put("alarmLevel",result.getInt(5));//报警等级
						map.put("userName",result.getString(6));//用户名称
						map.put("updateTime",result.getDate(7));//更新时间
						// 将list放入容器中
						lists.add(map);
					}
				}
				// 返回结果
				return lists;
			}

		});
		//执行sql
		dao.execute(sql);
		// 返回结果
		return sql.getList(List.class);
	}

	/**
	 * 将已经设置好的阀值更新进数据库.
	 * 
	 * @param tsb
	 *            已经设置好的阀值行.
	 * @return int 更新行数.
	 */
	@Override
	public int update(ThresholdSetBean tsb) {
		// 从数据库提取数据
		ThresholdSetBean t = dao.fetch(ThresholdSetBean.class, tsb.getId());
		// 设置提取的数据
		tsb.setThresholdId(t.getThresholdId());
		// 向数据库更新数据
		int i = dao.update(tsb);
		// 返回跟新行数
		return i;
	}
}
