package com.wonders.wdac.at;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.wdac.entity.SjbdTask;
import com.wonders.wdac.entity.SjjcCxBo;
import com.wonders.wdac.entity.SjjcDataFeedbackBo;
import com.wonders.wdac.entity.SjjcIffyBo;
import com.wonders.wdac.entity.SjjcTaskBo;
import com.wonders.wdac.entity.TagInfoBo;
import com.wonders.wddc.suite.csrq.entity.ColConfigBo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;
import com.wonders.wddc.tiles.sn.SnCreator;

@At("/wdac/dataDuibi")
@IocBean(fields = "dao")
public class SjdbAct {

	private Dao dao;

	/**
	 * 对比方案设置 - 首页
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_index")
	public Map<String, Object> toDuibiIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<SjbdTask> info = dao.query(SjbdTask.class, null);
		result.put("info", info);
		return result;
	}

	/**
	 * 对比方案设置 - 删除任务
	 * 
	 * @param id
	 */
	@At
	public void delectTask(String id) {
		dao.delete(SjbdTask.class, id);
	}

	/**
	 * 对比方案设置 - 增量对比1
	 * 
	 * @param id
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_tjjcrw1")
	public Map<String, Object> toDuibitjjcrw1(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		id = "DB00001";
		if (id != null && id != "" && id.length() > 0) {
			List<TableConfigBo> list = dao.query(TableConfigBo.class,
					Cnd.where("DATA_SOURCE_ID", "=", id));
			result.put("list", list);
			result.put("dbId", id);
		} else {
			List<TableConfigBo> list = dao.query(TableConfigBo.class, null);
			result.put("list", list);
		}
		return result;
	}

	/**
	 * 对比方案设置 - 增量对比2
	 * 
	 * @param id
	 * 
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_tjjcrw2")
	public Map<String, Object> toDuibitjjcrw2(String mbb, String czb) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<ColConfigBo> info = dao.query(ColConfigBo.class,
				Cnd.where("THEME_ID", "=", mbb));
		result.put("info", info);
		result.put("czb", czb);
		return result;
	}

	/**
	 * 对比方案设置 - 增量对比3
	 * 
	 * @param id
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_tjjcrw3")
	public Map<String, Object> toDuibitjjcrw3(String colCfgId, String id,
			String czb) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 根据id来判断是直接点击任务名称进入本页面还是根据步骤进入本页面
		if (id == null || id == "") {
			String[] arr = colCfgId.split(":");
			// 根据arr的length判断前台是全选还是单选
			if (arr.length > 1) {
				TableConfigBo info = dao.fetch(TableConfigBo.class,
						Integer.parseInt(arr[1]));
				result.put("info", info);
				result.put("data", "all");
			} else {
				ColConfigBo colConfig = dao.fetch(ColConfigBo.class,
						Integer.parseInt(colCfgId));
				TableConfigBo info = dao.fetch(TableConfigBo.class,
						colConfig.getThemeId());
				TableConfigBo cxf = dao.fetch(TableConfigBo.class,
						Cnd.where("THEME_ID", "=", czb));
				result.put("cxf", cxf);
				result.put("info", info);
				result.put("data", colConfig);

			}
		} else {
			SjbdTask info = dao.fetch(SjbdTask.class, id);

			result.put("info", info);
			result.put("data", "none");

		}

		return result;
	}

	/**
	 * 问题数据检测 - 保存任务
	 * 
	 * @param info
	 * @return
	 */
	@At
	public View toSaveTask(@Param("::info.") SjbdTask info) {
		info.setId(UUID.randomUUID().toString().replace("-", ""));
		info.setCreateTime(new Date());
		if (Strings.isEmpty(info.getTaskNumber())) {
			info.setTaskNumber(SnCreator.getInstance().getSN("task_number", 5,
					"TN"));
		}
		dao.insert(info);
		TableConfigBo tableConfigBo = dao.fetch(TableConfigBo.class,
				Cnd.where("VIEW_NAME", "=", info.getTargetTable()));
		TableConfigBo tableConfigBo1 = dao.fetch(TableConfigBo.class,
				Cnd.where("VIEW_NAME", "=", info.getReferTable()));
		SjjcCxBo sc = new SjjcCxBo();
		sc.setId(info.getId());
		sc.setTaskName(info.getTaskName());
		sc.setTaskNumber(info.getTaskNumber());
		sc.setTaskDesc(info.getTaskDesc());
		sc.setTaskRules(info.getTaskRules());
		sc.setBdDate(new Date());
		sc.setItemCode(info.getItemCode());
		sc.setItemName(info.getItemName());
		sc.setTargetTable(tableConfigBo.getViewName());
		sc.setReferTable(tableConfigBo1.getViewName());
		dao.insert(sc);

		return new ServerRedirectView("/wdac/dataDuibi/toDuibiIndex");
	}

	/**
	 * 问题数据检测 - 查看任务
	 * 
	 * @param info
	 * @return
	 */
	@At
	public View toEditTask(@Param("::info.") SjbdTask info) {
		SjbdTask taskBo = dao.fetch(SjbdTask.class, info.getId());
		taskBo.setTaskName(info.getTaskName());
		taskBo.setTaskRules(info.getTaskRules());
		taskBo.setTaskDesc(info.getTaskDesc());

		return new ServerRedirectView("/wdac/dataDuibi/toDuibiIndex");
	}

	/**
	 * 对比数据查询
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.bdjgcx")
	public Map<String, Object> toDuibibdjgcx() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<SjjcCxBo> info = dao.query(SjjcCxBo.class, null);
		result.put("info", info);

		return result;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.bdjgtj")
	public Map<String, Object> toDuibibdjgtj() {
		Map<String, Object> result = new HashMap<String, Object>();
		Object zj = dao.func2(SjjcCxBo.class, "sum", "zj_data");
		Object sj = dao.func2(SjjcCxBo.class, "sum", "js_data");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> xz = new ArrayList<Object>();
		// List<Integer> jss = new ArrayList<Integer>();
		xz.add(zj);
		xz.add(sj);

		map.put("xz", xz);
		// map.put("jss", jss);
		// map.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new
		// Date()));

		result.put("data", JSONObject.fromObject(map));
		return result;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_job_gl")
	public Map<String, Object> toJobGli() {

		return null;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.log")
	public Map<String, Object> toLog() {

		return null;
	}

}
