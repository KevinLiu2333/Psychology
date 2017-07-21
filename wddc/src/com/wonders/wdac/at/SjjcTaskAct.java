package com.wonders.wdac.at;


import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wonders.wdac.entity.SjjcDataFeedbackBo;
import com.wonders.wdac.entity.SjjcIffyBo;
import com.wonders.wdac.entity.SjjcTaskBo;
import com.wonders.wddc.suite.csrq.entity.ColConfigBo;
import com.wonders.wddc.suite.data.entity.DataFileDataBo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;
import com.wonders.wddc.tiles.sn.SnCreator;

@At("/wdac/dataCheck")
@IocBean(fields = "dao")
public class SjjcTaskAct {
	
	private Dao dao ;
	
	
	/**
	 * 问题数据检测 - 首页
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.check_index")
	public Map<String, Object> toCheckIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<SjjcTaskBo> info = dao.query(SjjcTaskBo.class, null);
		result.put("info", info);
		return result;
	}
	
	/**
	 * 问题数据检测 - 删除任务
	 * @param id
	 */
	@At
	public void delectTask(String id){
		dao.delete(SjjcTaskBo.class,id);
	}
	
	/**
	 * 问题数据检测 - 添加任务step1
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.check_step1")
	public Map<String, Object> toCheckStep1(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		id = "DB00001";
		if (id != null && id != "" && id.length() > 0) {
			List<TableConfigBo> list = dao.query(TableConfigBo.class, Cnd.where("DATA_SOURCE_ID","=",id));
			result.put("list", list);
			result.put("dbId", id);
		}else {
			List<TableConfigBo> list = dao.query(TableConfigBo.class, null);
			result.put("list", list);
		}
		return result;
	}
	
	/**
	 * 问题数据检测 - 添加任务step2
	 * @param themeId
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.check_step2")
	public Map<String, Object> toCheckStep2(String themeId){
		Map<String, Object> result = new HashMap<String, Object>();
		List<ColConfigBo> info = dao.query(ColConfigBo.class, Cnd.where("THEME_ID","=",themeId));
		result.put("info", info);
		return result;
	}
	
	/**
	 * 问题数据检测 - 添加任务step3
	 * @param colCfgId
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.check_step3")
	public Map<String, Object> toCheckStep3(String colCfgId,String id){
		Map<String, Object> result = new HashMap<String, Object>();
		// 根据id来判断是直接点击任务名称进入本页面还是根据步骤进入本页面
		if (id == null || id == "") {
			String[] arr = colCfgId.split(":");
			//根据arr的length判断前台是全选还是单选
			if (arr.length > 1) {
				TableConfigBo info = dao.fetch(TableConfigBo.class,Integer.parseInt(arr[1]));
				result.put("info", info);
				result.put("data", "all");
			}else {
				ColConfigBo colConfig = dao.fetch(ColConfigBo.class,Integer.parseInt(colCfgId));
				TableConfigBo info = dao.fetch(TableConfigBo.class,colConfig.getThemeId());
				result.put("info", info);
				result.put("data", colConfig);
			}
		}else {
			SjjcTaskBo info =dao.fetch(SjjcTaskBo.class,id);
			result.put("info", info);
			result.put("data", "none");
		}
		
		return result;
	}
	
	/**
	 * 问题数据检测 - 保存任务
	 * @param info
	 * @return
	 */
	@At
	public View toSaveTask(@Param("::info.") SjjcTaskBo info){
		info.setId(UUID.randomUUID().toString().replace("-", ""));
		info.setCreateTime(new Date());
		if (Strings.isEmpty(info.getTaskNumber())) {
			info.setTaskNumber(SnCreator.getInstance().getSN("task_number", 5, "TN"));
		}
		info.setLastUpdate(new Date());
		dao.insert(info);
		TableConfigBo tableConfigBo = dao.fetch(TableConfigBo.class,Cnd.where("VIEW_NAME","=",info.getResourceTable()));
		// 新建任务的同时新建与此任务关联的反馈信息
		SjjcDataFeedbackBo sjjcDataFeedbackBo = new SjjcDataFeedbackBo();
		sjjcDataFeedbackBo.setId(info.getId());
		sjjcDataFeedbackBo.setTaskNumber(info.getTaskNumber());
		sjjcDataFeedbackBo.setTaskName(info.getTaskName());
		sjjcDataFeedbackBo.setTaskRules(info.getTaskRules());
		sjjcDataFeedbackBo.setCheck_time(new Date());
		sjjcDataFeedbackBo.setResourceTable(info.getResourceTable());
		sjjcDataFeedbackBo.setProviderDepartment(info.getProviderDepartment());
		sjjcDataFeedbackBo.setCount(tableConfigBo.getTotalCount());
		dao.insert(sjjcDataFeedbackBo);
		return new ServerRedirectView("/wdac/dataCheck/toCheckIndex");
	}
	
	/**
	 * 问题数据检测 - 编辑任务
	 * @param info
	 * @return
	 */
	@At
	public View toEditTask(@Param("::info.") SjjcTaskBo info){
		SjjcTaskBo taskBo = dao.fetch(SjjcTaskBo.class,info.getId());
		taskBo.setTaskName(info.getTaskName());
		taskBo.setTaskRules(info.getTaskRules());
		taskBo.setTaskDesc(info.getTaskDesc());
		taskBo.setLastUpdate(new Date());
		dao.update(taskBo);
		return new ServerRedirectView("/wdac/dataCheck/toCheckIndex");
	}
	
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.check_job_gl")
	public Map<String, Object> toJobGl(){
		return null;
	}
	
