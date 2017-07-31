package com.wondersgroup.cmc.quartz.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;

import com.wondersgroup.cmc.commons.event.BusilogUtils;
import com.wondersgroup.cmc.model.bo.QrtzTriggerConfig;
import com.wondersgroup.cmc.model.bo.QrtzTriggerConfigRelevance;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.quartz.vo.CheckTriggerVO;
import com.wondersgroup.cmc.quartz.vo.QrtzTriggersVO;
import com.wondersgroup.cmc.utils.NumberUtils;
import com.wondersgroup.cmc.utils.UserContextUtils;
import com.wondersgroup.cmc.utils.pagequery.BaseQueryVSImpl;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.cmc.utils.pagequery.JdbcDaoLogUtils;
import com.wondersgroup.cmc.utils.pagequery.JdbcDaoUtils;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;
import com.wondersgroup.wssip.util.BeanTools;
import com.wondersgroup.wssip.util.StringTools;

public class QrtzTriggerConfigServiceImpl extends BaseQueryVSImpl implements QrtzTriggerConfigUtils{
	private static Log log = LogFactory.getLog(QrtzTriggerConfigServiceImpl.class);
	private SchedulerUtils schedulerUtils;

	public void setSchedulerUtils(SchedulerUtils schedulerUtils) {
		this.schedulerUtils = schedulerUtils;
	}
	
	/**
	 * 查询所有后台配置任务
	 * @return
	 */
	public List<QrtzTriggerConfig> findAllTask() {
		String sql = "";
		sql = " FROM QrtzTriggerConfig t where t.validity = '1' order by createTime desc ";
		return CommonHibernateDaoUtils.find(sql);
	}

	/**
	 * 检测是否存在同类型的并且状态为WAITING或ACQUIRED
	 * @param triggerConfigId
	 * @return
	*/
	public List<CheckTriggerVO> checkTriggerexits(String triggerConfigId) {
		if(StringTools.isEmpty(triggerConfigId)){
			throw new BusinessException("传入参数 triggerConfigId 为空");
		}
		String sql = "";
		sql = " select b.trigger_name triggerName from qrtz_triggers a,qrtz_trigger_config_relevance b " +
			  " where a.trigger_state in ('WAITING','ACQUIRED') and a.trigger_name = b.trigger_name and b.trigger_config_id = ? ";
		return CommonJdbcDaoUtils.query(sql, CheckTriggerVO.class,triggerConfigId);
	}
	
