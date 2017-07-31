package com.wonders.zymlgl.common;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.utils.DateUtils;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgl.entity.ResourceDetails;

/**
 * 导入excel数据到资源目录表.
 */
@IocBean
public class ReadExcelDataToDb {

	/** 日志 **/
	private static Log	log	= Logs.get();

	@Inject
	private Dao			dao;

	/**
	 * 获取Xls文件数量
	 * 
	 * @param xlsFilePath
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public int readXslCount(String xlsFilePath) {
		int count = 0;
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(xlsFilePath));
			Sheet sheet = workbook.getSheet(0);
			count = sheet.getRows(); // 获取所有的行
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count - 1;
	}

	/**
	 * 解析资源目录xls文件到数据库(生成目录模板).
	 * 
	 * @param xlsFilePath
	 * @param resourceId
	 * @param resourceFileId
	 * @param session
	 */
	public void readXlsToDb(String xlsFilePath, String resourceFileId, HttpSession session) throws Exception {
		Workbook workbook = Workbook.getWorkbook(new File(xlsFilePath));
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows(); // 获取所有的行
		int endCount = 0; // 资源数据结束标记的行

		Resource resource = saveResource(sheet, session);

		// >>>>>>解析xls，设置资源目录子表信息<<<<<<<

		// 找到资源项所在的行数范围
		for (int index = 0; index < rows; index++) {
			String content = sheet.getCell(1, index).getContents();
			if (Constants.RESOURCE_TEMPLE_ENDFLAG.equals(content)) {
				endCount = sheet.getCell(1, index).getRow();
				log.info("标记结束符:" + content + "在第" + endCount + "行");
			}
		}

		// 设置资源子项
		ResourceDetails resourceDetails = null;
		int orderByNum = 1;
		for (int i = 14; i < endCount; i++) {
			resourceDetails = new ResourceDetails();

			String noProviderReason = sheet.getCell(7, i).getContents();

			resourceDetails.setDataItemName(sheet.getCell(1, i).getContents());
			resourceDetails.setFieldCode(sheet.getCell(2, i).getContents());
			String dataItemType = sheet.getCell(3, i).getContents();
			if (Constants.ZH_DATA_ITEM_TYPE_STRING.equals(dataItemType)) {
				resourceDetails.setDataItemType(Constants.NU_DATA_ITEM_TYPE_STRING);
			} else if (Constants.ZH_DATA_ITEM_TYPE_DATE.equals(dataItemType)) {
				resourceDetails.setDataItemType(Constants.NU_DATA_ITEM_TYPE_DATE);
			} else if (Constants.ZH_DATA_ITEM_TYPE_NUMBER.equals(dataItemType)) {
				resourceDetails.setDataItemType(Constants.NU_DATA_ITEM_TYPE_NUMBER);
			}
			resourceDetails.setMemo(sheet.getCell(4, i).getContents());
			String detailsShareProperty = sheet.getCell(5, i).getContents();
			if (Constants.ZH_SHARE_PROPERTY_GENERAL.equals(detailsShareProperty)) {
				resourceDetails.setShareProperty(Constants.NU_SHARE_PROPERTY_GENERAL);
			} else if (Constants.ZH_SHARE_PROPERTY_NEEDED.equals(detailsShareProperty)) {
				resourceDetails.setShareProperty(Constants.NU_SHARE_PROPERTY_NEEDED);
			} else if (Constants.ZH_SHARE_PROPERTY_NOSHARE.equals(detailsShareProperty)) {
				resourceDetails.setShareProperty(Constants.NU_SHARE_PROPERTY_NOSHARE);
			}
			String detailsOpenProperty = sheet.getCell(6, i).getContents();
			if (Constants.ZH_OPEN_PROPERTY_GENERAL.equals(detailsOpenProperty)) {
				resourceDetails.setOpenProperty(Constants.NU_OPEN_PROPERTY_GENERAL);
			} else if (Constants.ZH_OPEN_PROPERTY_NEEDED.equals(detailsOpenProperty)) {
				resourceDetails.setOpenProperty(Constants.NU_OPEN_PROPERTY_NEEDED);
			} else if (Constants.ZH_OPEN_PROPERTY_NOPEN.equals(detailsOpenProperty)) {
				resourceDetails.setOpenProperty(Constants.NU_OPEN_PROPERTY_NOPEN);
			}
			resourceDetails.setNoOpenReason(noProviderReason);
			resourceDetails.setOrderNum(orderByNum);
			resourceDetails.setResourceId(resource.getResourceId());
			dao.insert(resourceDetails);
			orderByNum++;
		}

	}

