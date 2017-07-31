package com.wondersgroup.cmc.quartz.action;

import java.util.List;

import com.wondersgroup.cmc.model.bo.QrtzTriggerConfig;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.quartz.utils.QrtzTriggerConfigUtils;
import com.wondersgroup.cmc.quartz.vo.CheckTriggerVO;
import com.wondersgroup.cmc.quartz.vo.QrtzTriggersVO;
import com.wondersgroup.cmc.utils.AjaxUtils;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;
import com.wondersgroup.wssip.util.BeanTools;
import com.wondersgroup.wssip.util.RequestUtils;

public class QuartzTriggerConfigAction extends BaseAjaxAction{
	
	private static final long serialVersionUID = 1L;
	private QrtzTriggersVO qrtzTriggersVO = new QrtzTriggersVO();
	private QrtzTriggerConfigUtils qrtzTriggerConfigUtils;

	public void setQrtzTriggerConfigUtils(
			QrtzTriggerConfigUtils qrtzTriggerConfigUtils) {
		this.qrtzTriggerConfigUtils = qrtzTriggerConfigUtils;
	}

	public PageQueryDTO getQueryDTO(){
		PageQueryDTO dto = new PageQueryDTO(this.getServletRequest());
		return dto;
	}
	
	@Override
	public ValueObject getValueObject() {
		return qrtzTriggersVO;
	}
	
	/**
     * 查询任务配置，在任务调度配置中任务名称下拉框
     * @return
     */
	public String queryTaskBeanBy(){
    	 List<QrtzTriggerConfig> qrtzTriggerConfig = qrtzTriggerConfigUtils.findAllTask();
         createJSonData(VOUtils.getJsonDataFromCollection(qrtzTriggerConfig));
         return AJAX;
    }
	
	/**
	 * 检测是否存在同类型的并且状态为WAITING或ACQUIRED
	 * @return
	 */
	public String checkTriggerExits(){
		boolean isExits = false;
		String triggerConfigId = RequestUtils.getParameter(this.getServletRequest(), "triggerConfigId", "");
        String triggerName = "";
        List<CheckTriggerVO> checkTriggerVOs = qrtzTriggerConfigUtils.checkTriggerexits(triggerConfigId);
        if (checkTriggerVOs != null && checkTriggerVOs.size() > 0) {
        	//同一种等待中或者运行中的数据只有一条
    		triggerName = checkTriggerVOs.get(0).getTriggerName();
        	isExits = true;
        }
        CheckTriggerVO checkTriggerVO = new CheckTriggerVO();
        checkTriggerVO.setExistsflag(isExits);
        checkTriggerVO.setTriggerName(triggerName);
        createJSonData(AjaxUtils.getJsonData(checkTriggerVO));
        return AJAX;
	}
	
	/**
	 * 保存触发器
	 * @return
	 */
	public String saveTrigger(){
		qrtzTriggerConfigUtils.saveTirgger(qrtzTriggersVO);
		createJSonData(AjaxUtils.getJsonData(qrtzTriggersVO));
		return AJAX;
	}
	
	/**
	 * 查询所有的触发器
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAllQuartzTrigger(){
		// (1) new 查询条件实例
		PageQueryDTO dto = getQueryDTO();
		// (2) 执行查询
		DataGrid dg = qrtzTriggerConfigUtils.findAllQuartzTrigger(dto);
		createJSonData(AjaxUtils.getJsonData(dg));
		return AJAX;
	}
    /**
     * 更该状态，triggerState为0是暂停,为1是恢复
     * @return
     */
	public String changeStatues(){
		qrtzTriggerConfigUtils.changeStatues(qrtzTriggersVO);
		createJSonData(AjaxUtils.getJsonData(qrtzTriggersVO));
		return AJAX;
	}
	
	/**
	 * 移除触发器
	 * @return
	 */
	public String removeTrigger(){
		qrtzTriggerConfigUtils.removeTrigger(qrtzTriggersVO);
		createJSonData(AjaxUtils.getJsonData(qrtzTriggersVO));
		return AJAX;
	}
	
	/**
	 * 保存任务配置
	 * @return
	 */
	public String saveTaskBean(){
		qrtzTriggerConfigUtils.saveTaskBean(qrtzTriggersVO);
		createJSonData(AjaxUtils.getJsonData(qrtzTriggersVO));
		return AJAX;
	}
	
	/**
	 * 查询任务配置
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryTaskBean(){
		// (1) new 查询条件实例
		PageQueryDTO dto = getQueryDTO();
		// (2) 执行查询
		DataGrid dg = qrtzTriggerConfigUtils.queryTaskBean(dto);
		createJSonData(AjaxUtils.getJsonData(dg));
		return AJAX;
	}
	
	/**
	 * 查看任务配置
	 * @return
	 */
	public String loadTaskBean(){
		QrtzTriggerConfig qrtzTriggerConfig = qrtzTriggerConfigUtils.queryTaskBean(qrtzTriggersVO);
		BeanTools.copyProperties(qrtzTriggerConfig, qrtzTriggersVO);
		createJSonData(AjaxUtils.getJsonData(qrtzTriggersVO));
		return AJAX;
	}
	
	/**
	 * 删除任务配置
	 * @return
	 */
	public String removeTaskBean(){
		qrtzTriggerConfigUtils.removeTaskBean(qrtzTriggersVO);
		createJSonData(AjaxUtils.getJsonData(qrtzTriggersVO));
		return AJAX;
	}
}
