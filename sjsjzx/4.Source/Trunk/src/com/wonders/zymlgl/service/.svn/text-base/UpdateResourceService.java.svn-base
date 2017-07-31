package com.wonders.zymlgl.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.Constants;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgl.entity.ResourceDetails;

/**
 * 资源目录更新的操作service.
 */
@IocBean
public class UpdateResourceService {
	@Inject
	private Dao	dao;
	@Inject
	private CreateExcelService createExcelService;

	/**
	 * 根据"编目信息"创建表. 审核通过后，同时创建两张表，一张为保存最新上传的数据，一张保存所有的历史数据。
	 */
	public void createTatble(String resourceId) {
		Resource resource = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
		// 表名称
		String tableName = resource.getTableName();
		if (!Strings.isEmpty(tableName)) {
			// 保存最新上传的数据（表名前缀+编目时填写的表名称）
			String realTable = Constants.PREFIX_TABLE + tableName;
			// 保存历史上传数据（表名前缀+编目时填写的表名称+_HISTORY）
			String realHistoryTable = Constants.PREFIX_TABLE + tableName + Constants.SUFFIX_TABLE;

			// 创建新的表前，需要删除上一个版本资源目录的表
			dropTable(realTable);
			dropTable(realHistoryTable);

			StringBuffer realTableSql = new StringBuffer("create table " + realTable + "(");
			StringBuffer realHistoryTableSql = new StringBuffer("create table " + realHistoryTable + "(");

			Criteria cri = Cnd.cri();
			cri.where().andEquals("resourceId", resourceId);
			cri.getOrderBy().asc("orderNum");
			List<ResourceDetails> ResourceDetailsList = dao.query(ResourceDetails.class, cri);
			
			realTableSql.append("ID").append(" ");
			realTableSql.append("VARCHAR2(64 BYTE)").append(",");
			
			// 外键，上传文件主键
			realHistoryTableSql.append("FILE_ID").append(" ");
			realHistoryTableSql.append("VARCHAR2(64 BYTE)").append(","); 
			// 数据所属年份
			realHistoryTableSql.append("DATA_YEAR").append(" ");
			realHistoryTableSql.append("Date").append(","); 
			// 数据所属月份
			realHistoryTableSql.append("DATA_MON").append(" ");
			realHistoryTableSql.append("Date").append(",");

			realHistoryTableSql.append("ID").append(" ");
			realHistoryTableSql.append("VARCHAR2(64 BYTE)").append(",");
			// 长传userId
			realHistoryTableSql.append("UPLOAD_USER_ID").append(" ");
			realHistoryTableSql.append("VARCHAR2(64 BYTE)").append(",");
			// 上传日期 
			realHistoryTableSql.append("UPLOAD_DATE").append(" ");
			realHistoryTableSql.append("Date").append(","); 
			
			for (ResourceDetails field : ResourceDetailsList) {
				// 数据资源项英文名
				realTableSql.append(field.getFieldCode()).append(" ");
				realHistoryTableSql.append(field.getFieldCode()).append(" ");
				if ("1".equals(field.getDataItemType())) {
					realTableSql.append("VARCHAR2(60 BYTE)").append(",");
					realHistoryTableSql.append("VARCHAR2(60 BYTE)").append(",");
				}
				if ("2".equals(field.getDataItemType())) {
					realTableSql.append("Date").append(",");
					realHistoryTableSql.append("Date").append(",");
				}
				if ("3".equals(field.getDataItemType())) {
					realTableSql.append("NUMBER").append(",");
					realHistoryTableSql.append("NUMBER").append(",");
				}
			}
			realTableSql.deleteCharAt(realTableSql.length() - 1);
			realHistoryTableSql.deleteCharAt(realHistoryTableSql.length() - 1);
			realTableSql.append(")");
			realHistoryTableSql.append(")");
			// 创建表
			executeCreateTable(realTableSql.toString());
			// 创建历史表
			executeCreateTable(realHistoryTableSql.toString());
			// 生成excel文件
			createExcelService.createExcelTemplet(resourceId);
		}
	}

	public void dropTable(String tableName) {
		if (exists(tableName)) {
			String sql = "drop table " + tableName;
			Sql dropTableSql = Sqls.create(sql);
			dao.execute(dropTableSql);
		}
	}

	/**
	 * 判断表是否存在.
	 */
	private boolean exists(String tableName) {
		boolean flag = false;
		String existTableSql = "select count(*) from user_tables where table_name = '" + tableName.toUpperCase() + "'";
		Sql exeSql = Sqls.create(existTableSql);
		exeSql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				Object obj = null;
				while (rs.next()) {
					return rs.getObject(1);
				}
				return obj;
			}
		});
		dao.execute(exeSql);
		Long count = Long.parseLong(exeSql.getResult().toString());
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	public void executeCreateTable(String sql) {
		Sql exeCreateTableSql = Sqls.create(sql);
		dao.execute(exeCreateTableSql);
	}

}
