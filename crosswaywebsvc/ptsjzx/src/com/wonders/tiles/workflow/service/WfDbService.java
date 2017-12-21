package com.wonders.tiles.workflow.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.service.Service;

import com.wonders.tiles.workflow.WfConstants;
import com.wonders.tiles.workflow.entity.WfDefine;
import com.wonders.tiles.workflow.entity.WfHisInstance;
import com.wonders.tiles.workflow.entity.WfInstance;
import com.wonders.tiles.workflow.entity.WfNode;
import com.wonders.tiles.workflow.entity.WfOpnn;
import com.wonders.tiles.workflow.entity.WfRoute;
import com.wonders.tiles.workflow.entity.WfUser;
import com.wonders.tiles.workflow.exception.WfException;
import com.wonders.tiles.workflow.renderer.DefaultRender;

/**
 * 处理工作流与数据库交互.
 * 
 */
@IocBean(fields = "dao")
public class WfDbService extends Service{
    /** wfService实例 */
	@Inject("refer:defaultRender")
    DefaultRender defaultRender = null;
	
	public WfUser getUserInfoById(String userId) {
		return defaultRender.getUserInfoById(userId);
	}
	/**
	 * 根据角色获取用户列表信息.
	 * @param userId
	 * @return
	 */
	public List<WfUser> genUserListByRoleId(String roleId){
		return defaultRender.genUserListByRoleId(roleId);
	}

	/**
	 * 根据角色获取用户idd字符串，用逗号号隔开.
	 * @param userId
	 * @return
	 */
	public String genUserIdsByRoleId(String roleId){
		return defaultRender.genUserIdsByRoleId(roleId);
	}

	/**
	 * 根据角色获取用户idd字符串，用逗号号隔开.
	 * @param userId
	 * @return
	 */
	public String genUserIdsByDeptCode(String deptCode){
		return defaultRender.genUserIdsByDeptCode(deptCode);
	}
	
	/**
	 * 根据部门过滤节点中的 人.
	 * @param roleId
	 * @param deptCode
	 * @return
	 */
	public String genUserIdsByFilter(String roleId ,String deptCode){
		return defaultRender.genUserIdsByFilter(roleId, deptCode);
	}
	
	/**
	 * 根据流程代码查询流程代码对象
	 * @param  wfCode  流程代码
	 * @return WfDefine 流程定义对象
	 * @throws WfException 流程异常
	 */
	public WfDefine getWfDefineByCode(String wfCode) throws WfException {
		WfDefine wfDefine = dao().fetch(WfDefine.class, Cnd.where("wfCode", "=",wfCode));
		if (wfDefine != null) {
			return wfDefine;
		} else {
			throw new WfException(WfConstants.ERROR_WF_CODE);
		}
	}
	
	/**
	 * 根据流程代码查询所有流程节点.
	 * @param wfCode 流程代码
	 * @return List<WfNode>
	 * @throws WfException 流程异常
	 */
	public List<WfNode> findWfNodeListByCode(String wfCode) throws WfException {
		List<WfNode> wfCodeList = dao().query(WfNode.class, Cnd.where("wfCode", "=",wfCode));
		if (wfCodeList != null && wfCodeList.size() > 0) {
			return wfCodeList;
		} else {
			throw new WfException(WfConstants.ERROR_WF_NODE);
		}
	}

	
	/**
	 * 根据流程代码查询所有流程节点.
	 * @param wfCode 流程代码
	 * @return List<WfNode>
	 * @throws WfException 流程异常
	 */
	public WfNode findWfNodeListById(String nodeId) throws WfException {
		return dao().fetch(WfNode.class, Cnd.where("nodeId", "=",nodeId));
	}

	/**
	 * 根据流程代码查询所有流程节点.
	 * @param wfCode 流程代码
	 * @return List<WfNode>
	 * @throws WfException 流程异常
	 */
	public WfNode getWfNodeByNodeId(String nodeId) throws WfException {
		List<WfNode> wfCodeList = dao().query(WfNode.class, Cnd.where("nodeId", "=",nodeId));
		if (wfCodeList != null && wfCodeList.size() > 0) {
			return wfCodeList.get(0);
		} else {
			throw new WfException(WfConstants.ERROR_WF_NODE);
		}
	}
	
