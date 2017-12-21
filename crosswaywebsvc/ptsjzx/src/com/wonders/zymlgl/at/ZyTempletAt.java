package com.wonders.zymlgl.at;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import com.wonders.Constants;
import com.wonders.log.entity.OperateLog;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.util.PropertyUtils;
import com.wonders.utils.DateUtils;
import com.wonders.zymlgl.common.ReadExcelDataToDb;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgl.entity.ResourceFile;
import com.wonders.zymlgl.service.QueryDataService;

/**
 * 资源目录模板.
 */
@IocBean(fields = "dao")
@At("/zy/templet")
public class ZyTempletAt {
	private Dao					dao;

	/** 日志 **/
	private static Logger		logger	= Logger.getLogger(ZyTempletAt.class);

	@Inject
	private ReadExcelDataToDb	readExcelDataToDb;
	@Inject
	QueryDataService			queryDataService;

	/**
	 * 资源目录-资源模板下载和上传页面.
	 */
	@At
	@Ok("jsp:jsp.zymlgl.zymlmb.catalog-templet")
	public Object catalogTemplet(String userId) {
		Map<String, Object> result = new HashMap<String, Object>();

		return result;
	}

	/**
	 * 资源目录模板下载.
	 * 
	 * @param name
	 * @param response
	 * @throws Exception
	 */
	@At
	public void downloadTemplet(String resourceId, String isCatalog, HttpServletResponse response) throws Exception {
		File file = null;
		Resource resource = null;
		String fileName = "";

		if (com.wonders.zymlgl.common.Constants.TEMPLET_TYPE_RESOURCE.equals(isCatalog)) {
			fileName = "resource-templet.xls";
			file = new File(getBasePath() + PropertyUtils.getProperty("downloadResourceTemplet") + "/" + "resource-templet.xls");
		} else {
			if (!Strings.isEmpty(resourceId)) {
				resource = dao.fetch(Resource.class, resourceId);
			}
			fileName = resource.getTableName() + ".xls";
			file = new File(getBasePath() + PropertyUtils.getProperty("downloadResourceTemplet") + "/" + resourceId + ".xls");
		}

		InputStream inStream = new FileInputStream(file);
		OutputStream outStream = response.getOutputStream();
		response.reset();

		response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));
		int tempbyte;
		while ((tempbyte = inStream.read()) != -1) {
			outStream.write(tempbyte);
			outStream.flush();
		}
		outStream.close();
		inStream.close();
	}

	private String getBasePath() {
		String path = PropertyUtils.getProperty("app.path");
		return path;
	}

	/**
	 * 获取上传的资源数据路径.
	 */
	private String getUploadResourceDataPath() {
		return getBasePath() + PropertyUtils.getProperty("upload.zysj.path");
	}

	/**
	 * 获取上传的资源目录路径.
	 */
	private String getUploadResourceCatalogPath() {
		return getBasePath() + PropertyUtils.getProperty("upload.zyml.path");
	}

	/**
	 * 资源目录-上传资源目录模板并解析xls入库.
	 */
	@At
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/uploadFile/" })
	@Ok("json")
	public Map<String, Object> uploadResourceCatalogTemplet(TempFile tempFile, HttpServletRequest req, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		String fileId = UUID.randomUUID().toString().replaceAll("-", "");
		ResourceFile resourceFile = new ResourceFile();
		resourceFile.setId(fileId);
		String fileName = fileId + DateUtils.getCurrDateStr() + DateUtils.getCurrTime() + ".xls";
		resourceFile.setFileName(fileName.replace("-", "").replace(":", ""));
		resourceFile.setUploadDate(new Date());
		resourceFile.setUploadPerson(((User) session.getAttribute(SystemConstants.SYSTEM_USER)).getUserId());
		resourceFile.setValid("1");
		resourceFile.setTempletType(com.wonders.zymlgl.common.Constants.TEMPLET_TYPE_RESOURCE);
		
		File file = tempFile.getFile();
		File dirFile = new File(getUploadResourceCatalogPath());
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File resultFile = new File(getUploadResourceCatalogPath() + "/" + resourceFile.getFileName());
		try {
			FileUtils.copyFile(file, resultFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dao.insert(resourceFile);
		
		OperateLog log = new OperateLog();
		log.setOperateDate(new Date());
		log.setOperateType("上报文件");
		log.setOperateUser(user.getLogonName());
		log.setOperateResult("上报成功");
		// 解析xls入库
		String xlsPath = getUploadResourceCatalogPath() + "/" + resourceFile.getFileName();
		try {
			readExcelDataToDb.readXlsToDb(xlsPath, resourceFile.getId(), session);
			log.setOperateResult("上报成功");
			logger.info("===================资源目录模板上传成功!====================");
		} catch (Exception e) {
			e.printStackTrace();
			log.setOperateResult("上报失败");
			logger.info("===================资源目录板上传失败!====================");
		}
		dao.insert(log);
		return result;
	}

	/**
	 * 资源目录-资源数据文件上传并解析xls入库.
	 * 
	 * @param resourceId
	 *            资源主键
	 * @param dataMon
	 *            数据所属月份
	 * @return
	 * @throws IOException
	 */
	@At
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/uploadFile/" })
	@Ok("jsp:jsp.zymlgl.zysj.version-list")
	public Map<String, Object> uploadResourceDataFile(TempFile tempFile, HttpServletRequest req, HttpSession session, String resourceId, String dataMonStr) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		Pager pager = ConUtils.makePaginationPager(req);
		Criteria cri = ConUtils.makeCri(req);
		OperateLog log = new OperateLog();
		log.setOperateDate(new Date());
		log.setOperateType("上报文件");
		log.setOperateUser(user.getLogonName());
		ResourceFile resourceFile = upload(tempFile, session, resourceId, dataMonStr);
		String readXlsPath = getUploadResourceDataPath() + "/" + resourceFile.getFileName();
		// 解析xls
		try {
			try {
				readExcelDataToDb.readXlsToDb(readXlsPath, resourceId, resourceFile.getId(), session,dataMonStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			log.setOperateResult("上报成功");
			logger.info("===================资源数据模板上传成功!====================");
		} catch (BiffException e) {
			log.setOperateResult("上报失败");
			logger.info("===================资源数据模板上传失败!====================");
			e.printStackTrace();
		}
		dao.insert(log);

		cri.where().andEquals("resourceId", resourceId);
		cri.getOrderBy().desc("uploadDate");
		List<ResourceFile> resourceFileList = dao.query(ResourceFile.class, cri, pager);
		pager.setRecordCount(dao.count(ResourceFile.class, cri));
		Resource resource = dao.fetch(Resource.class, resourceId);
		result.put("resourceFileList", resourceFileList);
		result.put("resourceId", resourceId);
		String tableName = Constants.PREFIX_TABLE + resource.getTableName() + Constants.SUFFIX_TABLE;
		result.put("tableName", tableName);
		return result;
	}

	private ResourceFile upload(TempFile tempFile, HttpSession session, String resourceId, String dataMon) {
		String fileId = UUID.randomUUID().toString().replaceAll("-", "");
		ResourceFile resourceFile = new ResourceFile();
		resourceFile.setId(fileId);
		String fileName = fileId + DateUtils.getCurrDateStr() + DateUtils.getCurrTime() + ".xls";
		resourceFile.setFileName(fileName.replace("-", "").replace(":", ""));
		resourceFile.setUploadDate(new Date());
		resourceFile.setUploadPerson(((User) session.getAttribute(SystemConstants.SYSTEM_USER)).getUserId());

		if (!Strings.isEmpty(dataMon)) {
			resourceFile.setDataMon(dataMon);
		}
		resourceFile.setResourceId(resourceId);
		resourceFile.setValid("1");
		resourceFile.setTempletType(com.wonders.zymlgl.common.Constants.TEMPLET_TYPE_DATA);

		File file = tempFile.getFile();
		File dirFile = new File(getUploadResourceDataPath());
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File resultFile = new File(getUploadResourceDataPath() + "/" + resourceFile.getFileName());
		try {
			FileUtils.copyFile(file, resultFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 当文件在本地生成后才取到其路径
		String xlsPath = getUploadResourceDataPath() + "/" + resourceFile.getFileName();
		// 设置数据上传量
		resourceFile.setDataCount(readExcelDataToDb.readXslCount(xlsPath));

		dao.insert(resourceFile);
		return resourceFile;
	}

	/**
	 * 进入数据上传页面.
	 * 
	 * @param resourceId
	 *            资源主键
	 * @return
	 */
	@At
	@Ok("jsp:jsp.zymlgl.zysj.version-list")
	public Map<String, Object> uploadDataUI(String resourceId, String dataMon, HttpServletRequest req, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(req);
		Criteria cri = ConUtils.makeCri(req);
		if (!Strings.isEmpty(dataMon)) {
			cri.where().andEquals("dataMon", dataMon);
		}

		cri.where().andEquals("resourceId", resourceId);
		cri.getOrderBy().desc("uploadDate");
		List<ResourceFile> resourceFileList = dao.query(ResourceFile.class, cri, pager);

		pager.setRecordCount(dao.count(ResourceFile.class, cri));
		Resource resource = dao.fetch(Resource.class, resourceId);
		result.put("resourceFileList", resourceFileList);
		result.put("pager", pager);
		result.put("resourceId", resourceId);
		String tableName = Constants.PREFIX_TABLE + resource.getTableName() + Constants.SUFFIX_TABLE;
		result.put("tableName", tableName);
		return result;
	}

	@At
	@Ok("jsp:jsp.zymlgl.zysj.query-file")
	public Object toZxbmList(String tableName, String fileId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("t_table", tableName);
		result.put("fileId", fileId);
		return result;
	}

	@At
	@Ok("json")
	public List countquery(String fileId, String table) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("select * from " + table + " where FILE_ID=" + "'" + fileId + "'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet result, Sql sql) throws SQLException {
				Map map = new LinkedHashMap();
				List list = new ArrayList();
				while (result.next()) {
					map = parseResultSetToMap(result);
					if (null != map) {
						list.add(map);
					}
				}
				return list;
			}
		});
		dao.execute(sql);
		// result.put("sql",sql.getResult());
		return (List) sql.getResult();
	}

	private static Map<String, Object> parseResultSetToMap(ResultSet rs) {
		if (null == rs) {
			return null;
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int colNum = meta.getColumnCount();
			for (int i = 1; i <= colNum; i++) {
				// 列名
				String name = meta.getColumnLabel(i); // i+1
				Object value = rs.getObject(i);
				if (value == null || value == "null") {
					value = " ";
				}
				// 加入属性
				map.put(name, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取最后一个版本号.
	 * 
	 * @param resourceId
	 *            表名
	 * @return 版本号
	 */
	@Deprecated
	public int getLastVersionNum(String resourceId) {
		int versionNum = 0;
		String sql = "select max(VERSION_NUM) from R_RESOURCE_FILE WHERE RESOURCE_ID='" + resourceId + "'";
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
		if (exeSql.getResult() != null) {
			versionNum = Integer.parseInt(exeSql.getResult().toString());
		}
		return versionNum;
	}
}