	/**
	 * 保存触发器
	 * @param qrtzTriggersVO
	 */
	public void saveTirgger(QrtzTriggersVO qrtzTriggersVO) {
		//校验数据完整性
		if(qrtzTriggersVO == null || StringTools.isEmpty(qrtzTriggersVO.getBeanDesc())){
			throw new BusinessException("传入参数 qrtzTriggersVO 为空");
		}
		
		if(StringTools.hasText(qrtzTriggersVO.getAction())){
			if(StringTools.isEmpty(qrtzTriggersVO.getTriggerName())){
				throw new BusinessException("传入参数 qrtzTriggersVO 为空");
			}
		}
		//创建业务日志
		BusilogUtils.createBusinessLog();
		// 如果是暂停，则先暂停原有调度
		if ("pause".equals(qrtzTriggersVO.getAction())){
			try {
				//判断是否有正在等待执行或正在执行的调度
				List<CheckTriggerVO> checkTriggerVOs = this.checkTriggerexits(qrtzTriggersVO.getBeanDesc());
				if (checkTriggerVOs != null && checkTriggerVOs.size() > 0) {
					if(checkTriggerVOs.get(0).getTriggerName().equals(qrtzTriggersVO.getTriggerName())){
						schedulerUtils.pauseTrigger(qrtzTriggersVO.getTriggerName(), "DEFAULT");
					} else {
						schedulerUtils.pauseTrigger(checkTriggerVOs.get(0).getTriggerName(), "DEFAULT");
					}
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
				throw new BusinessException("暂停调度错误："+e.getMessage());
			}
		} else {
			//否则判断是否有正在等待执行或正在执行的调度
			List<CheckTriggerVO> checkTriggerVOs = this.checkTriggerexits(qrtzTriggersVO.getBeanDesc());
			if (checkTriggerVOs != null && checkTriggerVOs.size() > 0) {
				throw new BusinessException("已存在相同类型的调度（等待执行或执行中）");
			}
		}
		//生成调度名称
		String triggerName = UUID.randomUUID().toString();
		qrtzTriggersVO.setTriggerName(triggerName);
		//保存调度配置明细表
		QrtzTriggerConfigRelevance qrtzTriggerConfigRelevance = new QrtzTriggerConfigRelevance();
		qrtzTriggerConfigRelevance.setTriggerConfigId(Long.parseLong(qrtzTriggersVO.getBeanDesc()));//配置流水号
		qrtzTriggerConfigRelevance.setTriggerName(triggerName);	//名称
		qrtzTriggerConfigRelevance.setValidity("1");			//是否有效
		qrtzTriggerConfigRelevance.setCreater(UserContextUtils.getOperatorId());	//创建人
		qrtzTriggerConfigRelevance.setCreateTime(new Date());	//创建时间
		qrtzTriggerConfigRelevance.setExt1(qrtzTriggersVO.getExt1());	//描述
		if ("1".equals(qrtzTriggersVO.getType())) {
			qrtzTriggerConfigRelevance.setTriggerType("SIMPLE");
			qrtzTriggerConfigRelevance.setStartTime(qrtzTriggersVO.getStartTime_date());
			qrtzTriggerConfigRelevance.setEndTime(qrtzTriggersVO.getEndTime_date());
			qrtzTriggerConfigRelevance.setExeucount(qrtzTriggersVO.getExeuCount());
			qrtzTriggerConfigRelevance.setExeuspace(qrtzTriggersVO.getExeuSpace());
		} else {
			qrtzTriggerConfigRelevance.setTriggerType("CRON");
			qrtzTriggerConfigRelevance.setCronExpr(qrtzTriggersVO.getCronExp());

		}
		CommonHibernateDaoUtils.save(qrtzTriggerConfigRelevance);
		//触发器调度
		switch (qrtzTriggersVO.getType().charAt(0)) {
			case '1':
				try {
					this.saveSimpleTrigger(qrtzTriggersVO);
				} catch (SchedulerException e) {
					e.printStackTrace();
					throw new BusinessException("保存临时调度失败："+e.getMessage());
				}
				break;
			case '2':
				try {
					this.saveCronTrigger(qrtzTriggersVO);
				} catch (ParseException e) {
					e.printStackTrace();
					throw new BusinessException("保存周期性调度失败："+e.getMessage());
				} catch (SchedulerException e) {
					e.printStackTrace();
					throw new BusinessException("保存周期性调度失败："+e.getMessage());
				}
				break;
			default:
				break;
		}
	}
	/**
	 * 保存simple类型的触发器
	 * @throws SchedulerException 
	 * @throws NumberFormatException 
	 */
	private void saveSimpleTrigger(QrtzTriggersVO qrtzTriggersVO) throws SchedulerException {
		if(qrtzTriggersVO == null){
			throw new BusinessException("传入参数  qrtzTriggersVO 为空");
		}
		if (NumberUtils.isEmpty(qrtzTriggersVO.getExeuCount())) {
			qrtzTriggersVO.setExeuCount(0l);
		}
		int exeuCont = qrtzTriggersVO.getExeuCount().intValue();
		Long exeuSpace = qrtzTriggersVO.getExeuSpace();
		
		if (NumberUtils.isEmpty(exeuSpace)) {
			if(exeuCont == 0l){
				if(qrtzTriggersVO.getEndTime_date() == null){
					schedulerUtils.schedule(qrtzTriggersVO.getTriggerName(), qrtzTriggersVO.getStartTime_date());
				} else {
					schedulerUtils.schedule(qrtzTriggersVO.getTriggerName(), qrtzTriggersVO.getStartTime_date(), qrtzTriggersVO.getEndTime_date());
				}
			} else {
				schedulerUtils.schedule(qrtzTriggersVO.getTriggerName(), qrtzTriggersVO.getStartTime_date(), qrtzTriggersVO.getEndTime_date(), exeuCont);
			}
		} else {
			schedulerUtils.schedule(qrtzTriggersVO.getTriggerName(), qrtzTriggersVO.getStartTime_date(), qrtzTriggersVO.getEndTime_date(), exeuCont, exeuSpace);
		}
	}
	
	/**
	 * 保存Cron类型的触发器
	 * @throws SchedulerException 
	 * @throws ParseException 
	*/
	private void saveCronTrigger(QrtzTriggersVO qrtzTriggersVO) throws ParseException, SchedulerException {
		schedulerUtils.schedule(qrtzTriggersVO.getTriggerName(), qrtzTriggersVO.getCronExp());
	}

	/**
	 * 查询所有触发器
	 * @param dto
	 * @return
	*/
	@SuppressWarnings("rawtypes")
	public DataGrid findAllQuartzTrigger(PageQueryDTO dto) {
	    JdbcDaoLogUtils.doLog(log, "[findAllQuartzTigger]QueryDTO = [" + dto.toString() + "]");

	    String fromSQL = new String(" select a.trigger_name triggerName,a.next_fire_time nextFireTime,b.ext1,d.displayname createrName,  a.trigger_type triggerType,a.trigger_state triggerState, c.bean_desc beandesc, c.id beanId, b.cron_expr cronExp,b.create_time createTime  from qrtz_triggers a left join qrtz_trigger_config_relevance b on a.trigger_name = b.trigger_name left join qrtz_trigger_config c on b.trigger_config_id = c.id left join uauserinfo d on c.creater = d.userid");

	    String orderBy = " a.next_fire_time ";

	    StringBuffer condition = new StringBuffer("");
	    List args = new ArrayList();

	    String where = formatWhere(condition, args);
	    String orderby = formatOrderBy(orderBy, dto);

	    String sql = fromSQL + where + orderby;
	    Object[] params = args.toArray();
	    DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), QrtzTriggersVO.class);
	    return dataGrid;
	}

	/**
	 * 更新触发器
	 * @param qrtzTriggersVO
	 */
	public void changeStatues(QrtzTriggersVO qrtzTriggersVO) {
		//校验数据完整性
		if(qrtzTriggersVO == null || NumberUtils.isEmpty(qrtzTriggersVO.getBeanId()) || 
				StringTools.isEmpty(qrtzTriggersVO.getTriggerName()) || StringTools.isEmpty(qrtzTriggersVO.getTriggerState())){
			throw new BusinessException("传入参数  qrtzTriggersVO 为空");
		}
		
		if(StringTools.hasText(qrtzTriggersVO.getAction())){
			if(StringTools.isEmpty(qrtzTriggersVO.getExt2())){
				throw new BusinessException("传入参数  qrtzTriggersVO 为空");
			}
		}
		Long beanId = qrtzTriggersVO.getBeanId();				//调度配置流水号
		String triggerName = qrtzTriggersVO.getTriggerName();	//名称
		String status = qrtzTriggersVO.getTriggerState();		//状态
		String action = qrtzTriggersVO.getAction();				//操作
		String oldName = qrtzTriggersVO.getExt2();				//名称
		
		//创建业务日志
		BusilogUtils.createBusinessLog();
		//确定beanId 与 triggerName一致
		String sql = "";
		sql = " select * from qrtz_trigger_config_relevance t where t.TRIGGER_CONFIG_ID = ? and t.TRIGGER_NAME = ? ";
		QrtzTriggerConfigRelevance qrtzTriggerConfigRelevance = CommonJdbcDaoUtils.get(sql, QrtzTriggerConfigRelevance.class, beanId,triggerName);

		if(qrtzTriggerConfigRelevance == null){
			throw new BusinessException("未查询到调度信息");
		}
		
		if ("1".equals(status)){
			//恢复时 首先判断是否有相同类型调度存在
			if("resume".equals(action)) {
				try {
					//判断是否有正在等待执行或正在执行的调度
					List<CheckTriggerVO> checkTriggerVOs = this.checkTriggerexits(beanId.toString());
					if (checkTriggerVOs != null && checkTriggerVOs.size() > 0) {
						if(checkTriggerVOs.get(0).getTriggerName().equals(oldName)){
							schedulerUtils.pauseTrigger(oldName, "DEFAULT");
						} else {
							schedulerUtils.pauseTrigger(checkTriggerVOs.get(0).getTriggerName(), "DEFAULT");
						}
					}
				} catch (SchedulerException e) {
					e.printStackTrace();
					throw new BusinessException("暂停同类调度错误："+e.getMessage());
				}
			} else {
				//否则判断是否有正在等待执行或正在执行的调度
				List<CheckTriggerVO> checkTriggerVOs = this.checkTriggerexits(beanId.toString());
				if (checkTriggerVOs != null && checkTriggerVOs.size() > 0) {
					throw new BusinessException("已存在相同类型的调度（等待执行或执行中）");
				}
			}
			//恢复调度
			try {
				schedulerUtils.resumeTrigger(qrtzTriggersVO.getTriggerName(), "DEFAULT");
			} catch (SchedulerException e) {
				e.printStackTrace();
				throw new BusinessException("恢复调度错误："+e.getMessage());
			}
		} else if ("0".equals(status)) {
			try {
				schedulerUtils.pauseTrigger(qrtzTriggersVO.getTriggerName(), "DEFAULT");
			} catch (SchedulerException e) {
				e.printStackTrace();
				throw new BusinessException("暂停调度错误："+e.getMessage());
			}
		}
	}

	/**
	 * 移除触发器
	 * @return
	 */
	public void removeTrigger(QrtzTriggersVO qrtzTriggersVO) {
		//校验数据完整性
		if(qrtzTriggersVO == null || StringTools.isEmpty(qrtzTriggersVO.getTriggerName())){
			throw new BusinessException("传入参数 qrtzTriggersVO 为空");
		}
		//创建业务日志
		BusilogUtils.createBusinessLog();
		try {
			schedulerUtils.removeTrigdger(qrtzTriggersVO.getTriggerName(), "DEFAULT");
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new BusinessException("删除调度错误："+e.getMessage());
		}
	}

	/**
	 * 根据triggerName查询QrtzTriggerConfig数据
	 * @param triggerName 调度名称
	 * @return
	*/
	public QrtzTriggerConfig findBeanByTriggerName(String triggerName) {
		String hql = " select t from QrtzTriggerConfig t,QrtzTriggerConfigRelevance q where t.id=q.triggerConfigId and q.triggerName=? ";
	    List<QrtzTriggerConfig> list = CommonHibernateDaoUtils.find(hql, new Object[]{triggerName});
	    return list != null && !list.isEmpty()?(QrtzTriggerConfig)list.get(0):null;
	}
	
	/**
	 * 根据triggerName查询QrtzTriggerConfigRelevance数据
	 * @param triggerName
	 * @return
	 */
	public QrtzTriggerConfigRelevance findTriggerByTriggerName(String triggerName) {
		String hql = "select t from QrtzTriggerConfigRelevance t where t.triggerName=? ";
	    List<QrtzTriggerConfigRelevance> list = CommonHibernateDaoUtils.find(hql, new Object[]{triggerName});
	    return list != null && !list.isEmpty()?(QrtzTriggerConfigRelevance)list.get(0):null;
	}
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
	public void dispatcher(Object object, Map<String, Object> filter) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = object.getClass().getMethod("executeMethod", Map.class);
        method.invoke(object, filter);
	}

	/**
	 * 保存任务配置
	 * @param qrtzTriggersVO
	 */
	public void saveTaskBean(QrtzTriggersVO qrtzTriggersVO) {
		//校验数据完整性
		if(qrtzTriggersVO == null || StringTools.isEmpty(qrtzTriggersVO.getBeanName()) || StringTools.isEmpty(qrtzTriggersVO.getBeanDesc())){
			throw new BusinessException("传入参数 qrtzTriggersVO 为空");
		}
		//创建业务日志
		BusilogUtils.createBusinessLog();
		//保存任务配置表
		QrtzTriggerConfig qrtzTriggerConfig = new QrtzTriggerConfig();
		BeanTools.copyProperties(qrtzTriggersVO, qrtzTriggerConfig);
		qrtzTriggerConfig.setValidity("1");			//是否有效
		qrtzTriggerConfig.setCreateTime(new Date());//创建时间
		qrtzTriggerConfig.setCreater(UserContextUtils.getOperatorId());	//创建人
		if(NumberUtils.isNotEmpty(qrtzTriggersVO.getBeanId())){
			qrtzTriggerConfig.setId(qrtzTriggersVO.getBeanId());
			CommonHibernateDaoUtils.update(qrtzTriggerConfig);
		} else {
			CommonHibernateDaoUtils.save(qrtzTriggerConfig);
			qrtzTriggersVO.setBeanId(qrtzTriggerConfig.getId());
		}
	}


	@SuppressWarnings("rawtypes")
	public DataGrid queryTaskBean(PageQueryDTO dto) {
	    JdbcDaoLogUtils.doLog(log, "[queryTaskBean]QueryDTO = [" + dto.toString() + "]");

	    String fromSQL = new String(" select a.id beanId,a.bean_desc beanDesc,a.bean_name beanName,a.create_time createTime,b.displayname createrName,a.notes,a.ext1,a.ext2  from qrtz_trigger_config a left join uauserinfo b on a.creater = b.userid ");

	    String orderBy = " a.create_time desc ";

	    StringBuffer condition = new StringBuffer("");
	    List args = new ArrayList();

	    condition.append(" AND a.validity = '1' ");

	    String where = formatWhere(condition, args);
	    String orderby = formatOrderBy(orderBy, dto);

	    String sql = fromSQL + where + orderby;
	    Object[] params = args.toArray();
	    DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), QrtzTriggersVO.class);
	    return dataGrid;
	}

	/**
	 * 查看任务配置
	 * @return
	 */
	public QrtzTriggerConfig queryTaskBean(QrtzTriggersVO qrtzTriggersVO) {
		if(qrtzTriggersVO == null || NumberUtils.isEmpty(qrtzTriggersVO.getBeanId())){
			throw new BusinessException("传入参数 qrtzTriggersVO 为空");
		}
		return CommonHibernateDaoUtils.load(QrtzTriggerConfig.class,qrtzTriggersVO.getBeanId());
	}

	/**
	 * 删除任务配置
	 * @return
	 */
	public void removeTaskBean(QrtzTriggersVO qrtzTriggersVO) {
		QrtzTriggerConfig qrtzTriggerConfig = this.queryTaskBean(qrtzTriggersVO);
		//校验能否删除
		String sql = " select count(1) exeucount from qrtz_triggers a,qrtz_trigger_config_relevance b,qrtz_trigger_config c " +
					 " where a.trigger_name = b.trigger_name AND b.trigger_config_id = c.id and c.id = ? ";
		QrtzTriggersVO cnts = CommonJdbcDaoUtils.get(sql, QrtzTriggersVO.class,qrtzTriggersVO.getBeanId());
		if(cnts.getExeuCount()>0){
			throw new BusinessException("该任务已配置调度，请先删除调用后再删除该任务。");
		}
		//创建业务日志
		BusilogUtils.createBusinessLog();
		if (qrtzTriggerConfig != null) {
			qrtzTriggerConfig.setValidity("0");	//是否有效
			CommonHibernateDaoUtils.update(qrtzTriggerConfig);
		}
	}
}