	/**
	 * 根据ID查询流程实例对象.
	 * @param instanceId 流程实例ID
	 * @return WfInstance 流程实例对象
	 * @throws WfException 流程异常
	 */
	public WfInstance findWfInstanceById(String instanceId) throws WfException {
		WfInstance wfInstance = dao().fetch(WfInstance.class, instanceId);
		if (wfInstance != null) {
			return wfInstance;
		} else {
			throw new WfException(WfConstants.ERROR_NO_INSTANCE);
		}
	}

	/**
	 * 根据ID查询流程实例对象.
	 * @param instanceId 流程实例ID
	 * @return WfInstance 流程实例对象
	 * @throws WfException 流程异常
	 */
	public WfHisInstance findWfHisInstanceById(String instanceId) throws WfException {
		WfHisInstance wfInstance = dao().fetch(WfHisInstance.class, instanceId);
		if (wfInstance != null) {
			return wfInstance;
		} else {
			throw new WfException(WfConstants.ERROR_NO_INSTANCE);
		}
	}
	
	/**
	 * 根据节点主键查询流程实例（等待和待办状态）对象，如果为完成状态，则抛出异常.
	 * @param nodeId 节点ID
	 * @return WfInstance 流程实例对象
	 * @throws WfException 流程异常
	 */
	public WfInstance getWfInstanceByNodeId(String nodeId,String instanceNo,String sendoutDept) throws WfException {
		SqlExpressionGroup se = Cnd.exps("curNodeId", "=",nodeId).and("instanceNo", "=", instanceNo);
		List<WfInstance> wfInstanceList = dao().query(WfInstance.class,Cnd.where(se));
		WfInstance wfInstance = null;
		if (wfInstanceList == null || wfInstanceList.size() ==0){
			return wfInstance;
		}
		//针对于会签延续的判断
		if (Strings.isBlank(sendoutDept)) {
			wfInstance =  wfInstanceList.get(0);
		}else {
			for (int i = 0; i < wfInstanceList.size(); i++) {
				if(wfInstanceList.get(i).equals(sendoutDept)){
					wfInstance = wfInstanceList.get(i);
					break;
				}
			}
		}
		return wfInstance;
	}
	
	/**
	 * 根据节点主键查询流程实例（已完成状态）对象，则抛出异常.
	 * @param nodeId 节点ID
	 * @return WfInstance 流程实例对象
	 * @throws WfException 流程异常
	 */
	public WfHisInstance getFinWfInstanceByNodeId(String nodeId,String instanceNo) throws WfException {
		WfHisInstance wfInstance = dao().fetch(WfHisInstance.class, Cnd.where("curNodeId", "=",nodeId).and("instanceNo", "=", instanceNo)
				.and("status", "=", WfConstants.STATUS_FINISH).desc("finishDate"));
		return wfInstance;
	}
	
	/**
	 * 根据节点主键查询节点的路由表，主要查看当前节点的前置节点.
	 * @param nodeId 节点ID
	 * @return List<WfRoute>
	 * @throws WfException 流程异常
	 */
	public List<WfRoute> findWfRouteListByNodeId(String nodeId) throws WfException {
		List<WfRoute> wfRouteList = dao().query(WfRoute.class, Cnd.where("nodeId", "=",nodeId));
		if (wfRouteList != null && wfRouteList.size() > 0) {
			return wfRouteList;
		} else {
			throw new WfException(WfConstants.ERROR_ROUTE_PATH);
		}
	}
	
	/**
	 * 根据流程代码主键查询节点的路由表，主要查看当前节点的前置节点和后置节点.
	 * @param code 节点ID
	 * @return List<WfRoute>
	 * @throws WfException 流程异常
	 */
	public List<WfRoute> findWfRouteListByCode(String code) throws WfException {
		List<WfRoute> wfRouteList = dao().query(WfRoute.class, Cnd.where("wfCode", "=",code));
		if (wfRouteList != null && wfRouteList.size() > 0) {
			return wfRouteList;
		} else {
			throw new WfException(WfConstants.ERROR_ROUTE_PATH);
		}
	}
	
