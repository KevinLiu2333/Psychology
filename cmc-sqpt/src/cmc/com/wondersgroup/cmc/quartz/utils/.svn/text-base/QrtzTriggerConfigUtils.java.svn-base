package com.wondersgroup.cmc.quartz.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.wondersgroup.cmc.model.bo.QrtzTriggerConfig;
import com.wondersgroup.cmc.model.bo.QrtzTriggerConfigRelevance;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.quartz.vo.CheckTriggerVO;
import com.wondersgroup.cmc.quartz.vo.QrtzTriggersVO;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;


public interface QrtzTriggerConfigUtils {

	/**
	 * 查询所有后台配置任务
	 * @return
	 */
	public List<QrtzTriggerConfig> findAllTask();

	/**
	 * 检测是否存在同类型的并且状态为WAITING或ACQUIRED
	 * @param triggerConfigId
	 * @return
	*/
	public List<CheckTriggerVO> checkTriggerexits(String triggerConfigId);

	/**
	 * 保存触发器
	 * @param qrtzTriggersVO
	 */
	public void saveTirgger(QrtzTriggersVO qrtzTriggersVO);

	/**
	 * 查询所有触发器
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid findAllQuartzTrigger(PageQueryDTO dto);

	/**
	 * 更新触发器
	 * @param qrtzTriggersVO
	 */
	public void changeStatues(QrtzTriggersVO qrtzTriggersVO);

	/**
	 * 移除触发器
	 * @return
	 */
	public void removeTrigger(QrtzTriggersVO qrtzTriggersVO);
	
	/**
	 * 根据triggerName查询QrtzTriggerConfig数据
	 * @param triggerName 调度名称
	 * @return
	*/
	public QrtzTriggerConfig findBeanByTriggerName(String triggerName);
	
	/**
	 * 根据triggerName查询QrtzTriggerConfigRelevance数据
	 * @param triggerName
	 * @return
	 */
	public QrtzTriggerConfigRelevance findTriggerByTriggerName(String triggerName);
	
	/**
	 * 分发
	 * @param object
	 * @param filter
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public void dispatcher(Object object,Map<String, Object> filter) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;

	/**
	 * 保存任务配置
	 * @param qrtzTriggersVO
	 */
	public void saveTaskBean(QrtzTriggersVO qrtzTriggersVO);

	/**
	 * 查询任务配置
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryTaskBean(PageQueryDTO dto);
	
	/**
	 * 查看任务配置
	 * @return
	 */
	public QrtzTriggerConfig queryTaskBean(QrtzTriggersVO qrtzTriggersVO);
	
	/**
	 * 删除任务配置
	 * @return
	 */
	public void removeTaskBean(QrtzTriggersVO qrtzTriggersVO);
}