	/**
	 * 问题数据查询
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.search_index")
	public Map<String, Object> toSearchIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<SjjcIffyBo> info = dao.query(SjjcIffyBo.class, null);
		result.put("info", info);
		return result;
	}
	
	/**
	 * 导出
	 * @param taskNumber
	 * @param req
	 * @param res
	 */
	@At
	public void export(String taskNumber,HttpServletRequest req, HttpServletResponse res){
		SjjcIffyBo sjjcIffyBo = dao.fetch(SjjcIffyBo.class,Cnd.where("TASK_NUMBER","=",taskNumber));
		// 拼接导出excel需要的sql语句
		String sqlString = "SELECT * FROM "+ sjjcIffyBo.getResourceTable()+" WHERE IS_"+sjjcIffyBo.getType()+"=\""+sjjcIffyBo.getType()+"\"";
		Sql sql = Sqls.create(sqlString);
		// 设置最大导出数量为10000条
		Pager pager = dao.createPager(1, 10000);
		sql.setPager(pager);
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql sql)
					throws SQLException {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				ResultSetMetaData rsmd = rs.getMetaData() ; 
				int columnCount = rsmd.getColumnCount();
				while (rs.next()) {
					ArrayList<String> info = new ArrayList<String>();
					for(int i=1;i <= columnCount;i++){
						info.add(rs.getString(i));
					}
					list.add(info);
				}
				return list;
			}
		});
		dao.execute(sql);
		ArrayList<ArrayList<Object>> result = (ArrayList<ArrayList<Object>>) sql.getResult();
		exportExcel(result,taskNumber,req,res);
	}
	
	/**
	 * 导出excel
	 * @param result
	 * @param taskNumber
	 * @param request
	 * @param resp
	 */
	public void exportExcel(ArrayList<ArrayList<Object>> result,String taskNumber, HttpServletRequest request, HttpServletResponse resp){
		HSSFWorkbook wb = new HSSFWorkbook();
		SjjcTaskBo sjjcTaskBo = dao.fetch(SjjcTaskBo.class,Cnd.where("TASK_NUMBER","=",taskNumber));
		TableConfigBo tableConfigBo = dao.fetch(TableConfigBo.class,Cnd.where("VIEW_NAME","=",sjjcTaskBo.getResourceTable()));
		List<ColConfigBo> colList = dao.query(ColConfigBo.class, Cnd.where("THEME_ID","=",tableConfigBo.getThemeId()));
		List<String> headList = new ArrayList<String>();
		List<String> headCodeList = new ArrayList<String>();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("application/x-download;charset=utf8");

		String fileName = sjjcTaskBo.getTaskName()+".xls";
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultRowHeight((short) (2 * 256));
		sheet.setColumnWidth(0, 50 * 160);
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (int i = 0; i < colList.size(); i++) {
			headList.add(colList.get(i).getColComment());
			headCodeList.add(colList.get(i).getColName());
		}
		headList.add("任务编号");
		headList.add("更新时间");
		headCodeList.add("TASK_NUMBER");
		headCodeList.add("UPDATE_TIME");
		HSSFRow row = sheet.createRow(0); //第一行设置中文表头
		HSSFRow row1 = sheet.createRow(1);//第二行设置英文(数据库对应字段)表头
		for (int i = 0; i < headList.size(); i++) {
			HSSFCell cell = row.createCell(i);
			HSSFCell cell1 = row1.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(headList.get(i));
			cell1.setCellStyle(style);
			cell1.setCellValue(headCodeList.get(i));
		}
	
		for (int i = 0; i < result.size(); i++)
		{
			//从第三行开始插入数据
			HSSFRow row2 = sheet.createRow((int) i + 2);
			for (int j = 0; j < headList.size(); j++) {
				HSSFCell cell = row2.createCell(j);
				cell.setCellValue(result.get(i).get(j)== null?"":result.get(i).get(j).toString());
			}
		}
		try
			{
				OutputStream out = resp.getOutputStream();
				wb.write(out);
				out.close();
				SjjcDataFeedbackBo sjjcDataFeedbackBo = dao.fetch(SjjcDataFeedbackBo.class,Cnd.where("task_number","=",taskNumber));
				sjjcDataFeedbackBo.setFeedback("结果已导出");
				sjjcDataFeedbackBo.setFeedbackTime(new Date());
				dao.update(sjjcDataFeedbackBo);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

	}
	
	/**
	 * 问题数据统计
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.tj_index")
	public Map<String, Object> toTjIndex(){
		//数据更新时间为数据组装的缓存时间，若没有缓存，则显示当前时间
		Map<String, Object> result=new HashMap<String, Object>();
		DataFileDataBo dataBo = dao.fetch(DataFileDataBo.class,"F00038");
		if (dataBo == null) {
			result.put("updateTime", new Date());
		}else {
			result.put("updateTime", dataBo.getUpdatetime());
		}
		
		return result;
	}
	
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.fk_index")
	public Map<String, Object> toFkIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<SjjcDataFeedbackBo> list = dao.query(SjjcDataFeedbackBo.class, null);
		result.put("list", list);
		return result;
	}
	
	@At
	@Ok("jsp:wdac.dataDeal.dataCheck.log")
	public Map<String, Object> toLog(){
		return null;
	}
}