	/**
	 * 解析xls数据保存资源目录主表信息(生成目录模板).
	 * 
	 * @param sheet
	 * @return
	 */
	public Resource saveResource(Sheet sheet, HttpSession session) {
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		Resource resource = new Resource();
		// >>>>>>解析xls，设置资源目录主表信息<<<<<<<
		// 资源名称
		resource.setResourceName(sheet.getCell(2, 1).getContents());
		// 摘要
		resource.setAbstractWord(sheet.getCell(2, 2).getContents());
		// 信息系统名称
		resource.setInfoSysName(sheet.getCell(2, 3).getContents());
		// 系统简介
		resource.setSysAbstract(sheet.getCell(2, 4).getContents());
		// 信息系统链接
		resource.setSysUrl(sheet.getCell(2, 5).getContents());
		// 检索关键字
		resource.setKeyWord(sheet.getCell(2, 6).getContents());
		// 提供科室,没有hidden部门deptNo
		resource.setProvideDepartment(sheet.getCell(2, 7).getContents());
		// 业务联系人
		resource.setBusLinkman(sheet.getCell(2, 8).getContents());
		// 业务联系人电话
		resource.setBusLinkmanPhone(sheet.getCell(6, 8).getContents());
		// 对接联系人
		resource.setJointLinkman(sheet.getCell(2, 9).getContents());
		// 对接联系人电话
		resource.setJointLinkmanPhone(sheet.getCell(6, 9).getContents());
		// 对接方式
		String jointType = sheet.getCell(2, 10).getContents();
		if (Constants.ZH_JOINT_TYPE1.equals(jointType)) {
			resource.setJointType(Constants.NU_JOINT_TYPE1);
		} else if (Constants.ZH_JOINT_TYPE2.equals(jointType)) {
			resource.setJointType(Constants.NU_JOINT_TYPE2);
		}
		// 资源数据更新频度
		String updateRate = sheet.getCell(6, 10).getContents();
		if (Constants.ZH_RESOURCE_UPDATE_RATE1.equals(updateRate)) {
			resource.setUpdateRate(Constants.NU_RESOURCE_UPDATE_RATE1);
		} else if (Constants.ZH_RESOURCE_UPDATE_RATE2.equals(updateRate)) {
			resource.setUpdateRate(Constants.NU_RESOURCE_UPDATE_RATE2);
		} else if (Constants.ZH_RESOURCE_UPDATE_RATE3.equals(updateRate)) {
			resource.setUpdateRate(Constants.NU_RESOURCE_UPDATE_RATE3);
		} else if (Constants.ZH_RESOURCE_UPDATE_RATE4.equals(updateRate)) {
			resource.setUpdateRate(Constants.NU_RESOURCE_UPDATE_RATE4);
		} else if (Constants.ZH_RESOURCE_UPDATE_RATE5.equals(updateRate)) {
			resource.setUpdateRate(Constants.NU_RESOURCE_UPDATE_RATE5);
		} else if (Constants.ZH_RESOURCE_UPDATE_RATE6.equals(updateRate)) {
			resource.setUpdateRate(Constants.NU_RESOURCE_UPDATE_RATE6);
		}
		// 所属资源类型
		String resourceType = sheet.getCell(2, 11).getContents();
		if (Constants.ZH_RESOURCE_TYPE_PERSON.equals(resourceType)) {
			resource.setResourceType(Constants.NU_RESOURCE_TYPE_PERSON);
		} else if (Constants.ZH_RESOURCE_TYPE_HOUSE.equals(resourceType)) {
			resource.setResourceType(Constants.NU_RESOURCE_TYPE_HOUSE);
		} else if (Constants.ZH_RESOURCE_TYPE_CORP.equals(resourceType)) {
			resource.setResourceType(Constants.NU_RESOURCE_TYPE_CORP);
		}
		// 资源公开属性
		String openProperty = sheet.getCell(6, 11).getContents();
		if (Constants.ZH_OPEN_PROPERTY_GENERAL.equals(openProperty)) {
			resource.setOpenProperty(Constants.NU_OPEN_PROPERTY_GENERAL);
		} else if (Constants.ZH_OPEN_PROPERTY_NEEDED.equals(openProperty)) {
			resource.setOpenProperty(Constants.NU_OPEN_PROPERTY_NEEDED);
		} else if (Constants.ZH_OPEN_PROPERTY_NOPEN.equals(openProperty)) {
			resource.setOpenProperty(Constants.NU_OPEN_PROPERTY_NOPEN);
		}
		// 数据库中文名称
		resource.setTableChinese(sheet.getCell(2, 12).getContents());
		// 数据库表名称
		resource.setTableName(sheet.getCell(6, 12).getContents());
		// 设置resource基本信息
		resource.setValidity("1");
		resource.setCreateTime(new Date());
		resource.setUpdateDate(new Date());
		resource.setStatus(com.wonders.Constants.DIC_BMZC);
		resource.setOpUser("0");// 表示由各委办领导审核
		resource.setProvideDepId(user.getDept());
		dao.insert(resource);
		return resource;
	}

