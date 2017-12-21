package com.wonders.sjtb.at;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
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

import com.wonders.log.entity.OperateLog;
import com.wonders.pzgl.entity.DwLog;
import com.wonders.query.entity.CorpInfo;
import com.wonders.sjtb.entity.TbColumns;
import com.wonders.sjtb.entity.TbColumnsSgs;
import com.wonders.sjtb.entity.TbContentsSgs;
import com.wonders.sjtb.entity.TbCorp;
import com.wonders.sjtb.entity.TbFileSgs;
import com.wonders.sjtb.service.SgsServiece;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;

@At("/sjtb/sgs")
@IocBean
public class SgsSjtbAt {

	@Inject
	private SgsServiece	sgsServiece;
	@Inject
	private Dao			dao;

	/**
	 * 
	 * @param type
	 *            类型 ：1、行政许可 2、行政处罚
	 * @return
	 */
	@At
	@Ok("jsp:jsp.sjtb.sgs.index")
	public Map<String, Object> toIndex(String type) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("contents", sgsServiece.getContentsSgsbyType(type));
		return result;
	}

	@At
	public void downloadMoban(String id, HttpServletResponse response) {
		sgsServiece.download(id, response);
	}

	@At
	@Ok("jsp:jsp.sjtb.sgs.tbym")
	public Map<String, Object> toTbym(String id, HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		Map<String, Object> result = sgsServiece.getTbymRecord(id, request, user);
		result.put("id", id);
		return result;
	}

	@At
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/upload/" })
	@Ok("jsp:jsp.sjtb.sgs.tbymbackgroud")
	public Map<String, Object> upload1(TempFile tempFile, HttpServletRequest req, HttpSession session, String id, String dept, HttpServletRequest request) throws IOException {
		TbFileSgs file = sgsServiece.upload1(tempFile, session, id, dept);

		Map<String, Object> result = sgsServiece.getTbymRecord1(request);
		result.put("state", file.getValid());
		result.put("fileid", file.getId());
		return result;
	}

	@At
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/upload/" })
	@Ok("jsp:jsp.sjtb.sgs.tbym")
	public Map<String, Object> upload(TempFile tempFile, HttpServletRequest req, HttpSession session, String id, HttpServletRequest request) throws IOException {
		TbFileSgs file = sgsServiece.upload(tempFile, session, id);
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		Map<String, Object> result = sgsServiece.getTbymRecord(id, request, user);
		OperateLog log = new OperateLog();
		log.setOperateDate(new Date());
		log.setOperateType("上报文件");
		log.setOperateUser(((User) session.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		if (file.getValid() == "0") {
			log.setOperateResult("双公示上报失败");
		} else if (file.getValid() == "1") {
			log.setOperateResult("双公示上报成功");
		}
		dao.insert(log);
		result.put("id", id);
		result.put("state", file.getValid());
		result.put("fileid", file.getId());
		return result;
	}

	@At
	@Ok("jsp:jsp.sjtb.sgs.fileerror")
	public Map<String, Object> toFileError(String fileid) {
		Map<String, Object> result = sgsServiece.getFileMsg(fileid);
		return result;
	}

	@At
	@Ok("jsp:jsp.sjtb.sgs.filesuccess")
	public Map<String, Object> success(String id, String uploadid) {
		Map<String, Object> result = new HashMap<String, Object>();
		TbContentsSgs content = dao.fetch(TbContentsSgs.class, id);
		final List<TbColumnsSgs> columns = dao.query(TbColumnsSgs.class, Cnd.where("CONTENT_ID", "=", content.getId()).and("IS_YUAN", "=", "1"));
		StringBuffer sqlstr = new StringBuffer();
		sqlstr.append("select ");
		List<String> namelist = new ArrayList<String>();
		for (TbColumnsSgs col : columns) {
			sqlstr.append(col.getColumnname()).append(",");
			namelist.add(col.getColumncomment());
		}
		namelist.add("填报月份");
		sqlstr.append("DATA_MON from ").append(content.getTablename());
		sqlstr.append(" where uploadid = '").append(uploadid).append("'");
		Sql sql = Sqls.create(sqlstr.toString());
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					for (TbColumnsSgs col : columns) {
						map.put(col.getColumnname(), rs.getString(col.getColumnname()));
					}
					map.put("data_mon", rs.getString("DATA_MON"));
					list.add(map);
				}
				return list;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) sql.getResult();
		result.put("list", list);
		result.put("namelist", namelist);
		result.put("count", list.size());
		result.put("name", content.getSheetname());
		return result;
	}

	/**
	 * 填报统计
	 * 
	 * @param datamonth
	 *            数据所属月份
	 * @param dataweek
	 *            数据所属周次
	 * @param type
	 *            统计类型 ：1、按月 2、按周
	 * @param operate
	 *            操作类型 ：1、上一月（周）2、下一月（周）
	 * @return
	 */
	@At
	@Ok("jsp:jsp.sjtb.sgs.count")
	public Map<String, Object> toCount(String datamonth, String dataweek, String type, String operate,Date jdrq1,Date jdrq2) {
		Map<String, Object> result = sgsServiece.count(datamonth, dataweek, type, operate,jdrq1,jdrq2);
		return result;
	}

	@At
	@Ok("jsp:jsp.sjtb.sgs.tbymbackgroud")
	public Map<String, Object> tbymbackground(HttpServletRequest request) {
		Map<String, Object> result = sgsServiece.getTbymRecord1(request);
		return result;
	}

	@At
	@Ok("jsp:jsp.sjtb.sgs.toexport")
	public Object toExport(String flag) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@At
	public void exportdata(String dept, String sjlx, String sjssyf, String sjssyf1, String jdrq1,String jdrq2,HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws WriteException, IOException {
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("导出法双公示数据列表");
		log.setCountType("sgs");
		log.setOperateUser(((User) session.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryTable(sjlx);
		List<Map<String, Object>> list = null;
		List<Map<String, Object>> list1 = null;
		Sql sql = Sqls.create("select COLUMN_NAME,COLUMN_COMMENT from tb_columns_sgs where CONTENT_ID = '" + sjlx.replaceAll("_", "") + "' order by COLUMN_LOCATION asc");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				// TODO Auto-generated method stub
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("COLUMN_NAME", rs.getString("COLUMN_NAME"));
					map.put("COLUMN_COMMENT", rs.getString("COLUMN_COMMENT"));
					list.add(map);
				}
				return list;
			}
		});
		dao.execute(sql);
		list = (List<Map<String, Object>>) sql.getResult();

		Map map = null;
		String netMode = null;
		StringBuffer ziduan = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			map = (HashMap) list.get(i);
			netMode = (String) map.get("COLUMN_NAME");
			ziduan.append(netMode).append(",");
		}
		ziduan.deleteCharAt(ziduan.length() - 1);
		String con = ziduan.toString();
		String sqlstr = "";
		String jdrq = "";
		StringBuffer buffer  = new StringBuffer("");
		if (!Strings.isEmpty(dept)) {
			sqlstr = "select " + con + " from " + sjlx + " where TBDEPT = '" + dept + "'" ;
		} else {
			sqlstr = "select " + con + ",TBDEPT from " + sjlx + " where 1=1";
		}
		if(!Strings.isEmpty(sjssyf)){
			sqlstr += " and DATA_MON between to_number(" + sjssyf.replaceAll("-", "") + ") and to_number("
					+ sjssyf1.replaceAll("-", "") + ")";
		}
		if("corp_xk".equals(sjlx)||"people_xk".equals(sjlx)){
			jdrq = "xk_jdrq";
		}
		if("corp_cf".equals(sjlx)||"people_cf".equals(sjlx)){
			jdrq = "cf_jdrq";
		}
		if(!Strings.isEmpty(jdrq1)){
			buffer.append("  and to_char(").append(jdrq).append(",'yyyy-mm-dd') >'").append(jdrq1).append("'");
		}
		if(!Strings.isEmpty(jdrq2)){
			buffer.append("  and to_char(").append(jdrq).append(",'yyyy-mm-dd') <='").append(jdrq2).append("'");
		}
		Sql sql1 = Sqls.create(sqlstr+buffer.toString());

		sql1.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				String[] name = new String[count];
				for (int i = 0; i < count; i++) {
					name[i] = rsmd.getColumnName(i + 1);
				}
				while (rs.next()) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					for (String key : name) {
						map1.put(key, rs.getString(key));
					}
					list1.add(map1);
				}
				return list1;
			}
		});
		dao.execute(sql1);
		list1 = (List<Map<String, Object>>) sql1.getResult();
		WritableWorkbook workbook = null;
		try {
			DicDataUtils dicutil = DicDataUtils.getInstance();
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(dicutil.getDicData(2008, sjlx) + ".xls", "utf-8"));
			response.setContentType("application/msexcel");
			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet(dicutil.getDicData(2008, sjlx), 0);
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行
			/** ***************以下是EXCEL第一行列标题********************* */
			sheet.addCell(new Label(0, 0, "序号", wcf_center));
			int i = 1;
			for (Map<String, Object> info : list) {
				sheet.addCell(new Label(i, 0, (String) (info.get("COLUMN_COMMENT")), wcf_center));
				i++;
			}
			sheet.addCell(new Label(i, 0, (String) ("数据所属单位"), wcf_center));
			/** ***************以下是EXCEL正文数据********************* */
			int d = 1;
			int j = 0;
			for (Map<String, Object> info : list1) {
				for (j = 0; j < list.size(); j++) {
					map = (HashMap) list.get(j);
					netMode = (String) map.get("COLUMN_NAME");
					sheet.addCell(new Label(j + 1, d, (String) (info.get(netMode) == null ? "" : info.get(netMode)), wcf_left));
				}
				if (Strings.isEmpty(dept)) {
					sheet.addCell(new Label(j + 1, d, dicutil.getDicData(1003, (String) (info.get("TBDEPT") == null ? "" : info.get("TBDEPT"))), wcf_left));
				} else {
					sheet.addCell(new Label(j + 1, d, dicutil.getDicData(1003, dept), wcf_left));
				}
				sheet.addCell(new Label(0, d, String.valueOf(d), wcf_left));
				d++;
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/** *********关闭文件************* */
			workbook.close();
		}
	}

	@At
	@Ok("json")
	public void countYuanqu() {
		List<TbColumns> columns = dao.query(TbColumns.class, Cnd.wrap("COUNT_FLAG in (1,2,3)"));
		DicDataUtils dicutil = DicDataUtils.getInstance();
		for (TbColumns col : columns) {
			List<TbCorp> corp = dao.query(TbCorp.class, Cnd.where("TABLENAME", "=", col.getTableBm()).and("SHEETID", "=", col.getSheetId()));
			if (corp.size() > 0) {
				for (TbCorp cor : corp) {
					CorpInfo corpinfo = null;
					if (!Strings.isEmpty(cor.getZzjgdm())) {
						corpinfo = dao.fetch(CorpInfo.class, Cnd.where("ORGAN_CODE", "=", cor.getZzjgdm()));
					}
					if (corpinfo == null && !Strings.isEmpty(cor.getShtyxydm())) {
						corpinfo = dao.fetch(CorpInfo.class, Cnd.where("UNI_SC_ID", "=", cor.getShtyxydm()));
					}
					if (corpinfo == null && !Strings.isEmpty(cor.getNsrsbh())) {
						corpinfo = dao.fetch(CorpInfo.class, Cnd.where("TAXPAYERS_CODE", "=", cor.getNsrsbh()));
					}
					if (corpinfo == null && !Strings.isEmpty(cor.getYyzzzch())) {
						corpinfo = dao.fetch(CorpInfo.class, Cnd.where("REG_NO", "=", cor.getYyzzzch()));
					}

				}
			}
		}
	}
}
