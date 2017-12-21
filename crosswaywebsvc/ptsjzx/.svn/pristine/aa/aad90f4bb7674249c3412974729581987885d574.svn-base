package com.wonders.sjtb.at;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.sjtb.entity.TbColumns;
import com.wonders.sjtb.entity.TbContents;
import com.wonders.sjtb.entity.TbContentsSgs;
import com.wonders.sjtb.entity.TbSheet;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.util.PropertyUtils;

@At("/sjtb/config")
@IocBean
public class ConfigAt {
	@Inject
	private Dao dao;

	@At
	@Ok("jsp:jsp.sjtb.config.index")
	public Map<String, Object> toIndex(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		cri.getOrderBy().asc("DEPT_ID");
		List<TbContents> contents = dao.query(TbContents.class, cri, pager);
		pager.setRecordCount(dao.count(TbContents.class, cri));
		result.put("list", contents);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("jsp:jsp.sjtb.config.add")
	public Map<String, Object> toAdd() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	@At
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/upload/" })
	@Ok("jsp:jsp.sjtb.config.add2")
	public Map<String, Object> saveMoban1(String tablename,
			String historytablename, String filename, String filebm,
			TempFile file, String dept,String sjlx,int orderno) {
		Map<String, Object> result = new HashMap<String, Object>();
		TbContents content = new TbContents();
		content.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		content.setName(filebm);
		content.setDeptId(dept);
		content.setOrderNo(orderno);
		content.setTbFileName(filename);
		content.setZwm(sjlx);
		content.setTbFile(filebm+".xls");
		dao.insert(content);
		File dirFile = new File(getUploadPath());
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File moban =new File(getUploadPath()+"/"+content.getTbFile());
		try {
			FileUtils.copyFile(file.getFile(), moban);
			InputStream stream = new FileInputStream(file.getFile());
			Workbook rwb = Workbook.getWorkbook(stream);
			Sheet[] sheets = rwb.getSheets();
			List<String> sheet = new ArrayList<String>();
			List<List<TbColumns>> column = new ArrayList<List<TbColumns>>();
			for(int i=0;i<sheets.length;i++){
				TbSheet tbSheet =new TbSheet();
				tbSheet.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				tbSheet.setTabelBm(filebm);
				tbSheet.setOrderNo(i+1);
				tbSheet.setSheetName(sheets[i].getName().trim());
				dao.insert(tbSheet);
				sheet.add(sheets[i].getName().trim());
				List<TbColumns> columns = new ArrayList<TbColumns>();
				Cell[] row = sheets[i].getRow(0);
				for(int j=1;j<row.length;j++){
					TbColumns columns2 = new TbColumns();
					columns2.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					columns2.setColumnComment(row[j].getContents().trim());
					columns2.setTableBm(filebm);
					columns2.setTableName(tablename);
					columns2.setHistoryTableName(historytablename);
					columns2.setSheetId(i+1);
					columns2.setOrderNo(j);
					columns2.setSheetName(sheets[i].getName().trim());
					columns.add(columns2);
				}
				column.add(columns);
			}
			result.put("column", column);
			result.put("sheet", sheet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private String getUploadPath() {
		String path = PropertyUtils.getProperty("app.path")
				+ "download/tbmb";
		return path;
	}
	@At
	public View save(@Param("::column")List<TbColumns> column){
		StringBuffer table = new StringBuffer();
		StringBuffer historytable = new StringBuffer();
		StringBuffer comment = new StringBuffer();
		table.append("create table "+column.get(0).getTableName()+"(");
		historytable.append("create table "+column.get(0).getHistoryTableName()+"(");
		for(TbColumns tbColumns :column){
			String type="VARCHAR2(4000)";
			comment.append("COMMENT ON COLUMN "+column.get(0).getTableName()+""
					+ "."+tbColumns.getColumnName()+" is '"+tbColumns.getColumnComment()+"';");
			comment.append("COMMENT ON COLUMN "+column.get(0).getHistoryTableName()+""
					+ "."+tbColumns.getColumnName()+" is '"+tbColumns.getColumnComment()+"';");
			if(!Strings.isEmpty(tbColumns.getColumnType())){
			if(tbColumns.getColumnType().equals("VARCHAR")){
				type="VARCHAR2(4000)";
			}
			if(tbColumns.getColumnType().equals("DATE")){
				type="DATE";
				tbColumns.setDateType("yyyy年MM月dd日");
				tbColumns.setParseDateType("yyyy-MM-dd");
			}
			if(tbColumns.getColumnType().equals("NUMBER")){
				type="NUMBER";
			}
			}
			dao.insert(tbColumns);
			table.append(tbColumns.getColumnName() +" "+type+" ,");
			historytable.append(tbColumns.getColumnName() +" "+type+" ,");
		}
		table.append("DATA_YEAR VARCHAR2(10),DATA_MON  VARCHAR2(5),SBR       VARCHAR2(64),ID VARCHAR2(64))");
		historytable.append("FILE_ID   VARCHAR2(64),SBR       VARCHAR2(64),SHEET_ID  VARCHAR2(5), DATA_YEAR VARCHAR2(10),DATA_MON  VARCHAR2(5),ID VARCHAR2(64))");
		Sql sql1=Sqls.create(table.toString());
		Sql sql2=Sqls.create(historytable.toString());
		dao.execute(sql1,sql2);
		for(String sqlstr:comment.toString().split(";")){
			Sql sql = Sqls.create(sqlstr);
			dao.execute(sql);
		}
		return new ServerRedirectView("/sjtb/config/toIndex");
	}
	
	/**
	 * 数据填报综合统计
	 * @return
	 */
	@At
	@Ok("jsp:jsp.sjtb.allcount")
	public Map<String, Object> toCount(){
		Map<String, Object> result = new HashMap<String, Object>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
		String datamon = format.format(c.getTime());
		String sgsdatamom = format.format(new Date());
		Map<String, String> dept = DicDataUtils.getInstance().getDic(1069);
		Map<String, Integer[]> data = new HashMap<String, Integer[]>();
		for(String key:dept.keySet()){
			Integer[] row = new Integer[7];
			for(int i=0;i<3;i++){
				Map<String, Object> map2 = new HashMap<String, Object>();
				TbContents contents = dao.fetch(TbContents.class,Cnd.where("DEPT_ID","=",key).and("ORDER_NO","=",i+1));
				if(contents!=null){
//					
					Sql sql1=Sqls.create("select count(1) count from "+contents.getTableName()+" where DATA_YEAR ='"+datamon+"'");
					sql1.setCallback(new SqlCallback() {
						
						@Override
						public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
							// TODO Auto-generated method stub
							
							while (rs.next()) {
								return rs.getInt("count");
							}
							return 0;
						}
					});
					dao.execute(sql1);
					int a4 = (Integer) sql1.getResult();
					row[i]=a4;	
				}
			}
			data.put(key, row);
		}
		
		List<TbContentsSgs> contents = dao.query(TbContentsSgs.class,Cnd.cri().desc("TABLE_NAME"));
		for(int i=0;i<contents.size();i++){
			String string = "select TBDEPT ,count(1) from "+contents.get(i).getTablename()+" where DATA_MON='"+sgsdatamom+"' group by TBDEPT";
			Sql sql = Sqls.create(string);
			sql.setCallback(new SqlCallback() {
			
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					// TODO Auto-generated method stub
					Map<String, Integer> map = new HashMap<String, Integer>(); 
					while(rs.next()){
						map.put(rs.getString(1), rs.getInt(2));
					}
					return map;
				}
			});
			
			dao.execute(sql);
			Map<String, Integer> teMap = (Map<String, Integer>) sql.getResult();
			for(String key:teMap.keySet()){
				if(data.containsKey(key)){
					data.get(key)[i+3] = teMap.get(key);
				}else{
					Integer[] row = new Integer[7];
					row[i+3] = teMap.get(key);
					data.put(key, row);
				}
			}	
			
		}
		result.put("data", data);
		return result;
		
	}
}
