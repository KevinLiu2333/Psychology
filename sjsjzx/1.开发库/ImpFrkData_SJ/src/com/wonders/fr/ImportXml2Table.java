package com.wonders.fr;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wonders.db.ConnectionPool;
import com.wonders.fr.bean.AfterBean;
import com.wonders.fr.bean.PunishNoteBean;
import com.wonders.fr.bean.PunishNoteCorpBean;
import com.wonders.fr.bean.PunishNoteInfoBean;
import com.wonders.fr.service.FrService;
import com.wonders.jk.Control2Table;
import com.wonders.utils.PropertyUtils;

public class ImportXml2Table {

	public static void main(String[] args) {
		ImportXml2Table.execute();
	}

	private Logger				logger				= Logger.getLogger(ImportXml2Table.class);

	private Connection			conn				= ConnectionPool.getInstance().getConnection();

	/** 类型为NUBMBER或DATE **/
	public static final String	IS_NUMBER_OR_DATE	= "1";
	/** 是否开启写入数据库操作标记(1:开启,0:关闭) **/
	public static final String	POWER_ON			= "1";

	/** 打印插入数据日志级别 **/
	public static final String	LOG_LEVEL			= PropertyUtils.getProperty("log.level");
	/** 日志信息一级输出 **/
	public static final String	LOG_DEBUG			= "debug";
	/** 日志信息二级输出 **/
	public static final String	LOG_INFO			= "info";
	/** 日志信息三级输出 **/
	public static final String	LOG_WARN			= "warn";
	/** 日志级别控制,默认为不输出. **/
	private int					logLevel			= 0;
	/** 记录数据插入量 **/
	int							count				= 0;

	public static void intoFrTable(String fileName) {
		new ImportXml2Table().readXml(getXmlRoot(new File(fileName)));
	}

	public static void execute() {
		String path = PropertyUtils.getProperty("filePath");
		ImportXml2Table ix2t = new ImportXml2Table();
		ix2t.readXmlsByPath(path);
	}

	public ImportXml2Table() {
		if (LOG_DEBUG.equals(LOG_LEVEL)) {
			logLevel = 3;
		}
		if (LOG_INFO.equals(LOG_LEVEL)) {
			logLevel = 2;
		}
		if (LOG_WARN.equals(LOG_LEVEL)) {
			logLevel = 1;
		}
	}