	/**
	 * 解析资源数据xls文件到数据库(数据型).
	 * 
	 * @param file
	 *            xls文件路径
	 * @param resourceId
	 *            资源目录主键
	 */
	public void readXlsToDb(String xlsFilePath, String resourceId, String resourceFileId, HttpSession session,String dataMonStr) throws BiffException, IOException, ParseException {
		SimpleDateFormat fr=new SimpleDateFormat("yyyy-MM");
		Date dat=fr.parse(dataMonStr);
		// 最新数据表
		String newDataTablelName = "";
		// 历史数据表明
		String historyTableName = "";
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);

		Resource resource = dao.fetch(Resource.class, resourceId);
		if (resource != null) {
			if (!Strings.isEmpty(resource.getTableName())) {
				newDataTablelName = com.wonders.Constants.PREFIX_TABLE + resource.getTableName();
				historyTableName = com.wonders.Constants.PREFIX_TABLE + resource.getTableName() + com.wonders.Constants.SUFFIX_TABLE;
			} else {
				log.info("======================主键为【" + resourceId + "】的资源目录表名为空！======================");
			}
		}

		List<ResourceDetails> resourceDetailList = dao.query(ResourceDetails.class, Cnd.where("resourceId", "=", resourceId));
		// 用于存储资源项 英文字段 与 数据类型 对
		Map<String, String> fieldCodeMap = new HashMap<String, String>();
		fieldCodeMap.put("ID", Constants.DATA_TYPE_STRING);
		fieldCodeMap.put("UPLOAD_USER_ID", Constants.DATA_TYPE_STRING);
		fieldCodeMap.put("UPLOAD_DATE", Constants.DATA_TYPE_DATE);

		if (resourceDetailList.size() > 0) {
			for (ResourceDetails resourceDetails : resourceDetailList) {
				if (!Strings.isEmpty(resourceDetails.getFieldCode()) && !Strings.isEmpty(resourceDetails.getDataItemType())) {
					fieldCodeMap.put(resourceDetails.getFieldCode().toUpperCase(), resourceDetails.getDataItemType());
				}
			}
		}

		Workbook workbook = Workbook.getWorkbook(new File(xlsFilePath));
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows(); // 获取所有的行
		int cols = sheet.getColumns(); // 得到所有的列

		// 第一行为字段名称，第二行为数据的开始
		log.info("===========开始解析xls============");