	/**
	 * 根据节点主键查询是此节点向下一节点是否存在缺省路径
	 * @param nodeId 节点ID
	 * @return true存在缺省路径；false不在缺省路径
	 * @throws WfException 流程异常
	 */
	public boolean isDefaultRouteByNodeId(String nodeId) throws WfException {
		List<WfRoute> wfRouteList = dao().query(WfRoute.class, Cnd.where("nodeId", "=",nodeId).and("isCon", "!=", "1"));
		if (wfRouteList != null && wfRouteList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据业务主键查询操作记录对象列表
	 * @param busId 业务主键
	 * @return List<WfOpnn>
	 * @throws WfException 流程异常
	 */
	public List<WfOpnn> findWfOpnnByBusId(String busId) throws WfException {
		List<WfOpnn> wfOpnnList = dao().query(WfOpnn.class, Cnd.where("busId", "=",busId).and("nodeName", "!=", "开始").and("nodeName", "!=", "结束").asc("opnnTime"));
		return wfOpnnList;
	}

	/**
	 * 根据业务主键和流程No查询操作记录对象列表
	 * @param busId 业务主键
	 * @param instanceNo 流程No.
	 * @return List<WfOpnn>
	 * @throws WfException 流程异常
	 */
	public List<WfOpnn> findWfOpnn(String busId, String instanceNo) throws WfException {
		List<WfOpnn> wfOpnnList = dao().query(WfOpnn.class, Cnd.where("busId", "=",busId).and("instanceNo", "=", instanceNo).and("nodeName", "!=", "开始").and("nodeName", "!=", "结束").asc("opnnTime"));
		return wfOpnnList;
	}
	
	/**
	 * 获取待办列表,用户ID.
	 * @param userId
	 * @return
	 */
	public List<WfInstance> findWorkItemList(String userId){
		SqlExpressionGroup e2 = Cnd.exps("status", "=", WfConstants.STATUS_SHOW).and("validity", "=",WfConstants.VALUE_YESNO_YES).and("tudoUserId", "like","%"+userId+"%");
		List<WfInstance> wfInstanceList = dao().query(WfInstance.class,Cnd.where(e2).asc("recevieDate"));
	    return wfInstanceList;
	}

	/**
	 * 获取指定instanceNo已完成所有列表.
	 * @param instanceNo
	 * @return
	 */
	public String findAllWfHisInstanceByNo(String instanceNo){
		List<WfHisInstance> wfInstanceList = dao().query(WfHisInstance.class, Cnd.where("instanceNo", "=",instanceNo).and("curNodeName", "!=", "开始"));
		String result = null;
		if(wfInstanceList != null){
			for(WfHisInstance wfHisInstance : wfInstanceList){
				if(result == null){
					result = wfHisInstance.getFinishUserId();
				}else if(result.indexOf(wfHisInstance.getFinishUserId()) == -1){
					result = result + "," + wfHisInstance.getFinishUserId();
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取可回收列表,用户ID.
	 * @param userId
	 * @return
	 */
	public List<WfHisInstance> findCallBackList(String userId){
		List<WfHisInstance> wfInstanceList = dao().query(WfHisInstance.class, Cnd.where("finishUserId", "=",userId).and("validity", "=",WfConstants.VALUE_YESNO_YES).
				and("isCallback", "=", "1"));
		return wfInstanceList;
	}
	

	/**
	 * 获取已完成列表,用户ID.
	 * @param userId
	 * @return
	 */
	public List<WfHisInstance> findFinishList(String userId){
		List<WfHisInstance> wfInstanceList = dao().query(WfHisInstance.class, Cnd.where("opAllUserIds", "like","%"+userId+"%").and("validity", "=",WfConstants.VALUE_YESNO_YES).and("CUR_NODE_NAME", "=","结束").asc("finishDate")
				);
		return wfInstanceList;
	}


	/**
	 * 获取已办完列表但流程未完成.
	 * @param userId 用户ID
	 * @return
	 */
	public List<WfInstance> findDealList(String userId){
		List<WfInstance> wfInstanceList = dao().query(WfInstance.class, Cnd.where("opAllUserIds", "like","%"+userId+"%").and("validity", "=",WfConstants.VALUE_YESNO_YES).asc("finishDate")
				);
		return wfInstanceList;
	}

	/**
	 * 获取已办完事项数量但流程未完成.
	 * @param userId 用户ID
	 * @return
	 */
	public int getDealCount(String userId){
		SqlExpressionGroup se = Cnd.exps("opAllUserIds", "like","%"+userId+"%").and("validity", "=",WfConstants.VALUE_YESNO_YES);
		return dao().count(WfInstance.class,Cnd.where(se));
				
	}

	
	/**
	 * 获取待办列表数量.
	 * @param userId 用户ID
	 * @return
	 */
	public int getWorkItemCount(String wfCode,String userId,String curNodeId){
		SqlExpressionGroup se = Cnd.exps("status", "=", WfConstants.STATUS_SHOW).and("wfCode", "=", wfCode).and("validity", "=",WfConstants.VALUE_YESNO_YES).and("tudoUserId", "like","%"+userId+"%");
		if(!Strings.isBlank(curNodeId)){
			se.and("curNodeId", "=", curNodeId);
		}
		return dao().count(WfInstance.class,Cnd.where(se));
	}
	
	/**
	 * 查询未完成会签的节点.
	 * @param userId
	 * @return
	 */
	public List<WfInstance> findCurSendOutList(String nodeId,String instanceNo){
		List<WfInstance> wfInstanceList = dao().query(WfInstance.class, Cnd.where("sendoutFlag", "=",nodeId).and("instanceNo", "=",instanceNo));
		return wfInstanceList;
	}
	
	/**
	 * 查询当前节点的list.
	 * @param userId
	 * @return
	 */
	public List<WfInstance> findCurNodeListByBusId(String busId){
		List<WfInstance> wfInstanceList = dao().query(WfInstance.class, Cnd.where("busId", "=",busId).and("validity", "=",WfConstants.VALUE_YESNO_YES));
		return wfInstanceList;
	}

	/**
	 * 查询已完成会签的节点.
	 * @param userId
	 * @return
	 */
	public List<WfHisInstance> findHisSendOutList(String nodeId,String instanceNo){
		List<WfHisInstance> wfInstanceList = dao().query(WfHisInstance.class, Cnd.where("sendoutFlag", "=",nodeId).and("instanceNo", "=",instanceNo));
		return wfInstanceList;
	}
	
	/**
	 * 物理删除工作流
	 * @param busId 业务主键
	 */
	public void truncateWf(String busId,String instanceNo) {
		//删除待办
		List<WfInstance> instanceList = dao().query(WfInstance.class, Cnd.where("busId", "=",busId ).and("instanceNo", "=", instanceNo));
		for(WfInstance wfInstance : instanceList){
			dao().delete(wfInstance);
		}
		//删除已办
		List<WfHisInstance> wfHisInstanceList = dao().query(WfHisInstance.class, Cnd.where("busId", "=",busId ).and("instanceNo", "=", instanceNo));
		for(WfHisInstance wfHisInstance : wfHisInstanceList){
			dao().delete(wfHisInstance);
		}
	}

	/**
	 * 查询最早的计时类型的时间，如果不存在，返回当前时间 .
	 * @param busId 业务主键
	 */
	public Date getEarlyDurationTypeDate(String durationType,String instanceNo) {
		Date resultDate = new Date();
		//先查询历史
		WfHisInstance wfHisInstance = dao().fetch(WfHisInstance.class, Cnd.where("durationType", "=",durationType ).and("instanceNo", "=", instanceNo).asc("durationDate"));
		if(wfHisInstance == null){
			//不存在的话查询待办
			WfInstance wfInstance = dao().fetch(WfInstance.class, Cnd.where("durationType", "=",durationType ).and("instanceNo", "=", instanceNo).asc("durationDate"));
			//如果存在待办，去待办的日期
			if(wfInstance != null){
				resultDate = wfInstance.getDurationDate();
			}
		}else{
			resultDate = wfHisInstance.getDurationDate();
		}
		
		return resultDate;
	}

	private int dealCalculate(Date startDate, Date endDate,Map<String,Date[]> stopMap){
		int result = 0;
		//计算为暂停的总时间
		if(startDate != null){
			result = WorkDayService.getInstance().queryDateCount(startDate, endDate);
		}
		//剔除暂停时间
		if(stopMap != null){
			for(String instanceId : stopMap.keySet()){
				Date[] dates = stopMap.get(instanceId);
				int stopTime = WorkDayService.getInstance().queryDateCount(dates[0], dates[1]);
				result = result - stopTime;
			}
		}
		return result;
	}
	
	private Map<String,Date[]> dealStopMap(List<WfOpnn> wfOpnnList){
		Map<String,Date[]> stopMap = null;
		if(wfOpnnList != null){
			for(WfOpnn wfOpnn : wfOpnnList){
			   if(WfConstants.OP_TYPE_SUSPEND.equals(wfOpnn.getOpnnType())){
					if(stopMap == null){
						stopMap = new HashMap<String,Date[]>();
						stopMap.put(wfOpnn.getInstanceId(),new Date[]{wfOpnn.getOpnnTime(),new Date()});
					}
				}else if(WfConstants.OP_TYPE_RESUME.equals(wfOpnn.getOpnnType())){
					if(stopMap != null){
						Date[] dates = stopMap.get(wfOpnn.getInstanceId());
						if(dates != null && dates.length > 1){
							dates[1] = wfOpnn.getOpnnTime();
						}
					}
				}
			}
		}
		return stopMap;
	}

	/**
	 * 计算总用时.
	 * @param busId 业务主键
	 */
	public int getWfDuration(String instanceNo,Date durationDate) {
		//开始时间
		Date startDate = null;
		//结束时间
		Date endDate = durationDate;
		//组织数据
		List<WfOpnn> wfOpnnList = dao().query(WfOpnn.class, Cnd.where("instanceNo", "=",instanceNo).and("opnnTime", "<=",durationDate).asc("opnnTime"));
		if(wfOpnnList != null){
			for(WfOpnn wfOpnn : wfOpnnList){
				if("开始".equals(wfOpnn.getNodeName())){
					startDate = wfOpnn.getOpnnTime();
				}else if("结束".equals(wfOpnn.getNodeName())){
					endDate = wfOpnn.getOpnnTime();
				}
			}
		}
		//暂停map
		Map<String,Date[]> stopMap = this.dealStopMap(wfOpnnList);
		//返回总计时
		return this.dealCalculate(startDate, endDate, stopMap);
	}
	
	/**
	 * 计算总用时，用于已办计时，不对外开放.
	 * @param busId 业务主键
	 */
	public int getWfDuration(WfHisInstance wfHisInstance) {
		//开始时间
		Date startDate = wfHisInstance.getDurationDate();
		//结束时间
		Date endDate = wfHisInstance.getFinishDate();
		//组织数据
		List<WfOpnn> wfOpnnList = dao().query(WfOpnn.class, Cnd.where("instanceNo", "=",wfHisInstance.getInstanceNo()).and("nodeId", "=", wfHisInstance.getCurNodeId()).asc("opnnTime"));
		//暂停map
		Map<String,Date[]> stopMap = this.dealStopMap(wfOpnnList);
		//返回总计时
		return this.dealCalculate(startDate, endDate, stopMap);
	}

	/**
	 * 计算总用时,用于待办计时.
	 * @param busId 业务主键
	 */
	public int getWfDuration(String instanceNo,String curNodeId,Date durationDate) {
		//开始时间
		Date startDate = durationDate;
		//结束时间
		Date endDate = new Date();
		//组织数据
		List<WfOpnn> wfOpnnList = dao().query(WfOpnn.class, Cnd.where("instanceNo", "=",instanceNo).and("opnnTime", ">",startDate).asc("opnnTime"));
		//判断最后操作是否是暂停，如果是暂停，计时从开始到暂停时间即可
		if(wfOpnnList !=null && wfOpnnList.size()>0){
			WfOpnn wfOpnn = wfOpnnList.get(0);
			if(WfConstants.OP_TYPE_SUSPEND.equals(wfOpnn.getOpnnType())){
				endDate = wfOpnn.getOpnnTime();
			}
		}
		//暂停map
		Map<String,Date[]> stopMap = this.dealStopMap(wfOpnnList);
		//返回总计时
		return this.dealCalculate(startDate, endDate, stopMap);
	}

	/**
	 * 查询正在办理的工作流列表
	 * @param busId 业务主键
	 */
	public List<WfInstance> findCurInstanceList(String busId,String instanceNo) {
		List<WfInstance> instanceList = dao().query(WfInstance.class, Cnd.where("busId", "=",busId ).and("instanceNo", "=", instanceNo));
		return instanceList;
	}

	/**
	 * 查询正在办理的工作流列表
	 * @param busId 业务主键
	 */
	public WfOpnn findWfOpnnByNodeId(String busId,String instanceNo,String nodeId,String filterUser) {
		SqlExpressionGroup e2 = Cnd.exps("busId", "=",busId ).and("instanceNo", "=", instanceNo).and("nodeId", "=", nodeId);
		if(!Strings.isBlank(filterUser)){
			e2.and("userId", "in", "'"+filterUser.replaceAll(",", "','")+"'");
		}
		WfOpnn wfOpnn = dao().fetch(WfOpnn.class, Cnd.where(e2).desc("opnnTime"));
		return wfOpnn;
	}
	
	
	/**
	 * 逻辑删除工作流
	 * @param instanceId 流程实例ID
	 */
	public void deleteInstance(String instanceId) {
		dao().delete(WfInstance.class, instanceId);
	}

	/**
	 * 设置回收案件标识，将节点主键ID对应的流程实例设置为不可回收
	 * @param nodeIds 节点主键ID（用逗号隔开）
	 */
	public void setCallbackFlag(String instanceNo , String nodeIds) {
		dao().update(WfHisInstance.class, Chain.make("isCallback", "0"), Cnd.where("curNodeId", "in",nodeIds).and("instanceNo", "=", instanceNo));
	}


	/**
	 * 设置已操作人信息 。
	 * @param nodeIds 节点主键ID（用逗号隔开）
	 */
	public void setOpAllUserId(String instanceNo , String userIds) {
		dao().update(WfInstance.class, Chain.make("opAllUserIds", userIds), Cnd.where("instanceNo", "=", instanceNo));
	}

	/**
	 * 保存当前流程实例
	 * 
	 * @param wfInstance 流程实例对象
	 */
	public void saveInstance(WfInstance wfInstance) {
		dao().insert(wfInstance);
	}

	/**
	 * 保存历史流程实例
	 * 
	 * @param wfInstance 流程实例对象
	 */
	public void saveHisInstance(WfHisInstance wfHisInstance) {
		//设置已完成人员的用户主键，多个的话用逗号隔开
		if(Strings.isBlank(wfHisInstance.getOpAllUserIds())){
			wfHisInstance.setOpAllUserIds(wfHisInstance.getFinishUserId());
		}else{
			if(!Strings.isBlank(wfHisInstance.getFinishUserId()) && wfHisInstance.getOpAllUserIds().indexOf(wfHisInstance.getFinishUserId()) == -1){
				wfHisInstance.setOpAllUserIds(wfHisInstance.getOpAllUserIds()+","+wfHisInstance.getFinishUserId());
			}
		}
		//设置完成时间
		int nodeDuration = this.getWfDuration(wfHisInstance);
		//设置当前环节完成时间
		wfHisInstance.setNodeDuration(nodeDuration);
		//设置总完成时间
		wfHisInstance.setTotalDuration(wfHisInstance.getTotalDuration()+nodeDuration);
		dao().insert(wfHisInstance);
	}

	/**
	 * 保存流程实例
	 * 
	 * @param wfInstance 流程实例对象
	 */
	public void delInstance(WfInstance wfInstance) {
		dao().delete(wfInstance);
	}

	/**
	 * 更新流程实例
	 * 
	 * @param wfInstance 流程实例对象
	 */
	public void updateInstance(WfInstance wfInstance) {
		dao().update(wfInstance);
	}

	/**
	 * 更新流程实例
	 * 
	 * @param wfInstance 流程实例对象
	 */
	public void updateHisInstance(WfHisInstance wfInstance) {
		dao().update(wfInstance);
	}

	/**
	 * 保存操作记录
	 * 
	 * @param wfOpnn 操作记录对象
	 */
	public void saveOpnn(WfOpnn wfOpnn) {
		dao().insert(wfOpnn);
	}

	
}
