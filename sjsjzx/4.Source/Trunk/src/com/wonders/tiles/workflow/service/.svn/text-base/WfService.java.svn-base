package com.wonders.tiles.workflow.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.tiles.workflow.WfConstants;
import com.wonders.tiles.workflow.entity.WfHisInstance;
import com.wonders.tiles.workflow.entity.WfInstance;
import com.wonders.tiles.workflow.entity.WfNode;
import com.wonders.tiles.workflow.entity.WfOpnn;
import com.wonders.tiles.workflow.entity.WfRoute;
import com.wonders.tiles.workflow.entity.WfUser;
import com.wonders.tiles.workflow.exception.WfException;
import com.wonders.tiles.workflow.util.BeanUtils;

/**
 * 流程服务的service，提供各种流程操作.
 *
 */
@IocBean
public class WfService {

    /** WfDbService实例 */
	@Inject("refer:wfDbService")
    WfDbService wfDbService = null;
	
	//log日志信息
	private Log logs = Logs.get();
	
    /**
     * 启动流程
     * @param userId 用户ID
     * @param wfCode 流程代码
     * @param busId 业务ID
     * @param variableMap 流程控制变量 其中key：变量名称；value：变量值
     * @return 流程编号
     */
    public String start(String userId,String wfCode,String busId,Map<String,String> variableMap)throws WfException{
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfCodeByCode()查询出wfCode对象，如果为空，抛出ERROR_NO_WF异常
    	wfDbService.getWfDefineByCode(wfCode);
    	//查询wfDbService.queryWfNodeListByCode()流程节点列表wfNodeList，
    	List<WfNode> wfNodeList  = wfDbService.findWfNodeListByCode(wfCode);
    	//循环wfNodeList，找出开始节点startNode
    	WfNode startNode = this.filterNodeByType(WfConstants.NODE_TYPE_START, wfNodeList);
    	//如果为空，抛出ERROR_WF_NODE异常
    	if(startNode ==null){
    		throw new WfException(WfConstants.ERROR_WF_NODE);
    	}
    	//生成节点MAP
    	Map<String,WfNode> nodeMap = genNodeMap(wfNodeList);
    	//生成开始节点流程实例并保存
    	WfHisInstance startWfInstance = genStartWfInstance(startNode,busId,userId);
    	WfInstance oldStartInstance = new WfInstance();
    	BeanUtils.copyProperties(oldStartInstance, startWfInstance);
		wfDbService.saveHisInstance(startWfInstance);
		//获取当前节点的路由列表
		List<WfRoute> wfRouteList = wfDbService.findWfRouteListByNodeId(startNode.getNodeId());
    	//如果只有一个下一个环节
    	if(wfRouteList.size() == 1){
    		WfRoute wfRoute = wfRouteList.get(0);
    		//判断是否是默认路径，如果不是默认路径，则抛出异常
    		if(WfConstants.VALUE_YESNO_YES.equals(wfRoute.getIsCon())){
    			throw new WfException(WfConstants.ERROR_NO_PATH);
    		}else{
    			//生成下一个环节的流程实例并保存
        		dealStartWfInstance(nodeMap,wfRouteList,oldStartInstance,userId,variableMap);
    	}
    	//如果有多个下一个环节
    	}else if(wfRouteList.size() > 1){
    		//判断是否是存在默认路径，如果不存在默认路径，则抛出异常
    		if(!wfDbService.isDefaultRouteByNodeId(startNode.getNodeId())){
    			throw new WfException(WfConstants.ERROR_NO_PATH);
    		}
    		List<WfRoute> conRouteList = this.filterConRoute(wfRouteList, variableMap);
    		//生成下一个环节的流程实例并保存
    		dealStartWfInstance(nodeMap,conRouteList,oldStartInstance,userId,variableMap);
    		//提交到下一个环节的数量,判断是否走存在条件的路径。
    		if(conRouteList.size() == 0){
    		//带条件的路径（包含必须走的）都不满足，则走默认的路径
    		List<WfRoute> defaultRoute = this.filterDefaultRoute(wfRouteList);
    		//生成下一个环节的流程实例并保存
    		dealStartWfInstance(nodeMap,defaultRoute,oldStartInstance,userId,variableMap);
    	}
    	}
    	//生成并保存操作记录对象
    	WfOpnn wfOpnn = genStartWfOpnn(userInfo,startWfInstance);
    	wfDbService.saveOpnn(wfOpnn);
    	return startWfInstance.getInstanceNo();
    }
    
    
    /**
     * 签收案件，签收后只有userId用户可视
     * @param userId 用户ID
     * @param instanceId 流程实例ID
     * @return true:签收成功;false:签收失败
     */
    public boolean signIn(String userId,String instanceId)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfInstanceById()查询出WfInstance对象
    	WfInstance wfInstance = wfDbService.findWfInstanceById(instanceId);
    	//判断状态是否正常
    	if(WfConstants.STATUS_FINISH.equals(wfInstance.getStatus()) || WfConstants.STATUS_BACK.equals(wfInstance.getStatus())){
    		throw new WfException(WfConstants.ERROR_INSTANCE_STATUS);
    	}
    	//判断WfInstance签收人信息是否正常 
    	if(userInfo == null){
    		throw new WfException(WfConstants.ERROR_NO_USER);
    	}else{
    		//重复签收log提示
    		if(WfConstants.VALUE_YESNO_YES.endsWith(wfInstance.getIsSign())){
    			logs.info(this.genWfInstanceLogInfo(wfInstance, WfConstants.INFO_HAVE_SIGNIN));
    		}
           //如果没有签收wfDbService.signIn()，设置签收
    		wfInstance.setIsSign(WfConstants.VALUE_YESNO_YES);
    		wfInstance.setSignDate(new Date());
    		wfInstance.setSignUserId(userId);
    		wfInstance.setTudoUserId(userId);
    		wfDbService.updateInstance(wfInstance);
    		//生成并保存操作记录对象
    		WfOpnn wfOpnn = genWfOpnn(wfInstance,null,WfConstants.OP_TYPE_SIGNIN,userInfo);
    		wfDbService.saveOpnn(wfOpnn);
    	}
    	//前节点的前节点实例为不可回收状态
    	wfDbService.setCallbackFlag(wfInstance.getInstanceNo(),"'"+wfInstance.getFinPreNodeId().replaceAll(",", "','")+"'");
    	return true;
    }
    
    /**
     * 提交案件,如果存在多个按键，同时提交,下一步节点非直线流程不建议使用.
     * @param userId 用户ID
     * @param instanceId 流程实例ID
     * @param variableMap 流程控制变量 其中key：变量名称；value：变量值
     * @param memo 提交意见，可以为空
     * @return true:提交成功;false:提交失败
     */
    public boolean sendForward(String userId,String instanceNo,String busId,Map<String,String> variableMap,String memo)throws WfException{
    	List<WfInstance> instanceList = wfDbService.findCurInstanceList(busId, instanceNo);
    	for(WfInstance wfInstance : instanceList){
    		this.sendForward(userId, wfInstance.getInstanceId(), variableMap, memo);
    	}
    	return true;
    }
    /**
     * 提交案件
     * @param userId 用户ID
     * @param instanceId 流程实例ID
     * @param variableMap 流程控制变量 其中key：变量名称；value：变量值
     * @param memo 提交意见，可以为空
     * @return true:提交成功;false:提交失败
     */
    public boolean sendForward(String userId,String instanceId,Map<String,String> variableMap,String memo)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfInstanceById()查询出WfInstance对象
    	WfInstance wfInstance = wfDbService.findWfInstanceById(instanceId);
    	if(WfConstants.STATUS_FINISH.equals(wfInstance.getStatus()) || WfConstants.STATUS_BACK.equals(wfInstance.getStatus())){
    		throw new WfException(WfConstants.ERROR_INSTANCE_STATUS);
    	}
    	boolean isEnd = false;
    	//查询当前流程的节点列表
    	List<WfNode> wfNodeList  = wfDbService.findWfNodeListByCode(wfInstance.getWfCode());
    	//生成节点MAP
    	Map<String,WfNode> nodeMap = genNodeMap(wfNodeList);
    	//获取当前节点的路由列表
    	List<WfRoute> wfRouteList = wfDbService.findWfRouteListByNodeId(wfInstance.getCurNodeId());
    	//如果只有一个下一个环节
    	if(wfRouteList.size() == 1){
    		WfRoute wfRoute = (WfRoute) wfRouteList.get(0);
    		//判断是否是默认路径，如果不是默认路径，则抛出异常
    		if(WfConstants.VALUE_YESNO_YES.equals(wfRoute.getIsCon())){
    			throw new WfException(WfConstants.ERROR_NO_PATH);
    		}else{
    			//提交到下一个环节
    			isEnd = dealNextWfInstance(wfRoute,nodeMap,wfInstance,userId,variableMap);
			}
    	//如果有多个下一个环节
    	}else if(wfRouteList.size() > 1){
    		//判断是否是存在默认路径，如果不存在默认路径，则抛出异常
    		if(!wfDbService.isDefaultRouteByNodeId(wfInstance.getCurNodeId())){
    			throw new WfException(WfConstants.ERROR_NO_PATH);
    		}
    		List<WfRoute> conRouteList = this.filterConRoute(wfRouteList, variableMap);
    		//生成下一个环节的流程实例并保存
    		for(WfRoute wfRoute : conRouteList){
    			isEnd =  dealNextWfInstance(wfRoute,nodeMap,wfInstance,userId,variableMap);
    			//如果是下一个节点是最后一个节点,将本节点设置为不可回收状态
    	    	
    		}
    		//提交到下一个环节的数量,判断是否走存在条件的路径。
    		if(conRouteList.size() == 0){
    			//带条件的路径（包含必须走的）都不满足，则走默认的路径
    			List<WfRoute> defaultRoute = this.filterDefaultRoute(wfRouteList);
    			//生成下一个环节的流程实例并保存
    			for(WfRoute wfRoute : defaultRoute){
    				isEnd = dealNextWfInstance(wfRoute,nodeMap,wfInstance,userId,variableMap);
        	    	
        		}
    		}
    	}
    	//更新当前环节的流程实例的属性
    	wfInstance.setFinishDate(new Date());
		wfInstance.setStatus(WfConstants.STATUS_FINISH);
		wfInstance.setFinishUserId(userId);
		//流程分支节点是不是已经结束,如果有结束的不能回收
		if(isEnd){//流程已经完成的设置
			this.finishWfAdaptor(wfInstance, userId);
		}else{
			wfInstance.setIsCallback(WfConstants.VALUE_YESNO_YES);
		}
		this.dealInstanceToHis(wfInstance, true);
		//生成并保存操作记录对象
    	WfOpnn wfOpnn = genWfOpnn(wfInstance,memo,WfConstants.OP_TYPE_FORWARD,userInfo);
		wfDbService.saveOpnn(wfOpnn);
		//设置流程后的设置和操作
    	this.finishItemAdaptor(wfInstance.getInstanceNo(), userId, wfInstance.getFinPreNodeId());
    	
    	return true;
    }

   
    /**
     * 特送案件,特送将打破规则，不建议使用,建议直接使用拓扑结构实现.
     * @param userId 用户ID
     * @param instanceId 流程实例ID
     * @param aimNodeId 目标节点ID
     * @param memo 特送意见，可以为空
     * @return true:特送成功;false:特送失败
     */
    public boolean specialSend(String userId,String instanceId,String aimNodeId,String memo,Map<String,String> variableMap)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfInstanceById()查询出WfInstance对象
    	WfInstance wfInstance = wfDbService.findWfInstanceById(instanceId);
    	//判断状态是否正常
    	if(WfConstants.STATUS_FINISH.equals(wfInstance.getStatus()) || WfConstants.STATUS_BACK.equals(wfInstance.getStatus())){
    		throw new WfException(WfConstants.ERROR_INSTANCE_STATUS);
    	}
    	//查询当前流程的节点列表
    	List<WfNode> wfNodeList  = wfDbService.findWfNodeListByCode(wfInstance.getWfCode());
    	//生成节点MAP
    	Map<String,WfNode> nodeMap = genNodeMap(wfNodeList);
    	//生成特送环节的流程实例
    	WfInstance specialWfInstance = genSpecialWfInstance(nodeMap,aimNodeId,wfInstance,variableMap);
    	wfDbService.saveInstance(specialWfInstance);
    	//建立操作记录wfDbService.creatOpnn();
    	WfOpnn wfOpnn = genWfOpnn(wfInstance,memo,WfConstants.OP_TYPE_SPECIAL,userInfo);
		wfDbService.saveOpnn(wfOpnn);
    	//更新当前环节的流程实例的属性
    	wfInstance.setFinishDate(new Date());
		wfInstance.setStatus(WfConstants.STATUS_FINISH);
		wfInstance.setFinishUserId(userId);
		wfInstance.setIsCallback(WfConstants.VALUE_YESNO_YES);
		this.dealInstanceToHis(wfInstance, true);
		//设置流程后的设置和操作
    	this.finishItemAdaptor(wfInstance.getInstanceNo(), userId, wfInstance.getFinPreNodeId());
    	return true;
    }
    
    /**
     * 回收案件.
     * @param userId 用户ID
     * @param instanceId 流程实例ID
     * @return true:回收成功;false:回收失败
     */
    public boolean callBack(String userId,String instanceId)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfInstanceById()查询出WfInstance对象
    	WfHisInstance wfHisInstance = wfDbService.findWfHisInstanceById(instanceId);
    	//待更新的流程实例列表，下一个环节有多个已完成前置节点
    	List<WfInstance> curInstanceList = wfDbService.findCurInstanceList(wfHisInstance.getBusId(), wfHisInstance.getInstanceNo());
    	for(WfInstance wfInstance : curInstanceList){
    		if(wfHisInstance.getCurNodeId().equals(wfInstance.getFinPreNodeId())){
    			wfDbService.delInstance(wfInstance);
    		}else if(wfInstance.getFinPreNodeId().indexOf(wfHisInstance.getCurNodeId()) > 0){
    			//设置流程状态
    			wfInstance.setStatus(WfConstants.STATUS_WAIT);
    			//将节点从前置完成的节点中删除
    			this.removeInstanceFinNodeId(wfInstance, wfHisInstance.getCurNodeId());
    			wfDbService.updateInstance(wfInstance);
    		}
    	}
    	//生成新的流程实例对象并保存
    	WfInstance wfInstance = dealInstanceToCur(wfHisInstance);
    	wfInstance.setIsSign(WfConstants.VALUE_YESNO_YES);
    	wfInstance.setSignDate(new Date());
    	wfInstance.setSignUserId(userId);
    	wfInstance.setTudoUserId(userId);
    	wfInstance.setStatus(WfConstants.STATUS_SHOW);
    	wfInstance.setFinishDate(null);
    	wfInstance.setFinishUserId(null);
    	wfInstance.setRecevieDate(new Date());
    	//?
    	wfInstance.setInstanceId(getUuid());
		//时间处理
		this.dealNodeDuration(wfInstance, wfInstance.getDurationType());
    	wfDbService.saveInstance(wfInstance);
    	//生成并保存操作记录对象
    	WfOpnn wfOpnn = genWfOpnn(wfInstance,null,WfConstants.OP_TYPE_CALLBACK,userInfo);
		wfDbService.saveOpnn(wfOpnn);
		//设置当前历史实例为不可回收状态
		wfHisInstance.setIsCallback(WfConstants.VALUE_YESNO_NO);
    	wfDbService.updateHisInstance(wfHisInstance);
    	//签收完成操作
    	finishCallBackAdaptor();
    	return true;
    }
    
    /**
     * 退回案件.
     * @param userId 用户ID
     * @param instanceId 流程实例ID
     * @param preNodeId 前置节点ID，当前置节点唯一时，preNodeId可为空
     * @param memo 退回意见，可以为空
     * @return true:退回成功;false:退回失败
     */
    public boolean sendBack(String userId,String instanceId,String preNodeId,String memo)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfInstanceById()查询出WfInstance对象
    	WfInstance wfInstance = wfDbService.findWfInstanceById(instanceId);
    	//判断状态是否正常
    	if(WfConstants.STATUS_FINISH.equals(wfInstance.getStatus()) || WfConstants.STATUS_BACK.equals(wfInstance.getStatus())){
    		throw new WfException(WfConstants.ERROR_INSTANCE_STATUS);
    	}
    	WfHisInstance preWfInstance = null;
    	if(wfInstance.getFinPreNodeId().indexOf(",") ==-1){
    		preWfInstance = wfDbService.getFinWfInstanceByNodeId(wfInstance.getFinPreNodeId(),wfInstance.getInstanceNo());
    		wfInstance.setStatus(WfConstants.STATUS_FINISH);
    		wfInstance.setFinishDate(new Date());
    		wfInstance.setFinishUserId(userId);
    		this.dealInstanceToHis(wfInstance, true);
    	}else{
      		preWfInstance = wfDbService.getFinWfInstanceByNodeId(preNodeId,wfInstance.getInstanceNo());
      		wfInstance.setStatus(WfConstants.STATUS_WAIT);
			//将节点从前置完成的节点中删除
			this.removeInstanceFinNodeId(wfInstance, preNodeId);
			wfDbService.updateInstance(wfInstance);
    	}
    	//生成新的流程实例对象并保存
    	WfInstance wfCurInstance = dealInstanceToCur(preWfInstance);
    	wfCurInstance.setIsSign(WfConstants.VALUE_YESNO_YES);
    	//wfCurInstance.setSignDate(new Date());
    	wfCurInstance.setSignUserId(preWfInstance.getFinishUserId());
    	wfCurInstance.setTudoUserId(preWfInstance.getFinishUserId());
    	wfCurInstance.setStatus(WfConstants.STATUS_SHOW);
    	wfCurInstance.setFinishDate(null);
    	wfCurInstance.setFinishUserId(null);
    	wfCurInstance.setRecevieDate(new Date());
    	//?
    	wfCurInstance.setInstanceId(getUuid());
		//时间处理
		this.dealNodeDuration(wfCurInstance, wfCurInstance.getDurationType());
    	wfDbService.saveInstance(wfCurInstance);
    	//生成并保存操作记录对象
    	WfOpnn wfOpnn = genWfOpnn(wfInstance,memo,WfConstants.OP_TYPE_BACK,userInfo);
		wfDbService.saveOpnn(wfOpnn);
		//设置流程后的设置和操作
    	this.finishItemAdaptor(wfInstance.getInstanceNo(), userId, wfInstance.getFinPreNodeId());
    	return true;
    }
    
    /**
     * 根据业务ID获取流程操作意见
     * @param busId 业务ID
     * @return 内置WF_OPNN对象的List
     */
    public List<WfOpnn> wfOpnnMemo(String busId){
    	 //查询出busId对应所有意见
    	return wfDbService.findWfOpnnByBusId(busId);
    }
    
    /**
	 * 根据业务主键和流程No查询操作记录对象列表
	 * @param busId 业务主键
	 * @param instanceNo 流程No.
	 * @return List<WfOpnn>
	 */
    public List<WfOpnn> wfOpnnMemo(String busId, String instanceNo){
    	 //查询出busId对应所有意见
    	return wfDbService.findWfOpnn(busId, instanceNo);
    }
    
    /**
     * 逻辑删除流程待办项，可恢复
     * @param instanceId 流程实例ID
     * @return true:删除成功;false:删除失败
     */
    public boolean deleteWf(String userId,String instanceId)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfInstanceById()查询出WfInstance对象
    	WfInstance wfInstance = wfDbService.findWfInstanceById(instanceId);
    	//设置删除标志为删除
    	wfInstance.setValidity(WfConstants.VALUE_YESNO_NO);
		wfDbService.updateInstance(wfInstance);
		//生成并保存操作记录对象
    	WfOpnn wfOpnn = genWfOpnn(wfInstance,"待办项删除",WfConstants.OP_TYPE_DELETE,userInfo);
		wfDbService.saveOpnn(wfOpnn);
    	return true;
    }
    
    /**
     * 暂停流程待办项，通过恢复操作恢复.
     * @param instanceId 流程实例ID
     * @return true:暂停成功;false:暂停失败
     */
    public boolean suspendWf(String userId,String instanceId)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfInstanceById()查询出WfInstance对象
    	WfInstance wfInstance = wfDbService.findWfInstanceById(instanceId);
    	//设置状态标志为暂停
    	wfInstance.setStatus(WfConstants.STATUS_STOP);
		wfDbService.updateInstance(wfInstance);
		//生成并保存操作记录对象
    	WfOpnn wfOpnn = genWfOpnn(wfInstance,"待办项暂停",WfConstants.OP_TYPE_SUSPEND,userInfo);
		wfDbService.saveOpnn(wfOpnn);
    	return true;
    }

    /**
     * 恢复流程待办项.
     * @param instanceId 流程实例ID
     * @return true:恢复成功;false:恢复失败
     */
    public boolean resumeWf(String userId,String instanceId)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//根据wfDbService.queryWfInstanceById()查询出WfInstance对象
    	WfInstance wfInstance = wfDbService.findWfInstanceById(instanceId);
    	//设置状态标志为待办
    	wfInstance.setStatus(WfConstants.STATUS_SHOW);
		wfDbService.updateInstance(wfInstance);
		//生成并保存操作记录对象
    	WfOpnn wfOpnn = genWfOpnn(wfInstance,"待办项恢复",WfConstants.OP_TYPE_RESUME,userInfo);
		wfDbService.saveOpnn(wfOpnn);
    	return true;
    }
    
    /**
     * 物理删除流程，不可恢复
     * @param busId 业务ID
     * @return true:删除成功;false:删除失败
     */
    public boolean truncateWf(String userId,String busId,String instanceNo)throws WfException{
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	//物理删除busId的实例
    	wfDbService.truncateWf(busId,instanceNo);
    	//生成操作记录对象
    	WfOpnn wfOpnn = new WfOpnn();
    	wfOpnn.setOpnnId(getUuid());
    	wfOpnn.setBusId(busId);
    	wfOpnn.setNodeName("");
    	wfOpnn.setOpnnTime(new Timestamp(System.currentTimeMillis()));
    	wfOpnn.setOpnnType(WfConstants.OP_TYPE_DELETE);
    	wfOpnn.setUserId(userId);
    	wfOpnn.setUserName(userInfo.getDisplayName());
    	//保存操作记录对象
    	wfDbService.saveOpnn(wfOpnn);
    	return true;
    }

	/**
	 * 计算节点总用时,用于待办计时.
	 * @param busId 业务主键
	 */
	public int getNodeDuration(String instanceNo,String curNodeId,Date durationDate,int totalDuration) {
		return this.wfDbService.getWfDuration(instanceNo, curNodeId, durationDate)+totalDuration;
	}


	/**
	 * 计算节点总用时,用于待办计时.
	 * @param busId 业务主键
	 */
	public int getNodeDuration(String instanceId) {
		WfInstance wfInstance =  this.wfDbService.findWfInstanceById(instanceId);
		return this.wfDbService.getWfDuration(wfInstance.getInstanceNo(), wfInstance.getCurNodeId(), wfInstance.getDurationDate())+wfInstance.getTotalDuration();
	}
	


	/**
	 * 计算节点总用时,用于待办计时.
	 * @param busId 业务主键
	 */
	public Date getWorkDay(Date curDate, int count) {
		return WorkDayService.getInstance().queryDate(curDate, count+1);
	}
    
    /**
     * 保存意见对象
     * @param busId 业务主键
     * @param nodeName 节点名称
     * @param userId 用户ID
     * @param opnnType 意见类型
     * @param opnnMemo 意见信息
     */
    public void saveOpnnMemo(String busId, String nodeId, String nodeName, String userId, String opnnType, String opnnMemo)
    {
      WfUser userInfo = this.wfDbService.getUserInfoById(userId);
      WfOpnn wfOpnn = new WfOpnn();
      wfOpnn.setOpnnId(getUuid());
      wfOpnn.setBusId(busId);
      wfOpnn.setNodeName(nodeName);
      wfOpnn.setNodeId(nodeId);
  	  wfOpnn.setOpnnTime(new Timestamp(System.currentTimeMillis()));
      wfOpnn.setOpnnType(opnnType);
      wfOpnn.setUserId(userId);
      wfOpnn.setUserName(userInfo.getDisplayName());
      wfOpnn.setOpnnMemo(opnnMemo);

      this.wfDbService.saveOpnn(wfOpnn);
    }
    
    /**
     * 保存意见对象
     * @param wfOpnn 意见对象
     */
    public void saveOpnnMemo(WfOpnn wfOpnn){
      if(Strings.isBlank(wfOpnn.getOpnnId())){
    	  wfOpnn.setOpnnId(this.getUuid());
      }
      this.wfDbService.saveOpnn(wfOpnn);
    }
    

    /**
     * 生成下一个环节的流程实例 ，包含会签。
     * @param nodeMap
     * @param conRouteList
     * @param busId
     * @param instanceNo
     * @param variableMap
     */
    private void dealStartWfInstance(Map<String,WfNode> nodeMap,List<WfRoute> conRouteList,WfInstance wfStartInstance,String userId,Map<String,String> variableMap){
    	for(WfRoute wfRoute : conRouteList){
    		if(WfConstants.VALUE_YESNO_YES.equals(wfRoute.getIsSendOut())){
    			//如果是会签类型
    			String sendOut = variableMap.get(WfConstants.SEND_OUTS);
    			if(Strings.isBlank(sendOut)){
    				throw new WfException(WfConstants.ERROR_NO_PATH);
    			}
    			List<WfInstance>  wfInstanceList = this.genSendOutWfInstances(nodeMap, wfRoute, wfStartInstance.getBusId(), wfStartInstance.getInstanceNo(), sendOut.split(","));
    			for(WfInstance wfInstance : wfInstanceList){
    				wfDbService.saveInstance(wfInstance);
    			}
    		}else{//普通类型
				WfInstance wfInstance = genWfInstance(nodeMap,wfRoute,wfStartInstance,userId);
				//处理变量信息
				this.dealVariableInfo(variableMap, wfInstance);
	    		wfDbService.saveInstance(wfInstance);
    		}
		}
    }
    
    /**
     * 提交到下一个环节，产生新的实例
     * @param wfRoute 节点路由
     * @param nodeMap 节点MAP key：节点ID value：节点对象
     * @param wfInstance 当前节点实例
     * @param userId 用户ID
     */
    private boolean dealNextWfInstance(WfRoute wfRoute,Map<String,WfNode> nodeMap,WfInstance wfInstance,String userId,Map<String,String> variableMap){
    	//判断下一个是不是最后一个节点（end节点），是：true
    	boolean isEnd = false;
    	//查找下一个节点在等待和待办状态的流程实例
    	WfInstance nextWfInstance = null;
    	//只有会签延续的时候才进行部门过滤
    	if(WfConstants.SENDOUT_3.equals(wfRoute.getIsSendOut())){
    		nextWfInstance = wfDbService.getWfInstanceByNodeId(wfRoute.getNextNodeId(),wfInstance.getInstanceNo(),wfInstance.getSendoutDept());
    	}else{
    		nextWfInstance = wfDbService.getWfInstanceByNodeId(wfRoute.getNextNodeId(),wfInstance.getInstanceNo(),null);
    	}
    	//如果流程实例为空
		if(nextWfInstance == null){
			List<WfInstance>  wfInstanceList = new  ArrayList<WfInstance>();
			if(WfConstants.SENDOUT_1.equals(wfRoute.getIsSendOut())){
    			//如果是会签类型
    			String sendOut = variableMap.get(WfConstants.SEND_OUTS);
    			if(Strings.isBlank(sendOut)){
    				throw new WfException(WfConstants.ERROR_NO_PATH);
    			}
    			wfInstanceList = this.genSendOutWfInstances(nodeMap, wfRoute, wfInstance.getBusId(), wfInstance.getInstanceNo(), sendOut.split(","));
    		}else{
			//生成下一个环节新的流程实例
    			wfInstanceList.add(genWfInstance(nodeMap,wfRoute,wfInstance,userId));
    		}
			for(WfInstance tempNextWfInstance : wfInstanceList){
				//设置签收人
				this.dealVariableInfo(variableMap, tempNextWfInstance);
				//如果下一步是结束节点流程实例状态为已完成状态
				isEnd = this.dealInstanceBranch(wfRoute, nodeMap, tempNextWfInstance,wfInstance.getSendoutFlag(), userId);
				//如果是最后节点，并且是等待状态，直接处理为办结 
				if(isEnd){
					//保存办结意见信息
					wfDbService.saveOpnn(genFinishWfOpnn(tempNextWfInstance,userId));
					//设置完成信息状态设置
					finishWfAdaptor(tempNextWfInstance, userId) ;
					//处理实例
					this.dealInstanceToHis(tempNextWfInstance, false);
				}else{
					//保存新的流程实例
					wfDbService.saveInstance(tempNextWfInstance);
				}
			}
		}else if(WfConstants.STATUS_WAIT.equals(nextWfInstance.getStatus())){
			//如果流程实例不为空，根据前置节点的个数及类型判断其等待、完成、待办状态
			//设置签收人
			this.dealVariableInfo(variableMap, nextWfInstance);
			//设置流程已完成节点
			if(nextWfInstance.getFinPreNodeId().indexOf(wfRoute.getNodeId()) == -1){
				nextWfInstance.setFinPreNodeId(nextWfInstance.getFinPreNodeId()+","+wfRoute.getNodeId());
			}
			//如果下一步是结束节点流程实例状态为已完成状态
			isEnd = this.dealInstanceBranch(wfRoute, nodeMap, nextWfInstance,wfInstance.getSendoutFlag(),userId);
			//如果是最后节点，并且是等待状态，直接处理为办结 
			if(isEnd){
				//保存办结意见信息
				wfDbService.saveOpnn(genFinishWfOpnn(nextWfInstance,userId));
				//设置完成信息状态设置
				finishWfAdaptor(nextWfInstance, userId) ;
				//处理实例
				this.dealInstanceToHis(nextWfInstance, true);
			}else{
				//保存新的流程实例
				wfDbService.updateInstance(nextWfInstance);
			}
		}
		return isEnd;
    }
    
    /**
     * 处理流程实例下一环节的状态.
     * @param wfRoute
     * @param nodeMap
     * @param nextWfInstance
     * @param userId
     * @return 是否是结束环节
     */
    private boolean dealInstanceBranch(WfRoute wfRoute,Map<String,WfNode> nodeMap,WfInstance nextWfInstance,String sendoutFlag,String userId){
    	boolean result = false;
		WfNode nextWfNode = nodeMap.get(wfRoute.getNextNodeId());
		//设置默认为打开状态
		nextWfInstance.setStatus(WfConstants.STATUS_SHOW);
		//判断是否能保持打开状态
		if(WfConstants.JOIN_TYPE_2.equals(nextWfNode.getJoinType()) || WfConstants.JOIN_TYPE_9.equals(nextWfNode.getJoinType()) && WfConstants.JOIN_TYPE_2.equals(wfRoute.getPartJoinType())){
			//无会签,无组合形式
			if(WfConstants.SENDOUT_0.equals(wfRoute.getIsSendOut()) && Strings.isBlank(wfRoute.getPartJoinType())){
				if(nextWfNode.getSendCount() > nextWfInstance.getFinPreNodeId().split(",").length){
					nextWfInstance.setStatus(WfConstants.STATUS_WAIT);
				}
			}else {//会签和会签延续
				if(this.getHisSendOutCount(nextWfInstance.getInstanceNo(), sendoutFlag) < nextWfNode.getSendCount()){
					nextWfInstance.setStatus(WfConstants.STATUS_WAIT);
				}
			}
		}else if(WfConstants.JOIN_TYPE_3.equals(nextWfNode.getJoinType()) || (WfConstants.JOIN_TYPE_9.equals(nextWfNode.getJoinType()) && WfConstants.JOIN_TYPE_3.equals(wfRoute.getPartJoinType()))){
			//无会签,无组合形式
			if(WfConstants.SENDOUT_0.equals(wfRoute.getIsSendOut()) && Strings.isBlank(wfRoute.getPartJoinType())){ 
				//完成的数量小于全部前置节点的数量
				if(nextWfNode.getPreNodeId().split(",").length > nextWfInstance.getFinPreNodeId().split(",").length){
					nextWfInstance.setStatus(WfConstants.STATUS_WAIT);
				}
			}else{//会签和会签延续
				if(this.getCurSendOutCount(nextWfInstance.getInstanceNo(), sendoutFlag) > 1){
					nextWfInstance.setStatus(WfConstants.STATUS_WAIT);
				}
			}
		}
		//如果是最后节点，并且是等待状态，直接处理为办结 
		if(WfConstants.NODE_TYPE_END.equals(nextWfNode.getNodeType()) && WfConstants.STATUS_SHOW.equals(nextWfInstance.getStatus())){
			result = true;
			nextWfInstance.setStatus(WfConstants.STATUS_FINISH);
			nextWfInstance.setFinishDate(new Date());
			nextWfInstance.setFinishUserId(userId);
		}
		return result;
    }
    
    private int getCurSendOutCount(String instanceNo,String sendoutFlag){
    	int result = 0;
    	List<WfInstance> wfInstanceList = this.wfDbService.findCurSendOutList(sendoutFlag, instanceNo);
		if(wfInstanceList != null ){
			result = wfInstanceList.size();
		}
		return result;
    }

    
    private int getHisSendOutCount(String instanceNo,String sendoutFlag){
    	int result = 0;
    	List<WfHisInstance> wfHisInstanceList = this.wfDbService.findHisSendOutList(sendoutFlag, instanceNo);
		if(wfHisInstanceList != null){
			result = wfHisInstanceList.size()+1;
		}
		return result;
    }
    
    
    /**
     * 从路由中过滤符合条件的路径，包含必须要走的路径.
     * @param wfRouteList 路由列表
     * @param variableMap 变量map
     * @return
     */
    private List<WfRoute> filterConRoute(List<WfRoute> wfRouteList,Map<String,String> variableMap){
    	List<WfRoute> resultList = new ArrayList<WfRoute>();
    	for(int i=0;i<wfRouteList.size();i++){
			WfRoute wfRoute = (WfRoute) wfRouteList.get(i);
			//满足走向条件，即变量值和设置变量的值相等
			//必须的走的默认路径，注意默认路径可能有多条
			if(WfConstants.VALUE_YESNO_YES.equals(wfRoute.getIsCon()) && variableMap != null && wfRoute.getConValues() != null){
				if(wfRoute.getConValues().equals(variableMap.get(wfRoute.getConKeys()))){
					//生成下一个环节的流程实例并保存
					resultList.add(wfRoute);
				}
			}else if(WfConstants.VALUE_YESNO_NO.equals(wfRoute.getIsCon()) && WfConstants.VALUE_YESNO_YES.equals(wfRoute.getIsNeed())){
				resultList.add(wfRoute);
			}
		}
    	return resultList;
    }

    /**
     * 为案件设置签收人，主要为上一环节指派.
     * @param variableMap 变量Map
     * @param nextWfInstance 案件
     */
    private void dealVariableInfo(Map<String,String> variableMap,WfInstance nextWfInstance){
    	//处理签收
    	if(variableMap !=null && variableMap.containsKey(WfConstants.SIGN_USER_ID)){
	    	WfUser userInfo = wfDbService.getUserInfoById((String)variableMap.get(WfConstants.SIGN_USER_ID));
			if(userInfo !=null && WfConstants.VALUE_YESNO_NO.endsWith(nextWfInstance.getIsSign())){
				//设置签收日期--默认系统时间
				if(variableMap.containsKey(WfConstants.SIGN_DATE)){
					nextWfInstance.setSignDate(new Date(Long.parseLong(variableMap.get(WfConstants.SIGN_DATE))));
				}else{
					nextWfInstance.setSignDate(null);
				}
				//设置签收人
				nextWfInstance.setSignUserId(userInfo.getUserId());
				nextWfInstance.setTudoUserId(userInfo.getUserId());
			}else{
	    		throw new WfException(WfConstants.ERROR_NO_USER);
	    	}
		}else if(variableMap !=null && variableMap.containsKey(WfConstants.DEPT_FILTER)){
    		//如果未签收的时候在设置
			if(Strings.isBlank(nextWfInstance.getSignUserId())){
	    		String userIds = wfDbService.genUserIdsByFilter(nextWfInstance.getRoleId(), variableMap.get(WfConstants.DEPT_FILTER));
	    		if(Strings.isBlank(userIds)){
					throw new WfException(WfConstants.ERROR_NO_TODU_USER);
				}
    			nextWfInstance.setTudoUserId(userIds);
    		}
    	}
    }
    
    /**
     * 从路由中过滤缺省路径，可能有多条.
     * @param wfRouteList 路由列表
     * @return
     */
    private List<WfRoute> filterDefaultRoute(List<WfRoute> wfRouteList){
    	List<WfRoute> resultList = new ArrayList<WfRoute>();
    	for(int i=0;i<wfRouteList.size();i++){
			WfRoute wfRoute = (WfRoute) wfRouteList.get(i);
			if(WfConstants.VALUE_YESNO_NO.equals(wfRoute.getIsCon())){
				resultList.add(wfRoute);
			}
		}
    	return resultList;
    }
    
    /**
     * 将流转实例转化为历史实例并保存。
     * @param nextWfInstance 流转实例
     * @param isDeleteOld 是否删除流转实例。
     */
    private void dealInstanceToHis(WfInstance nextWfInstance,boolean isDeleteOld){
    	WfHisInstance wfHisInstance = new WfHisInstance();
		BeanUtils.copyProperties(wfHisInstance, nextWfInstance);
		wfDbService.saveHisInstance(wfHisInstance);
		if(isDeleteOld){
			wfDbService.delInstance(nextWfInstance);
		}
    }
    
    /**
     * 将流转实例转化为历史实例并保存。
     * @param nextWfInstance 流转实例
     * @param isDeleteOld 是否删除流转实例。
     */
    private WfInstance dealInstanceToCur(WfHisInstance wfHisInstance){
    	WfInstance nextWfInstance = new WfInstance();
		BeanUtils.copyProperties(nextWfInstance,wfHisInstance);
		return nextWfInstance;
    }
   

    /**
     * 将节点从前置完成的节点中删除 .
     * @param wfInstance
     * @param removeNodeId
     * @return
     */
    private WfInstance removeInstanceFinNodeId(WfInstance wfInstance,String removeNodeId){
    	if(wfInstance.getFinPreNodeId().startsWith(removeNodeId)){
			wfInstance.setFinPreNodeId(wfInstance.getFinPreNodeId().replaceAll(removeNodeId, ""));
		}else{
			wfInstance.setFinPreNodeId(wfInstance.getFinPreNodeId().replaceAll(","+removeNodeId, ""));
		}
    	return wfInstance;
    }
   
    /**
     * 生成节点MAP key：节点ID value：节点对象
     * @param wfNodeList<WfNode>
     * @return Map key：节点ID value：节点对象
     */
    private Map<String,WfNode> genNodeMap(List<WfNode> wfNodeList){
    	Map<String,WfNode> nodeMap = new HashMap<String,WfNode>();
    	for(int i=0;i<wfNodeList.size();i++){
    		WfNode wfNode = (WfNode)wfNodeList.get(i);
    		nodeMap.put(wfNode.getNodeId(), wfNode);
    	}
    	return nodeMap;
    }

	/**
	 * 根据节点类型获取节点对象.
	 * @param nodeType
	 * @param wfNodeList
	 * @return
	 */
	private WfNode filterNodeByType(String nodeType, List<WfNode> wfNodeList){
		for(int i=0;i<wfNodeList.size();i++){
    		WfNode wfNode = (WfNode)wfNodeList.get(i);
    		if(nodeType.equals(wfNode.getNodeType())){
    			return wfNode;
    		}
    	}
		return null;
	}
    
    /**
     * 根据当前节点流程实例生成操作记录对象
     * @param wfInstance 当前流程实例ID
     * @param opnnMemo 操作意见
     * @param opnnType 提交类型
     * @param userInfo 用户对象
     * @return WfOpnn 操作记录对象
     */
    private WfOpnn genWfOpnn(WfInstance wfInstance,String opnnMemo,String opnnType,WfUser userInfo){
    	WfOpnn wfOpnn = new WfOpnn();
    	wfOpnn.setOpnnId(getUuid());
    	wfOpnn.setBusId(wfInstance.getBusId());
    	wfOpnn.setNodeName(wfInstance.getCurNodeName());
    	wfOpnn.setOpnnTime(new Timestamp(System.currentTimeMillis()));
    	wfOpnn.setInstanceNo(wfInstance.getInstanceNo());
        wfOpnn.setNodeId(wfInstance.getCurNodeId());
    	//流程意见
    	if(!Strings.isBlank(opnnMemo)){
    		wfOpnn.setOpnnMemo(opnnMemo);
    	}
    	//意见类型
    	if(!Strings.isBlank(opnnType)){
        	wfOpnn.setOpnnType(opnnType);
    	}
    	wfOpnn.setUserId(userInfo.getUserId());
    	wfOpnn.setUserName(userInfo.getDisplayName());
    	wfOpnn.setInstanceId(wfInstance.getInstanceId());
		return wfOpnn;
    }
    
    /**
     * 生成开始节点操作记录对象.
     * @param busId 业务ID
     * @param startNode 开始节点对象
     * @param userInfo 用户对象
     * @return WfOpnn 操作记录对象
     */
    private WfOpnn genStartWfOpnn(WfUser userInfo,WfHisInstance wfInstance){
    	WfOpnn wfOpnn = new WfOpnn();
    	wfOpnn.setOpnnId(getUuid());
    	wfOpnn.setBusId(wfInstance.getBusId());
    	wfOpnn.setNodeName(wfInstance.getCurNodeName());
    	wfOpnn.setOpnnTime(new Timestamp(System.currentTimeMillis()));
    	wfOpnn.setOpnnType(WfConstants.OP_TYPE_START);
    	wfOpnn.setUserId(userInfo.getUserId());
    	wfOpnn.setUserName(userInfo.getDisplayName());
    	wfOpnn.setInstanceNo(wfInstance.getInstanceNo());
        wfOpnn.setNodeId(wfInstance.getCurNodeId());
    	wfOpnn.setInstanceId(wfInstance.getInstanceId());
    	return wfOpnn;
    }
    
    /**
     * 生成结束节点操作记录对象.
     * @param busId 业务ID
     * @param startNode 开始节点对象
     * @param userInfo 用户对象
     * @return WfOpnn 操作记录对象
     */
    private WfOpnn genFinishWfOpnn(WfInstance wfInstance,String userId){
    	//查询用户对象
    	WfUser userInfo = wfDbService.getUserInfoById(userId);
    	WfOpnn wfOpnn = new WfOpnn();
    	wfOpnn.setOpnnId(getUuid());
    	wfOpnn.setBusId(wfInstance.getBusId());
    	wfOpnn.setNodeName(wfInstance.getCurNodeName());
    	//延长开始时间
    	wfOpnn.setOpnnTime(new Timestamp(System.currentTimeMillis()+5000));
    	wfOpnn.setOpnnType(WfConstants.OP_TYPE_FINISH);
    	wfOpnn.setUserId(userInfo.getUserId());
    	wfOpnn.setInstanceNo(wfInstance.getInstanceNo());
    	wfOpnn.setUserName(userInfo.getDisplayName());
    	wfOpnn.setNodeId(wfInstance.getCurNodeId());
    	wfOpnn.setInstanceId(wfInstance.getInstanceId());
    	return wfOpnn;
    }

    /**
     * 生成开始节点的流程实例对象
     * @param nodeMap 节点MAP key：节点ID value：节点对象
     * @param startNode 启动节点对象
     * @param busId 业务ID
     * @return WfInstance 流程实例对象
     */
    private WfHisInstance genStartWfInstance(WfNode curNode,String busId,String userId){
    	WfHisInstance wfInstance = new WfHisInstance();
		wfInstance.setInstanceId(getUuid());
		wfInstance.setInstanceNo(getUuid());
		wfInstance.setBusId(busId);
		wfInstance.setCurNodeId(curNode.getNodeId());
		wfInstance.setCurNodeName(curNode.getNodeName());
		wfInstance.setFinPreNodeId(WfConstants.NODE_START);
		wfInstance.setIsSign(WfConstants.VALUE_YESNO_NO);
		wfInstance.setRecevieDate(new Date());
		wfInstance.setFinishDate(new Date());
		wfInstance.setRoleId(curNode.getRoleId());
		wfInstance.setTudoUserId(this.wfDbService.genUserIdsByRoleId(curNode.getRoleId()));
		wfInstance.setStatus(WfConstants.STATUS_FINISH);
		wfInstance.setValidity(WfConstants.VALUE_YESNO_YES);
		wfInstance.setWfCode(curNode.getWfCode());
		wfInstance.setFinishUserId(userId);
		wfInstance.setDurationDate(new Date());
		wfInstance.setTotalDuration(new Integer(0));
		wfInstance.setNodeDuration(new Integer(0));
		return wfInstance;
    }
    
    /**
     * 生成普通节点的流程实例对象.
     * @param nodeMap 节点MAP key：节点ID value：节点对象
     * @param wfRoute 节点路由对象
     * @param busId 业务ID
     * @return WfInstance 流程实例对象
     */
    private WfInstance genWfInstance(Map<String,WfNode> nodeMap,WfRoute wfRoute,WfInstance oldWfInstance,String userId){
    	WfNode curNode = (WfNode)nodeMap.get(wfRoute.getNextNodeId());
    	String filterUser = null;
		WfInstance wfInstance = new WfInstance();
		wfInstance.setInstanceId(getUuid());
		wfInstance.setInstanceNo(oldWfInstance.getInstanceNo());
		wfInstance.setBusId(oldWfInstance.getBusId());
		wfInstance.setCurNodeId(wfRoute.getNextNodeId());
		wfInstance.setCurNodeName(curNode.getNodeName());
		wfInstance.setFinPreNodeId(wfRoute.getNodeId());
		wfInstance.setIsSign(WfConstants.VALUE_YESNO_NO);

		wfInstance.setRecevieDate(new Date());
		wfInstance.setRoleId(curNode.getRoleId());
		wfInstance.setTudoUserId(this.wfDbService.genUserIdsByRoleId(curNode.getRoleId()));
		wfInstance.setStatus(WfConstants.STATUS_SHOW);
		wfInstance.setValidity(WfConstants.VALUE_YESNO_YES);
		wfInstance.setWfCode(curNode.getWfCode());
		if(WfConstants.SENDOUT_3.equals(wfRoute.getIsSendOut())){
			wfInstance.setSendoutDept(oldWfInstance.getSendoutDept());
			wfInstance.setSendoutFlag(oldWfInstance.getSendoutFlag());
			filterUser = this.wfDbService.genUserIdsByDeptCode(oldWfInstance.getSendoutDept());
		}
		//自动指定签收人
		if(!Strings.isBlank(curNode.getAssignUserNodeId())){
			WfOpnn wfOpnn = this.wfDbService.findWfOpnnByNodeId(oldWfInstance.getBusId(), oldWfInstance.getInstanceNo(), curNode.getAssignUserNodeId(),filterUser);
			if(wfOpnn != null){
				wfInstance.setSignUserId(wfOpnn.getUserId());
				wfInstance.setTudoUserId(wfOpnn.getUserId());
			}
		}
		//时间处理
		this.dealNodeDuration(wfInstance, curNode.getDurationType());
		return wfInstance;
    }

    /**
     * 生成特送节点的待办实例
     * @param nodeMap 节点MAP key：节点ID value：节点对象
     * @param aimNodeId 目标节点ID
     * @param wfInstance 当前节点实例对象
     * @return WfInstance 特送节点实例对象
     */
    private WfInstance genSpecialWfInstance(Map<String,WfNode> nodeMap,String aimNodeId,WfInstance wfInstance,Map<String,String> variableMap){
    	WfNode specialWfNode = (WfNode)nodeMap.get(aimNodeId);
    	WfInstance specialWfInstance = new WfInstance();
    	specialWfInstance.setInstanceId(getUuid());
    	specialWfInstance.setBusId(wfInstance.getBusId());
    	specialWfInstance.setCurNodeId(aimNodeId);
    	specialWfInstance.setCurNodeName(specialWfNode.getNodeName());
    	specialWfInstance.setFinPreNodeId(wfInstance.getCurNodeId());
    	specialWfInstance.setIsSign(WfConstants.VALUE_YESNO_NO);
    	specialWfInstance.setRecevieDate(new Date());
    	specialWfInstance.setRoleId(specialWfNode.getRoleId());
    	specialWfInstance.setTudoUserId(this.wfDbService.genUserIdsByRoleId(specialWfNode.getRoleId()));
    	specialWfInstance.setStatus(WfConstants.STATUS_SHOW);
    	specialWfInstance.setValidity(WfConstants.VALUE_YESNO_YES);
    	specialWfInstance.setWfCode(wfInstance.getWfCode());
    	specialWfInstance.setInstanceNo(wfInstance.getInstanceNo());
    	//变量处理
    	this.dealVariableInfo(variableMap,specialWfInstance);
		//时间处理
		this.dealNodeDuration(wfInstance, specialWfNode.getDurationType());
    	return specialWfInstance;
    }
    
    /**
     * 生成会签节点的流程实例对象.
     * @param nodeMap 节点MAP key：节点ID value：节点对象
     * @param wfRoute 节点路由对象
     * @param busId 业务ID
     * @return WfInstance 流程实例对象
     */
    private List<WfInstance> genSendOutWfInstances(Map<String,WfNode> nodeMap,WfRoute wfRoute,String busId,String instanceNo,String[] sendOuts){
    	WfNode curNode = (WfNode)nodeMap.get(wfRoute.getNextNodeId());
    	List<WfInstance> instanceList = new ArrayList<WfInstance>();
		//使用系统时间的long字段作为会签的标识，避免重复会签情况
    	String sendOutFlag = ""+System.currentTimeMillis();
    	for(String sendOut : sendOuts){
			WfInstance wfInstance = new WfInstance();
			wfInstance.setInstanceId(getUuid());
			wfInstance.setInstanceNo(instanceNo);
			wfInstance.setBusId(busId);
			wfInstance.setCurNodeId(wfRoute.getNextNodeId());
			wfInstance.setCurNodeName(curNode.getNodeName());
			wfInstance.setFinPreNodeId(wfRoute.getNodeId());
			wfInstance.setIsSign(WfConstants.VALUE_YESNO_NO);
			wfInstance.setRecevieDate(new Date());
			wfInstance.setRoleId(curNode.getRoleId());
			wfInstance.setSendoutDept(sendOut); 
			//使用系统时间的long字段作为会签的标识，避免重复会签情况
			wfInstance.setSendoutFlag(sendOutFlag);
			//获取过滤部门的人员信息
			String userIds = wfDbService.genUserIdsByFilter(curNode.getRoleId(), sendOut);
			if(Strings.isBlank(userIds)){
				throw new WfException(WfConstants.ERROR_NO_TODU_USER);
			}
			wfInstance.setTudoUserId(userIds);
			wfInstance.setStatus(WfConstants.STATUS_SHOW);
			wfInstance.setValidity(WfConstants.VALUE_YESNO_YES);
			wfInstance.setWfCode(curNode.getWfCode());
			//时间处理
			this.dealNodeDuration(wfInstance, curNode.getDurationType());
			instanceList.add(wfInstance);
    	}
    	return instanceList;
    }

    /**
     *  时间处理.
     */
    private void dealNodeDuration(WfInstance wfInstance,String durationType){
    	//计算类型
    	if(Strings.isBlank(durationType)){
    		wfInstance.setDurationType(null);
    		wfInstance.setDurationDate(new Date());
    	}else{
    		wfInstance.setDurationType(durationType);
    		wfInstance.setDurationDate(wfDbService.getEarlyDurationTypeDate(durationType, wfInstance.getInstanceNo()));
    	}
    	//计算总计时
    	wfInstance.setTotalDuration(wfDbService.getWfDuration(wfInstance.getInstanceNo(),wfInstance.getDurationDate()));
    }
    
    /**
     * 节点完成适配器.
     */
    private void finishItemAdaptor(String instanceNo, String userId,String finPreNodeId){
    	//设置不可回收状态
    	wfDbService.setCallbackFlag(instanceNo,"'"+finPreNodeId.replaceAll(",", "','")+"'");
    	//设置已操作人信息
    	wfDbService.setOpAllUserId(instanceNo, wfDbService.findAllWfHisInstanceByNo(instanceNo));
    	//设置计时信息
    	
    }
    
    /**
     * 流程完成适配器.
     */
    private void finishWfAdaptor(WfInstance wfInstance,String userId){
    	//设置不可回收状态
		wfInstance.setIsCallback(WfConstants.VALUE_YESNO_NO);
    	//设置已操作人信息
		wfInstance.setOpAllUserIds(userId+","+wfDbService.findAllWfHisInstanceByNo(wfInstance.getInstanceNo()));
    	//设置计时信息
    	
    }
    
    /**
     * 回收完成适配器.
     */
    private void finishCallBackAdaptor(){
    	//设置计时信息
    	
    }
    /**
     * 获取待办列表.
     * @param userId 用户ID
     */
    public List<WfInstance> findWorkItemList(String userId){
      return this.wfDbService.findWorkItemList(userId);
    }
    

    /**
     * 获取可回收列表.
     * @param userId 用户ID
     */
    public List<WfHisInstance> findCallBackList(String userId){
      return this.wfDbService.findCallBackList(userId);
    }
    

    /**
     * 获取已办信息，流程已办结.
     * @param userId 用户ID
     */
    public List<WfHisInstance> findFinishList(String userId){
      return this.wfDbService.findFinishList(userId);
    }

	/**
	 * 获取已办完列表但流程未完成.
	 * @param userId 用户ID
	 * @return
	 */
	public List<WfInstance> findDealList(String userId){
		return this.wfDbService.findDealList(userId);
	}

	/**
	 * 获取已办完事项数量(但流程未完成).
	 * @param userId 用户ID
	 * @return
	 */
	public int getDealCount(String userId){
		return wfDbService.getDealCount(userId);
	}

	/**
	 * 获取待办列表数量.
	 * @param userId 用户ID
	 * @return
	 */
	public int getWorkItemCount(String wfCode,String userId,String curNodeId){
		return wfDbService.getWorkItemCount(wfCode,userId,curNodeId);
	}

    /**
     *  生成32位的随机数作为id
     * @return  生成32位的随机数作为id
     */
    private String getUuid(){
		return  UUID.randomUUID().toString();
    }

	/**
	 * 生成提示的log信息.
	 * @param wfInstance
	 * @param info
	 * @return
	 */
	public String genWfInstanceLogInfo(WfInstance wfInstance,String info){
		return info+"["+Json.toJson(wfInstance)+"]";
	}
    
    /**
     * 根据角色获取的用户列表
     * @param roleId
     * @return
     */
    public List<WfUser> genUserListByRoleId(String roleId){
    	if(roleId.indexOf(",") != -1){
    		return wfDbService.genUserListByRoleId(roleId);
    	}else{
    		List<WfUser> wfUserList = new ArrayList<WfUser>();
    		String[] roleIds = roleId.split(",");
    		for(String tempRoleId : roleIds){
    			wfUserList.addAll(wfDbService.genUserListByRoleId(tempRoleId));
    		}
    		return wfUserList;
    	}
    }
    
    /**
     * 根据用户主键获取用户对象
     * @param userId 用户主键
     * @return
     */
	public WfUser getUserInfoById(String userId) {
		return wfDbService.getUserInfoById(userId);
	}
}