		for (int i = 0; i < rows; i++) {
			// 拼接插入最新库表的sql
			StringBuffer newDataTableNameSql = new StringBuffer("insert into " + newDataTablelName + "(");
			// 拼接插入历史数据库表的sql
			StringBuffer historyDataTableNameSql = new StringBuffer("insert into " + historyTableName + "(");
			historyDataTableNameSql.append("UPLOAD_USER_ID").append(",");
			historyDataTableNameSql.append("UPLOAD_DATE").append(",");
			historyDataTableNameSql.append("FILE_ID").append(",");
			historyDataTableNameSql.append("DATA_YEAR").append(",");
			historyDataTableNameSql.append("DATA_MON").append(",");

			StringBuffer newDataFieldKeyVars = new StringBuffer();
			StringBuffer historyDataFieldKeyVars = new StringBuffer();
			historyDataFieldKeyVars.append("@UPLOAD_USER_ID").append(",");
			historyDataFieldKeyVars.append("@UPLOAD_DATE").append(",");
			historyDataFieldKeyVars.append("@FILE_ID").append(",");
			historyDataFieldKeyVars.append("@DATA_YEAR").append(",");
			historyDataFieldKeyVars.append("@DATA_MON").append(",");

			String fieldValue = "";

			// 用于存储变量占位符及对应的值
			Map<String, String> paramsMap = new HashMap<String, String>();
			// 申明ID的值
			String idValue = "";

			for (int j = 0; j < cols; j++) {
				// 获取insert语句字段
				String fieldKey = sheet.getCell(j, 0).getContents();
				// 取得该条数据的ID值
				if (i >= 1) {
					idValue = sheet.getCell(0, i).getContents();
				}
				// 检查资源详细表R_RESOURCE_DETIALS表中是否存在该字段，如果存在则拼接。
				if (fieldCodeMap.containsKey(fieldKey.toLowerCase()) || fieldCodeMap.containsKey(fieldKey.toUpperCase())) {
					newDataTableNameSql.append(fieldKey).append(",");
					historyDataTableNameSql.append(fieldKey).append(",");

					newDataFieldKeyVars.append("@" + fieldKey).append(",");
					historyDataFieldKeyVars.append("@" + fieldKey).append(",");
					// 数据从第二行开始
					if (i >= 1) {
						fieldValue = sheet.getCell(j, i).getContents();
						if (Strings.isEmpty(fieldValue)) {
							paramsMap.put(fieldKey, "NULL");
							log.info("==================数据模板中的字段【" + fieldKey + "】和库表字段不对应，或存在空值！==================");
						} else {
							paramsMap.put(fieldKey, fieldValue);
						}
					}
				}

			}
			newDataTableNameSql.deleteCharAt(newDataTableNameSql.length() - 1);
			historyDataTableNameSql.deleteCharAt(historyDataTableNameSql.length() - 1);

			newDataTableNameSql.append(")").append(" values(");
			historyDataTableNameSql.append(")").append(" values(");

			newDataTableNameSql.append(newDataFieldKeyVars.deleteCharAt(newDataFieldKeyVars.length() - 1));
			historyDataTableNameSql.append(historyDataFieldKeyVars.deleteCharAt(historyDataFieldKeyVars.length() - 1));

			newDataTableNameSql.append(")");
			historyDataTableNameSql.append(")");

			Sql executeNewDataSql = Sqls.create(newDataTableNameSql.toString());
			Sql executeHistoryDataSql = Sqls.create(historyDataTableNameSql.toString());

			// 为变量占位符设置值
			if (i >= 1) {
				Set<Map.Entry<String, String>> allSet = paramsMap.entrySet();
				Iterator<Map.Entry<String, String>> iter = allSet.iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> me = iter.next();
					// 处理insert数据类型

					// 字符
					if (Constants.DATA_TYPE_STRING.equals(fieldCodeMap.get(me.getKey()))) {
						executeNewDataSql.params().set(me.getKey(), me.getValue());
						executeHistoryDataSql.params().set(me.getKey(), me.getValue());
					}
					// 日期
					if (Constants.DATA_TYPE_DATE.equals(fieldCodeMap.get(me.getKey()))) {
						if (Strings.isEmpty(validateDate(me.getKey()))) {
							String dateStr = me.getValue().replace("/", "-");
							Date date = DateUtils.format(dateStr, DateUtils.FORMAT_DATETIME);
							executeNewDataSql.params().set(me.getKey(), date);
							executeHistoryDataSql.params().set(me.getKey(), date);
						}
					}
					// 数字
					if (Constants.DATA_TYPE_NUMBER.equals(fieldCodeMap.get(me.getKey()))) {
						executeNewDataSql.params().set(me.getKey(), Integer.parseInt(me.getValue()));
						executeHistoryDataSql.params().set(me.getKey(), Integer.parseInt(me.getValue()));
					}
				}
				executeHistoryDataSql.params().set("UPLOAD_USER_ID", user.getUserId());
				executeHistoryDataSql.params().set("UPLOAD_DATE", new Date());
				executeHistoryDataSql.params().set("FILE_ID", resourceFileId);
				executeHistoryDataSql.params().set("DATA_MON",dat);
				// 执行sql
				System.out.println("执行SQL:" + executeNewDataSql);
				System.out.println("执行SQL:" + executeHistoryDataSql);
				// 往最新数据表中插数据
				if (!isExist(idValue, newDataTablelName)) {
					dao.execute(executeNewDataSql);
				} else {
					Sql updateSql = makeUpdateSql(newDataTablelName, paramsMap, fieldCodeMap);
					// 执行更新语句
					dao.execute(updateSql);
				}
				// 往历史库中插数据
				dao.execute(executeHistoryDataSql);
			}
		}
	}

	/**
	 * 获取update语句.
	 * 
	 * @param tableName
	 * @param paramsMap
	 * @param fieldCodeMap
	 * @return
	 */
	private Sql makeUpdateSql(String tableName, Map<String, String> paramsMap, Map<String, String> fieldCodeMap) {
		StringBuffer sql = new StringBuffer("update " + tableName + " set ");
		Set<Map.Entry<String, String>> allSet = paramsMap.entrySet();
		Iterator<Map.Entry<String, String>> iter = allSet.iterator();
		String idValue = "";
		while (iter.hasNext()) {
			Map.Entry<String, String> me = iter.next();
			if ("ID".equals(me.getKey().toUpperCase())) {
				idValue = me.getValue();
			} else {
				// 字符
				if (Constants.DATA_TYPE_STRING.equals(fieldCodeMap.get(me.getKey()))) {
					sql.append(me.getKey()).append("='");
					sql.append(me.getValue()).append("'").append(",");
				}
				// 日期
				if (Constants.DATA_TYPE_DATE.equals(fieldCodeMap.get(me.getKey()))) {
					sql.append(me.getKey()).append("=");
					sql.append("to_date('").append(me.getValue()).append("',").append("'yyyy-MM-dd HH:mi:ss'").append(")").append(",");
				}
				// 数字
				if (Constants.DATA_TYPE_NUMBER.equals(fieldCodeMap.get(me.getKey()))) {
					sql.append(me.getKey()).append("=").append(me.getValue()).append(",");
				}
			}
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where ").append("ID='").append(idValue).append("'");
		String sqlStr = sql.toString().replace("/", "-");
		Sql updateSql = Sqls.create(sqlStr);
		return updateSql;
	}

	public boolean isExist(String id, String tableName) {
		boolean flag = false;
		String sql = "select ID from " + tableName + " where " + "ID=" + "'" + id + "'";
		Sql exeSql = Sqls.create(sql);
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
		String value = (String) exeSql.getResult();
		if (!Strings.isEmpty(value)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 校验日期格式. 返回null表示日期格式
	 */
	public String validateDate(String date) {
		int year = 0;
		int month = 0;
		int day = 0;
		// 月2位 日2位
		if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}年[0-9]{2}月[0-9]{2}日").matcher(date).matches()) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
		}
		// 月2位 日1位
		if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{1}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}年[0-9]{2}月[0-9]{1}日").matcher(date).matches()) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 9));
		}
		// 月1位 日2位
		if (Pattern.compile("[0-9]{4}-[0-9]{1}-[0-9]{2}|[0-9]{4}/[0-9]{1}/[0-9]{2}|[0-9]{4}年[0-9]{1}月[0-9]{2}日").matcher(date).matches()) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 6));
			day = Integer.parseInt(date.substring(7, 9));
		}
		// 月1位 日1位
		if (Pattern.compile("[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}年[0-9]{1}月[0-9]{1}日").matcher(date).matches()) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 6));
			day = Integer.parseInt(date.substring(7, 8));
		}
		// 判断月份是否问题
		if (month > 12) {
			return "月份超过12";
		}
		// 日是否有问题
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if (day > 31) {
				return month + "月只有31天";
			}
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day > 30) {
				return month + "月只有30天";
			}
		}
		if (month == 2) {
			if ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0) {// 闰年
				if (day > 29) {
					return year + "年2月只有29天";
				}
			} else {// 非闰年
				if (day > 28) {
					return year + "年2月只有28天";
				}
			}
		}
		return null;
	}
}
