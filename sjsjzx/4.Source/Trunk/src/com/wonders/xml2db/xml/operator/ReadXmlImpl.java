package com.wonders.xml2db.xml.operator;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.nutz.lang.Strings;

import com.wonders.utils.PropertyUtils;

public class ReadXmlImpl implements IReadXml {
	private Logger				logger				= Logger.getLogger(ReadXmlImpl.class);

	private Connection			conn				= null;

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

	public ReadXmlImpl(Connection conn) {
		this.conn = conn;

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
	 * 获取xml根节点.
	 */
	public Element getXmlRoot() {
		Element root = null;
		try {
			SAXReader sax = new SAXReader();// 创建一个SAXReader对象
			File xmlFile = new File(PropertyUtils.getProperty("filePath"));// 根据指定的路径创建file对象
			sax.setEncoding("gbk");
			Document document;
			document = sax.read(xmlFile);
			root = document.getRootElement();// 获取根节点
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return root;
	}

	/**
	 * 根据指定文件获取xml根节点.
	 * 
	 * @param file
	 * @return
	 */
	public Element getXmlRoot(File xmlFile) {
		Element root = null;
		try {
			SAXReader sax = new SAXReader();// 创建一个SAXReader对象
			sax.setEncoding("gbk");
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
	public void readXml(Element root) {
		PreparedStatement pstmt = null;
		try {
			// 现在应该根据根节点找到全部的子节点
			Iterator<?> iter = root.elementIterator();

			while (iter.hasNext()) {
				Element item = (Element) iter.next();// 取得每一条数据

				String dbName = analysisTableName(item.getName());// 数据库表名称
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
						if (update(pkName, currentId, fieldMaps, dbName.toUpperCase())) {
							// insertData(this.conn, insertSql.toString());
							logger.info("**************ID为【" + currentId + "】的数据更新成功!***************");
						} else {
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
						insertData(this.conn, insertSql.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(pstmt);
		}
	}

	private String analysisTableName(String nodeName) {
		if ("corporation_info".equals(nodeName))
			return "CORP_INFO";
		if ("corp_assess".equals(nodeName))
			return "CORP_ASSESS";
		if ("corp_renew_normal".equals(nodeName))
			return "CORP_RENEW_NORMAL";
		return null;
	}

	public Map<String, Object> prepareMap(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return map;
	}

	public boolean insertData(Connection conn, String sql) throws SQLException {
		boolean flag = false;
		PreparedStatement pstmt = null;
		pstmt = this.conn.prepareStatement(sql);
		if (pstmt.executeUpdate() > 0) {
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
	public boolean update(String pkName, String pkValue, List<Map<String, Object>> listMap, String tableName) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		StringBuffer sql = new StringBuffer("update " + tableName + " set ");
		for (Map<String, Object> map : listMap) {
			Set<Map.Entry<String, Object>> allSet = map.entrySet();
			Iterator<Map.Entry<String, Object>> iter = allSet.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> me = iter.next();
				if (!Strings.isEmpty(me.getValue().toString()) && me.getValue() != null) {
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
				flag = true;
				if (logLevel >= 1) {
					logger.info("********************成功更新ID为：【" + pkValue + "】的数据********************");
				}
			}
		} catch (SQLException e) {
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

	@Override
	public void readXmlsByPath(String path) {
		File file = new File(path);
		if (Strings.isEmpty(path) || !file.exists()) {
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
					this.readXml(this.getXmlRoot(f));
					this.moveFile(PropertyUtils.getProperty("filePath"), PropertyUtils.getProperty("bakPath"), f, PropertyUtils.getProperty("suffix"));
				}
			}
		} else {// 如果不是目录
			this.readXml(getXmlRoot(file));
			this.moveFile(PropertyUtils.getProperty("filePath"), PropertyUtils.getProperty("bakPath"), file, PropertyUtils.getProperty("suffix"));
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
		if (Strings.isEmpty(fromPath) || Strings.isEmpty(toPath)) {
			if (Strings.isEmpty(fromPath)) {
				if (logLevel >= 1) {
					logger.info("******************移动文件的源路径不能为空!******************");
				}
			}
			if (Strings.isEmpty(toPath)) {
				if (logLevel >= 1) {
					logger.info("******************移动文件的目标径不能为空!******************");
				}
			}
			if (Strings.isEmpty(suffix)) {
				if (logLevel >= 1) {
					logger.info("******************请指定移动文件后的文件名后缀!******************");
				}
			}
			return false;
		}

		if (!Strings.isEmpty(fromPath) && !Strings.isEmpty(toPath)) {
			File fromFile = new File(fromPath + File.separator + file.getName());
			File destPath = new File(toPath);
			boolean isMoveSuccess = false;
			String suffixValue = null;
			if (!destPath.exists()) {
				destPath.mkdirs();
			}
			if (!Strings.isEmpty(suffix)) {
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
