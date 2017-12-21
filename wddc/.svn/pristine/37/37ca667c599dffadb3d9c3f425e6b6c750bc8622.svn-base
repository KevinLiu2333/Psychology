package com.wonders.wddc.suite.csrq.at;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wddc.suite.csrq.entity.ChartConfigBo;
import com.wonders.wddc.tiles.sn.SnCreator;

/**
 * 数据图表处理类
 * @author Administrator
 *
 */
@At("/suite/chart")
@IocBean(fields = "dao")
public class ChartAct {

	private Dao dao;
	
	/**
	 * 数据图表列表信息
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.charts.echarts_maintainList")
	public Map<String,Object> maintainList(){
		Map<String,Object> result = new HashMap<String,Object>();
		List<ChartConfigBo> echartList = dao.query(ChartConfigBo.class, null);
		result.put("echartList", echartList);
		return result;
	}
	
	/**
	 * 数据图表详细信息
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.charts.echarts_preview")
	public Map<String,Object> detailView(String id,String currDate){
		Map<String,Object> result = new HashMap<String,Object>();
		ChartConfigBo echart = dao.fetch(ChartConfigBo.class, id);
		echart.setOperateCount(echart.getOperateCount()+1);
		dao.update(echart);
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if(currDate==null){
			currDate = dft.format(new Date());
		}
		result.put("date", currDate);
		result.put("echart", echart);
		
		return result;
		
	}
	/**
	 * 数据图表详细信息
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.charts.echarts_preview2")
	public Map<String,Object> detailView2(String id){
		Map<String,Object> result = new HashMap<String,Object>();
		ChartConfigBo echart = dao.fetch(ChartConfigBo.class, id);
		echart.setOperateCount(echart.getOperateCount()+1);
		dao.update(echart);
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		result.put("echart", echart);
		
		return result;
		
	}
	
	/**
	 * 数据图表信息
	 */
	@At
	@Ok("json")
	public Map<String,Object> getEchartsObj(String id,String currDate){
		
		Map<String,Object> result = new HashMap<String,Object>();
		ChartConfigBo echart = dao.fetch(ChartConfigBo.class, id);
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if(currDate==null){
			currDate = dft.format(new Date());
		}
		result.put("date", currDate);
		result.put("echart", echart);
		
		return result;
	}
	/**
	 * 跳转到echarts新增界面
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.charts.echarts_add")
	public void preAddChart(){
		
	}
	/**
	 * 跳转到echarts编辑界面
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.charts.echarts_edit")
	public Map<String,Object> toEditEChart(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		if(!Strings.isEmpty(id)){
			ChartConfigBo echart = dao.fetch(ChartConfigBo.class, id);
			
			result.put("echart", echart);
		}
			return result;
	}
	/**
	 * 更新echarts配置信息  
	 */
	@At
	@Ok("redirect:/suite/chart/maintainList")
	public void updateOrSaveEchart(@Param("::echart.") ChartConfigBo echart ){
		
		if(echart != null){
			
			echart.setGenerateTime(new Date());
			if(Strings.isEmpty(echart.getId())){
				echart.setId(SnCreator.getInstance().getSN("echrts", 5, "E"));
				dao.insert(echart);
			}else{
				dao.update(echart);
			}
		}
	}
	/**
	 * 根据图表id删除图表
	 * @param id  图表id
	 */
	@At
	@Ok("redirect:/suite/chart/maintainList")
	public void deleteEchart(String id){
		if(!Strings.isEmpty(id)){
			ChartConfigBo echart = dao.fetch(ChartConfigBo.class, id);
			if(echart != null){
				dao.delete(echart);
			}
		}
	}
	
	
	
	
	
	
}