	/**
	 * 根据指定文件获取xml根节点.
	 * 
	 * @param file
	 * @return
	 */
	public static Element getXmlRoot(File xmlFile) {
		Element root = null;
		try {
			SAXReader sax = new SAXReader();// 创建一个SAXReader对象
			sax.setEncoding("utf-8");
			Document document;
			document = sax.read(xmlFile);
			root = document.getRootElement();// 获取根节点
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}

	/**
	 * 读取xml文件.
	 */
	@SuppressWarnings("static-access")
	public void readXml(Element root) {
		PreparedStatement pstmt = null;
		try {
			// 现在应该根据根节点找到全部的子节点
			Iterator<?> iter = root.elementIterator();

			// 交换监控需要的信息
			int insertSuCount = 0;// 交换类型为insert的成功数据
			int insertFaCount = 0;// 交换类型为insert的失败数据
			int updateSuCount = 0;// 交换类型为update的成功数据
			int updateFaCount = 0;// 交换类型为update的失败数据
			String dataTable = "";
			List<String> inSuIdList = new ArrayList<String>();// 保存新增操作结果成功的ID
			List<String> inFaIdList = new ArrayList<String>();// 保存新增操作结果失败的ID
			List<String> upSuIdList = new ArrayList<String>();// 保存更新操作结果成功的ID
			List<String> upFaIdList = new ArrayList<String>();// 保存更新操作结果失败的ID
			String idValue = "";

			while (iter.hasNext()) {
				Element item = (Element) iter.next();// 取得每一条数据

				String dbName = analysisTableName(item.getName());// 数据库表名称
				dataTable = dbName;
				if (dbName == null) {
					return;
				}
				/** 主键是否重复 **/
				boolean isPkSerpico = false;
				// =========获取数据库表字段名=========
				String sql = "select * from " + dbName;
				// 拼装SQL
				StringBuffer insertSql = new StringBuffer("insert into " + dbName + "(");

				pstmt = this.conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);
				ResultSetMetaData data = rs.getMetaData();

				/** 当前ID值 **/
				String currentId = "";
				/** 存放需要更新的字段键值对 **/
				List<Map<String, Object>> fieldMaps = new ArrayList<Map<String, Object>>();
				/** 数据库表主键 **/
				String pkName = null;
				// 1通过连接获取表的主键
				DatabaseMetaData dbMeta = this.conn.getMetaData();
				ResultSet pkRSet = dbMeta.getPrimaryKeys(null, null, dbName.toUpperCase());
				while (pkRSet.next()) {
					pkName = (String) pkRSet.getObject(4);
				}

				pkRSet.close();

				// 2如果数据库表没有设置主键则取得第一列
				if (pkName == null) {
					pkName = data.getColumnName(1);
				}
				// 主键的值
				List<String> idValues = getIdValue(pkName, dbName.toUpperCase());
				// *************************

				for (int i = 1; i <= data.getColumnCount(); i++) {
					// 获得指定列的列名
					String columnName = data.getColumnName(i);
					insertSql.append(columnName).append(",");
				}

				insertSql.deleteCharAt(insertSql.length() - 1).append(") values(");

				// 将新增的数据id加入到集合中
				inSuIdList.add(item.elementText(data.getColumnName(1).toLowerCase()));
				// 循环判断字段类型并给值
				for (int i = 1; i <= data.getColumnCount(); i++) {
					String value = null;// 接受字符串或日期格式
					String tempValue = null;
					String isNumber = "";// 是否是NUMBER或DATE类型(0:不是,1:是)
					// 获得指定列的列名
					String columnName = data.getColumnName(i);
					// 获得指定列的数据类型名
					String columnTypeName = data.getColumnTypeName(i);
					if ("VARCHAR2".equals(columnTypeName)) {
						if ("".equals(item.elementText(columnName.toLowerCase())) || "null".equals(item.elementText(columnName.toLowerCase()))
								|| item.elementText(columnName.toLowerCase()) == null) {
							value = "NULL";
						} else {
							value = item.elementText(columnName.toLowerCase());
							if (IS_NUMBER_OR_DATE.equals(isNumber)) {
								fieldMaps.add(prepareMap(columnName, value));
							} else {
								tempValue = "'" + value + "'";
								fieldMaps.add(prepareMap(columnName, tempValue));
							}
						}
						if (logLevel >= 3) {
							logger.info("共【" + data.getColumnCount() + "】列；给第【" + i + "】列,类型为：【" + columnTypeName + "】的:【" + columnName + "】赋值内容为:【" + value + "】");
						}
					}
					if ("DATE".equals(columnTypeName)) {
						// 如果从xml中取出的时间值为null时,拼接的sql不需要"'".
						isNumber = IS_NUMBER_OR_DATE;
						if ("".equals(item.elementText(columnName.toLowerCase())) || "null".equals(item.elementText(columnName.toLowerCase()))
								|| item.elementText(columnName.toLowerCase()) == null) {
							value = "to_date(NULL, 'yyyy-MM-dd HH:mi:ss')";
							if (IS_NUMBER_OR_DATE.equals(isNumber)) {
								fieldMaps.add(prepareMap(columnName, value));
							} else {
								tempValue = "'" + value + "'";
								fieldMaps.add(prepareMap(columnName, tempValue));
							}
						} else {
							value = "to_date(" + "'" + item.elementText(columnName.toLowerCase()).replace("T", " ") + "'" + ", 'yyyy-mm-dd HH24:mi:ss')";
							if (IS_NUMBER_OR_DATE.equals(isNumber)) {
								fieldMaps.add(prepareMap(columnName, value));
							} else {
								tempValue = "'" + value + "'";
								fieldMaps.add(prepareMap(columnName, tempValue));
							}
						}
						if (logLevel >= 3) {
							logger.info("共【" + data.getColumnCount() + "】列；给第【" + i + "】列,类型为：【" + columnTypeName + "】的:【" + columnName + "】赋值内容为:【" + value + "】");
						}
					}
					if ("NUMBER".equals(columnTypeName)) {
						isNumber = IS_NUMBER_OR_DATE;
						if ("".equals(item.elementText(columnName.toLowerCase())) || "null".equals(item.elementText(columnName.toLowerCase()))
								|| item.elementText(columnName.toLowerCase()) == null) {
							value = "NULL";
						} else {
							value = item.elementText(columnName.toLowerCase());
							if (IS_NUMBER_OR_DATE.equals(isNumber)) {
								fieldMaps.add(prepareMap(columnName, value));
							} else {
								tempValue = "'" + value + "'";
								fieldMaps.add(prepareMap(columnName, tempValue));
							}
						}
						if (logLevel >= 3) {
							logger.info("共【" + data.getColumnCount() + "】列；给第【" + i + "】列,类型为：【" + columnTypeName + "】的:【" + columnName + "】赋值内容为:【" + value + "】");
						}
					}

					if (IS_NUMBER_OR_DATE.equals(isNumber)) {
						insertSql.append(value).append(",");
					} else {
						insertSql.append("'" + value + "'").append(",");
					}

					// update

				}

				insertSql.deleteCharAt(insertSql.length() - 1).append(")");
				if (logLevel >= 2) {
					logger.info("执行的SQL语句为:" + insertSql);
				}

				// 如果有主键重复，则update(删除老的数据，再增加新的数据)
				for (String id : idValues) {
					if (!"".equals(item.elementText(pkName.toLowerCase())) && item.elementText(pkName.toLowerCase()).equals(id)) {
						isPkSerpico = true;// 已存在重复的ID
						currentId = id;
					}
				}

				// 如果ID重复
				if (isPkSerpico) {
					if (POWER_ON.equals(PropertyUtils.getProperty("power"))) {
						if (updateFrkData(pkName, currentId, fieldMaps, dbName.toUpperCase())) {
							++updateSuCount;
							upSuIdList.add(currentId);
							logger.info("**************ID为【" + currentId + "】的数据更新成功!***************");
						} else {
							++updateFaCount;
							upFaIdList.add(currentId);
							if (logLevel >= 1) {
								logger.info("*******************ID为【" + currentId + "】的数据更新失败！*******************");
							}
						}
					} else {
						if (logLevel >= 1) {
							logger.info("****************您已关闭数据写入操作!****************");
						}
					}
				} else {// ID不重复
					if (POWER_ON.equals(PropertyUtils.getProperty("power"))) {
						if (insertFrkData(this.conn, insertSql.toString())) {
							++insertSuCount;
						} else {
							++insertFaCount;
						}
					}
				}
				// 必须关闭，否则：超出打开游标的最大数
				pstmt.close();
				rs.close();

			}

			// 交换类型
			Control2Table control2Table = new Control2Table();
			// 库表名称 交换类型 交换结果 数量 交换时间 记录交换数据ID字段

			if (updateSuCount != 0 || updateFaCount != 0) {
				control2Table.execute(dataTable, "update", updateSuCount, updateFaCount, upSuIdList.size() > 0 ? upSuIdList.toString() : "",
						upFaIdList.size() > 0 ? upFaIdList.toString() : "");
			}
			if (insertSuCount != 0 || insertFaCount != 0) {
				control2Table.execute(dataTable, "insert", insertSuCount, insertFaCount, inSuIdList.size() > 0 ? inSuIdList.toString() : "",
						inFaIdList.size() > 0 ? inFaIdList.toString() : "");
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			this.close(pstmt);
		}
	}

	private String analysisTableName(String nodeName) {
		// 20160721加入“双告知”解析内容
		if ("corp_after_permit".equals(nodeName))
			return "CORP_AFTER_PERMIT";

		// 70 法人基本信息
		if ("corporation_info".equals(nodeName))
			return "CORP_INFO";
		// 法人资质
		if ("corp_license".equals(nodeName))
			return "CORP_LICENSE";
		// 吊销信息
		if ("corp_repeal".equals(nodeName))
			return "CORP_REPEAL";
		// 处罚信息主表
		// if ("punish_note_enty".equals(nodeName))
		// return "PUNISH_NOTE_ENTY";
		// 处罚信息事项关联表
		// if ("punish_note_info_enty".equals(nodeName))
		// return "PUNISH_NOTE_INFO_ENTY";
		// 处罚信息法人主体关联表
		// if ("punish_note_corp_enty".equals(nodeName))
		// return "PUNISH_NOTE_CORP_ENTY";

		// 以下不处理
		// 73 非正常户认定表 / 74 非正常户恢复正常表 / 79 组织机构代码验证表
		return null;
	}

	public Map<String, Object> prepareMap(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return map;
	}

	public boolean insertFrkData(Connection conn, String sql) throws SQLException {
		boolean flag = false;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				this.conn.commit();
				++count;
				flag = true;
				if (logLevel >= 1) {
					logger.info("****************成功插入【" + count + "】条数据!****************");
				}
			} else {
				if (logLevel >= 1) {
					logger.info("****************插如数据失败!****************");
				}
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 根据主键名称和表名称获取该条数据的主键值.
	 * 
	 * @param pkName
	 *            主键名称
	 * @param tableName
	 *            表名称
	 * @return 主键值
	 * @throws SQLException
	 */
	public List<String> getIdValue(String pkName, String tableName) {
		List<String> idList = new ArrayList<String>();
		PreparedStatement pstmt = null;
		String sql = "select " + pkName + " from " + tableName;
		try {
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				idList.add(rs.getString(1));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(pstmt);
		}
		return idList;
	}

	/**
	 * 更新数据.
	 * 
	 * @param pkName
	 *            主键字段名称
	 * @param pkValue
	 *            主键字段值
	 * @param objs
	 *            数组
	 * @param tableName
	 *            表名
	 * @return
	 */
	public boolean updateFrkData(String pkName, String pkValue, List<Map<String, Object>> listMap, String tableName) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		StringBuffer sql = new StringBuffer("update " + tableName + " set ");
		for (Map<String, Object> map : listMap) {
			Set<Map.Entry<String, Object>> allSet = map.entrySet();
			Iterator<Map.Entry<String, Object>> iter = allSet.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> me = iter.next();
				if (!"null".equals(me.getValue().toString()) && me.getValue() != null) {
					sql.append(me.getKey() + " = " + me.getValue() + ",");
				}
			}
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where " + pkName + " = '" + pkValue + "'");
		if (logLevel >= 1) {
			logger.info("*******************update语句为【" + sql + "】*******************");
		}
		try {
			pstmt = this.conn.prepareStatement(sql.toString());
			if (pstmt.executeUpdate() > 0) {
				this.conn.commit();
				flag = true;
				if (logLevel >= 1) {
					logger.info("********************成功更新ID为：【" + pkValue + "】的数据********************");
				}
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(pstmt);
		}
		return flag;
	}

	public void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void readXmlsByPath(String path) {
		File file = new File(path);
		if (path == null || !file.exists()) {
			if (logLevel >= 1) {
				logger.info("******************文件路径不存在!******************");
			}
			return;
		}
		if (file.listFiles().length == 0) {
			if (logLevel >= 1) {
				logger.info("******************此目录下没有需要解析的文件!******************");
				return;
			}
		}
		// 如果是目录
		if (file.isDirectory()) {
			File fs[] = file.listFiles();
			for (File f : fs) {
				if (f.isDirectory()) {
					readXmlsByPath(f.toString());
				} else {
					if (this.getXmlRoot(f) != null) {
						this.readXml(this.getXmlRoot(f));
						// 解析双告知数据
						this.insertAfterPermit(this.getXmlRoot(f));
						// 解析处罚数据
						this.insertPunishData(this.getXmlRoot(file));
						this.moveFile(PropertyUtils.getProperty("filePath"), PropertyUtils.getProperty("bakPath"), f, PropertyUtils.getProperty("suffix"));
					}
				}
			}
		} else {// 如果不是目录
			this.readXml(getXmlRoot(file));
			// 解析双告知数据
			this.insertAfterPermit(this.getXmlRoot(file));
			// 解析处罚数据
			this.insertPunishData(this.getXmlRoot(file));
			this.moveFile(PropertyUtils.getProperty("filePath"), PropertyUtils.getProperty("bakPath"), file, PropertyUtils.getProperty("suffix"));
		}
	}

	private void insertPunishData(Element xmlRoot) {
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement prestmt = null;
		try {
			List<PunishNoteBean> noteBeanList = new ArrayList<PunishNoteBean>();
			List<PunishNoteCorpBean> noteCorpBeanList = new ArrayList<PunishNoteCorpBean>();
			List<PunishNoteInfoBean> noteInfoBeanList = new ArrayList<PunishNoteInfoBean>();

			List<Element> noteList = xmlRoot.selectNodes("/send_data/punish_info/punish_note");
			List<Element> noteInfoList = xmlRoot.selectNodes("/send_data/punish_info/punish_note_info");
			List<Element> noteCorpList = xmlRoot.selectNodes("/send_data/punish_info/punish_note_corp");

			execDataPunishNote(noteBeanList, noteList);
			execDataPunishNoteInfo(noteInfoBeanList, noteInfoList);
			execDataPunishCorp(noteCorpBeanList, noteCorpList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (prestmt != null)
					prestmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private void execDataPunishCorp(List<PunishNoteCorpBean> noteCorpBeanList, List<Element> noteCorpList) {
		if (noteCorpList != null && noteCorpList.size() != 0) {
			for (Element ele : noteCorpList) {

				PunishNoteCorpBean ab = new PunishNoteCorpBean();
				Iterator<?> iter = ele.elementIterator();
				while (iter.hasNext()) {
					Element item = (Element) iter.next();// 取得每一条数据
					String strName = item.getName();
					if ("punish_corp_id".equals(strName))
						ab.setPunish_corp_id(Integer.parseInt(item.getText()));
					if ("punish_enty_id".equals(strName))
						ab.setPunish_enty_id(Integer.parseInt(item.getText()));
					if ("entity_id".equals(strName))
						ab.setEntity_id(Integer.parseInt(item.getText()));
					if ("corp_name".equals(strName))
						ab.setCorp_name(item.getText());
				}
				noteCorpBeanList.add(ab);
			}
		}

		if (noteCorpBeanList != null && noteCorpBeanList.size() > 0) {
			for (PunishNoteCorpBean ab : noteCorpBeanList) {
				FrService frService = new FrService();
				PunishNoteCorpBean pnb = frService.getPunishNoteCorpInfo(ab);
				if (pnb != null) {
					frService.updatePunishNoteCorp(ab);
					logger.info("*******************update PunishNoteCorp，成功***************");
				} else {
					frService.insertPunishNoteCorp(ab);
					logger.info("*******************insert PunishNoteCorp，成功***************");
				}
			}
		}

	}

	private void execDataPunishNoteInfo(List<PunishNoteInfoBean> noteInfoBeanList, List<Element> noteInfoList) {
		if (noteInfoList != null && noteInfoList.size() != 0) {
			for (Element ele : noteInfoList) {

				PunishNoteInfoBean ab = new PunishNoteInfoBean();
				Iterator<?> iter = ele.elementIterator();
				while (iter.hasNext()) {
					Element item = (Element) iter.next();// 取得每一条数据
					String strName = item.getName();
					if ("punish_info_id".equals(strName))
						ab.setPunish_info_id(Integer.parseInt(item.getText()));
					if ("punish_enty_id".equals(strName))
						ab.setPunish_enty_id(Integer.parseInt(item.getText()));
					if ("punish_type".equals(strName))
						ab.setPunish_type(item.getText());
					if ("punish_type_name".equals(strName))
						ab.setPunish_type_name(item.getText());
				}
				noteInfoBeanList.add(ab);
			}
		}

		if (noteInfoBeanList != null && noteInfoBeanList.size() > 0) {
			for (PunishNoteInfoBean ab : noteInfoBeanList) {
				FrService frService = new FrService();
				PunishNoteInfoBean pnb = frService.getPunishNoteInfo(ab);
				if (pnb != null) {
					frService.updatePunishNoteInfo(ab);
					logger.info("*******************update PunishNoteInfo，成功***************");
				} else {
					frService.insertPunishNoteINfo(ab);
					logger.info("*******************insert PunishNoteInfo，成功***************");
				}
			}
		}

	}

	private void execDataPunishNote(List<PunishNoteBean> noteBeanList, List<Element> noteList) {
		if (noteList != null && noteList.size() != 0) {
			for (Element ele : noteList) {

				PunishNoteBean ab = new PunishNoteBean();
				Iterator<?> iter = ele.elementIterator();
				while (iter.hasNext()) {
					Element item = (Element) iter.next();// 取得每一条数据
					String strName = item.getName();
					if ("punish_enty_id".equals(strName))
						ab.setPunish_enty_id(Integer.parseInt(item.getText()));
					if ("punish_code".equals(strName))
						ab.setPunish_code(item.getText());
					if ("punish_unit".equals(strName))
						ab.setPunish_unit(item.getText());
					if ("corp_name".equals(strName))
						ab.setCorp_name(item.getText());
					if ("illegal_rule".equals(strName))
						ab.setIllegal_rule(item.getText());
					if ("punish_basis".equals(strName))
						ab.setPunish_basis(item.getText());
					if ("punish_date".equals(strName))
						ab.setPunish_date(item.getText());
					if ("from_dept_id".equals(strName))
						ab.setFrom_dept_id(item.getText());
				}
				noteBeanList.add(ab);
			}
		}

		if (noteBeanList != null && noteBeanList.size() > 0) {
			for (PunishNoteBean ab : noteBeanList) {
				FrService frService = new FrService();
				PunishNoteBean pnb = frService.getPunishNote(ab.getPunish_enty_id());
				if (pnb != null) {
					frService.updatePunishNote(ab);
					logger.info("*******************update PunishNote，成功***************");
				} else {
					frService.insertPunishNote(ab);
					logger.info("*******************insert PunishNote，成功***************");
				}
			}
		}
	}

	private void insertAfterPermit(Element xmlRoot) {
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement prestmt = null;
		try {
			List<AfterBean> arrayList = new ArrayList<AfterBean>();

			List<Element> afterList = xmlRoot.selectNodes("/project/corporation_info/corporation_after_permit_set/corporation_after_permit");

			if (afterList != null && afterList.size() != 0) {
				for (Element afterEle : afterList) {

					AfterBean ab = new AfterBean();
					Iterator<?> iter = afterEle.elementIterator();
					while (iter.hasNext()) {
						Element item = (Element) iter.next();// 取得每一条数据
						String strName = item.getName();
						if ("id".equals(strName))
							ab.setId(Integer.parseInt(item.getText()));
						if ("permit_no".equals(strName))
							ab.setPermit_no(Integer.parseInt(item.getText()));
						if ("entity_id".equals(strName))
							ab.setEntity_id(item.getText());
						if ("permit_organ_code".equals(strName))
							ab.setPermit_organ_code(item.getText());
						if ("after_permit_item_name".equals(strName))
							ab.setAfter_permit_item_name(item.getText());
						if ("insert_time".equals(strName))
							ab.setInsert_time(item.getText().replace("T", " "));
						if ("upd_time".equals(strName))
							ab.setUpd_time(item.getText().replace("T", " "));
					}
					arrayList.add(ab);
				}
			}

			if (arrayList != null && arrayList.size() > 0) {
				for (AfterBean ab : arrayList) {
					String strSql = "select * from CORP_AFTER_PERMIT where id=" + ab.getId();
					st = this.conn.createStatement();
					rs = st.executeQuery(strSql);
					// 是否已经存在数据
					if (rs.next()) {
						// 已经存在，做更新
						String sql = "update CORP_AFTER_PERMIT set id=?,permit_no=?,entity_id=?,permit_organ_code=?,after_permit_item_name=?,insert_time=to_date(?, 'yyyy-mm-dd HH24:mi:ss'), upd_time=to_date(?, 'yyyy-mm-dd HH24:mi:ss') where id=?";
						prestmt = this.conn.prepareStatement(sql);
						prestmt.setInt(1, ab.getId());
						prestmt.setInt(2, ab.getPermit_no());
						prestmt.setString(3, ab.getEntity_id());
						prestmt.setString(4, ab.getPermit_organ_code());
						prestmt.setString(5, ab.getAfter_permit_item_name());
						prestmt.setString(6, ab.getInsert_time());
						prestmt.setString(7, ab.getUpd_time());
						prestmt.setInt(8, ab.getId());
						prestmt.execute();
						logger.info("*******************insert CORP_AFTER_PERMIT 语句为【" + sql + "】，成功*******************");
					} else {
						// 不存在，做插入
						String sql = "insert into CORP_AFTER_PERMIT (id,permit_no,entity_id,permit_organ_code,after_permit_item_name,insert_time,upd_time )values (?,?,?,?,?,to_date(?, 'yyyy-mm-dd HH24:mi:ss'), to_date(?, 'yyyy-mm-dd HH24:mi:ss'))";
						prestmt = this.conn.prepareStatement(sql);
						prestmt.setInt(1, ab.getId());
						prestmt.setInt(2, ab.getPermit_no());
						prestmt.setString(3, ab.getEntity_id());
						prestmt.setString(4, ab.getPermit_organ_code());
						prestmt.setString(5, ab.getAfter_permit_item_name());
						prestmt.setString(6, ab.getInsert_time());
						prestmt.setString(7, ab.getUpd_time());
						prestmt.execute();
						logger.info("*******************update CORP_AFTER_PERMIT 语句为【" + sql + "】，成功*******************");
					}

					try {
						if (rs != null)
							rs.close();
						if (st != null)
							st.close();
						if (prestmt != null)
							prestmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (prestmt != null)
					prestmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	/**
	 * 移动文件.
	 * 
	 * @param fromPath
	 *            源路径
	 * @param toPath
	 *            部门路径
	 * @param file
	 *            要移动的文件
	 * @param suffix
	 *            移动后的文件后缀
	 */
	public boolean moveFile(String fromPath, String toPath, File file, String suffix) {
		boolean flag = false;

		if (fromPath != null && toPath != null) {
			File fromFile = new File(fromPath + File.separator + file.getName());
			File destPath = new File(toPath);
			boolean isMoveSuccess = false;
			String suffixValue = null;
			if (!destPath.exists()) {
				destPath.mkdirs();
			}
			if (suffix != null) {
				suffixValue = "." + suffix;
				isMoveSuccess = fromFile.renameTo(new File(destPath + File.separator, file.getName() + suffixValue));
			} else {
				isMoveSuccess = fromFile.renameTo(new File(destPath + File.separator, file.getName()));
			}
			if (isMoveSuccess) {
				if (logLevel >= 1) {
					logger.info("******************文件从【" + fromPath + "】移动到【" + toPath + "】成功!******************");
					flag = true;
				}
			} else {
				if (logLevel >= 1) {
					logger.info("******************文件从【" + fromPath + "】移动到【" + toPath + "】失败!******************");
				}
			}
		}
		return flag;
	}

}