package com.wonders.jh.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.Service;

import com.wonders.jh.constants.FlowConstants;
import com.wonders.jh.entity.serve.FlowNodeConfig;
import com.wonders.jh.entity.serve.FlowTaskInfo;
import com.wonders.jh.entity.serve.FlowWorkInfo;
import com.wonders.jh.entity.serve.FlowWorkLog;
import com.wonders.jh.exception.FlowException;



/**
 * 处理工作流与数据库交互.
 * 
 */
@IocBean(fields = "dao")
public class FlowService extends Service {

	/** 任务 seq 名 */
	public static final String TASK_SEQ_NAME = "SEQ_FLOW_TASK_INFO";

	/** 工单 seq 名 */
	public static final String WORK_SEQ_NAME = "SEQ_FLOW_WORK_INFO";
	
	/** 日志 seq 名 */
	public static final String LOG_SEQ_NAME = "SEQ_FLOW_WORK_LOG";

	/**
	 * <b>添加任务</b><br/>
	 * 1. 初始化任务，任务状态 0 -初始化 <br/>
	 * 2. 初始化工单，工单状态 0 -初始化<br/>
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-3
	 * @param businessNo
	 *            业务编码
	 * @param userId
	 *            用户ID
	 * @param flowNo
	 *            流程NO
	 * @param from
	 *            发起方
	 * @param to
	 *            接受方
	 * @return 工单ID
	 */
	public FlowWorkInfo saveTaskInfo(String businessNo, String userId, String flowNo,
			String from, String to) {

		if (StringUtils.isBlank(businessNo) || StringUtils.isBlank(userId)
				|| StringUtils.isBlank(flowNo)) {
			throw new FlowException("添加任务时，户参数丢失 !");
		}
		// 初始化 任务 状态 0
		String taksId = initTask(businessNo, userId, flowNo);

		// 初始化 工单 状态 0
		FlowWorkInfo work = saveWork(businessNo, userId, flowNo, taksId,
				"lastNodeNo", FlowConstants.NODE_START_CODE,
				FlowConstants.NODE_STATUS_INIT, from, to);
		// 记录日志
			log(businessNo, userId, flowNo, work.getNodeNo(), work.getTaskId(), work.getWorkId(), FlowConstants.NODE_STATUS_INIT, null);
				
		return work;
	}

	
	/**
	 * 提交工单 并初始化任务 <br/>
	 * 1. 初始化任务
	 * 1. 将初始工单至无效 <br/>
	 * 2. 新增工单，工单 状态 3-提交 <br/>
	 * 3. 创建工单，工单 状态 1-签收 <br/>
	 * 4. 更新任务，任务 状态 1-执行中 <br/>
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-3
	 * 
	 * @param businessNo
	 *            业务编码
	 * @param userId
	 *            用户ID
	 * @param workId
	 *            流程NO
	 * @return 工单ID
	 */
	public String submitWork(String businessNo, String userId,String workId, String flowNo,String from,String to,String notes) {
		if (StringUtils.isBlank(businessNo) || StringUtils.isBlank(userId)) {
			throw new FlowException("添加任务时，户参数丢失 !");
		}
		
		Dao dao = dao();
		FlowWorkInfo work = null;
		// 是否已存在工单
		if (StringUtils.isNotBlank(workId)) {
			work = dao.fetch(FlowWorkInfo.class, workId);
		}
		if (null == work) {
			// 初始化任务
			work = saveTaskInfo(businessNo, userId, flowNo, from, to);
		}
		if (null == work) {
			throw new FlowException("创建任务信息异常 !");
		}

		// 提交工单 主要逻辑在里面
		FlowWorkInfo newWork = submitWork(work, userId, businessNo,notes);
		
		return newWork == null ? null : newWork.getWorkId();
	}
	
	/**
	 * 提交任务 <br/>
	 * 1. 讲初始工单至无效 <br/>
	 * 2. 新增工单，工单 状态 3-提交 <br/>
	 * 3. 创建工单，工单 状态 1-签收 <br/>
	 * 4. 更新任务，任务 状态 1-执行中 <br/>
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-3
	 * 
	 * @param businessNo
	 *            业务编码
	 * @param userId
	 *            用户ID
	 * @param workId
	 *            流程NO
	 * @return 工单ID
	 */
	public String submitTask(String businessNo, String userId, String workId,String notes) {
		if (StringUtils.isBlank(businessNo) || StringUtils.isBlank(userId)
				|| StringUtils.isBlank(workId)) {
			throw new FlowException("添加任务时，户参数丢失 !");
		}
		
		Dao dao = dao();
		// 获取工单信息
		FlowWorkInfo work = dao.fetch(FlowWorkInfo.class, workId);
		if (null == work) {
			throw new FlowException("获取工单信息异常 !");
		}
		// 提交工单 主要逻辑在里面
		FlowWorkInfo newWork = submitWork(work, userId, businessNo,notes);

		return newWork == null ? null : newWork.getWorkId();
	}


	/**
	 * 提交 工单
	 * 1. 讲初始工单至无效 <br/>
	 * 2. 新增工单，工单 状态 3-提交 <br/>
	 * 3. 创建工单，工单 状态 1-签收 <br/>
	 * 4. 更新任务，任务 状态 1-执行中 <br/>
	 * @author Xixi Luo
	 * @date 2015-11-10
	 * @param work
	 * @param userId
	 * @param businessNo
	 * @param dao
	 * @return
	 */
	private FlowWorkInfo submitWork(FlowWorkInfo work, String userId,
			String businessNo,String notes) {
		Dao dao = dao();
		// 
		// 工单 状态提交
		work.setNodeStatusCode(FlowConstants.NODE_STATUS_SBM);
		dao.update(work);

		/*saveWork(businessNo, userId, work.getFlowNo(), work.getTaskId(),
				"nodeNo", work.getNodeNo(), FlowConstants.NODE_STATUS_SBM,
				work.getWorkFrom(), work.getWorkTo());*/

		// 记录 提交 日志
		log(businessNo, userId, work.getFlowNo(), work.getNodeNo(), work.getTaskId(), work.getWorkId(), FlowConstants.NODE_STATUS_SBM, notes);
		
		// 
		
		// 是否存在已提交过的流程 
		// 获取下一环节的 
		FlowNodeConfig nodeConfig = dao.fetch(FlowNodeConfig.class, work.getNodeNo());
		if (null == nodeConfig) {
			throw new FlowException("获取环节信息失败 !");
		}
		FlowWorkInfo newWork = null ;
		newWork = getWork(work.getTaskId(),nodeConfig.getNextNodeNo(),businessNo);
		if (null == newWork) {
			// 新建工单信息
			// 工单 状态 签收
			newWork = saveWork(businessNo, userId, work.getFlowNo(),
					work.getTaskId(), "lastNodeNo", work.getNodeNo(),
					FlowConstants.NODE_STATUS_SIGN, work.getWorkFrom(),
					work.getWorkTo());
		}else{
			newWork.setNodeStatusCode(FlowConstants.NODE_STATUS_SIGN);
			dao.update(newWork);
		}

		// 修改任务状态 执行中
		FlowTaskInfo task = dao.fetch(FlowTaskInfo.class, work.getTaskId());
		task.setTaskStatus(FlowConstants.TASK_STATUS_RUN);
		dao.update(task);
		

		// 记录 签收 日志 
		log(businessNo, userId, newWork.getFlowNo(), newWork.getNodeNo()
				, newWork.getTaskId(), newWork.getWorkId()
				, FlowConstants.NODE_STATUS_SIGN, null);
		
		return newWork;
	}

	/**
	 * 退回工单 <br/>
	 * 1. 更新工单，工单 状态 8-取消 <br/>
	 * 2. 创建工单，工单 状态 5-退回 <br/>
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-3
	 * 
	 * @param businessNo
	 *            业务编码
	 * @param userId
	 *            用户ID
	 * @param workId
	 *            工单NO
	 * @return 工单ID
	 */
	public String rollbackTask(String businessNo, String userId, String workId,String notes) {
		if (StringUtils.isBlank(businessNo) || StringUtils.isBlank(userId)
				|| StringUtils.isBlank(workId)) {
			throw new FlowException("添加任务时，户参数丢失 !");
		}
		Dao dao = dao();

		// 获取工单信息
		FlowWorkInfo work = dao.fetch(FlowWorkInfo.class, workId);
		if (null == work) {
			throw new FlowException("获取工单信息异常 !");
		}
		
		// 将工单状态改完已退回
		work.setNodeStatusCode(FlowConstants.NODE_STATUS_TOBACK);
		dao.update(work);
		
		
		// 获取上一环节的工单 
		FlowNodeConfig nodeConfig = dao.fetch(FlowNodeConfig.class, work.getNodeNo());
		if (null == nodeConfig) {
			throw new FlowException("获取环节信息失败 !");
		}
		FlowWorkInfo lastWork = getWork(work.getTaskId(),nodeConfig.getLastNodeNo(),businessNo);
		if (null == lastWork) {
			throw new FlowException("获取工单信息失败 !");
		}
		lastWork.setNodeStatusCode(FlowConstants.NODE_STATUS_BACK); // 退回状态
		dao.update(lastWork);
		
		/*// 新建工单信息
		// 工单 状态 已退回
		saveWork(businessNo, userId, work.getFlowNo(), work.getTaskId(),
				"nestNodeNo", work.getNodeNo(),
				FlowConstants.NODE_STATUS_TOBACK, work.getWorkTo(),
				work.getWorkFrom());
		
		// 新建工单信息
		// 工单 状态 退回
		FlowWorkInfo newWork = saveWork(businessNo, userId, work.getFlowNo(),
				work.getTaskId(), "nestNodeNo", work.getNodeNo(),
				FlowConstants.NODE_STATUS_BACK, work.getWorkTo(),
				work.getWorkFrom());
		 */
		
		// 记录退回日志
		log(businessNo, userId, work.getFlowNo(), work.getNodeNo()
				, work.getTaskId(), work.getWorkId()
				, FlowConstants.NODE_STATUS_TOBACK, notes);
		
		return null;
	}

	/**
	 * 审核  服务
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-9
	 * @param businessNo
	 * @param userId
	 * @param workId
	 * @return
	 */
	public String checkServe(String businessNo, String userId,
			String workId,String flowNo,String notes) {
		if (StringUtils.isBlank(businessNo) || StringUtils.isBlank(userId)
				|| StringUtils.isBlank(workId)) {
			throw new FlowException("添加任务时，户参数丢失 !");
		}
		Dao dao = dao();
		
		// 获取工单信息
		FlowWorkInfo work = dao.fetch(FlowWorkInfo.class, workId);
		if (null == work) {
			throw new FlowException("获取工单信息异常 !");
		}
		// 修改工单信息 状态  完成
		work.setNodeStatusCode(FlowConstants.NODE_STATUS_FINISH);
		dao.update(work);
		

		// 获取上一环节的工单 修改状态为已完成  若有多个环节  应修改所有环节的状态
		// 获取上一环节的 工单
		FlowNodeConfig nodeConfig = dao.fetch(FlowNodeConfig.class, work.getNodeNo());
		if (null == nodeConfig) {
			throw new FlowException("获取环节信息失败 !");
		}
		FlowWorkInfo lastWork = getWork(work.getTaskId(),nodeConfig.getLastNodeNo(),businessNo);
		if (null == lastWork) {
			throw new FlowException("获取工单信息失败 !");
		}
		lastWork.setNodeStatusCode(FlowConstants.NODE_STATUS_FINISH);
		dao.update(lastWork);
		
/*
		// 新建工单信息
		// 工单 状态 审核完成
		FlowWorkInfo newWork = saveWork(businessNo, userId, flowNo,
				work.getTaskId(), "nodeNo", work.getNodeNo(),
				FlowConstants.NODE_STATUS_FINISH, work.getWorkFrom(),
				work.getWorkTo());*/

		// 修改任务状态 完成
		FlowTaskInfo task = dao.fetch(FlowTaskInfo.class, work.getTaskId());
		task.setTaskStatus(FlowConstants.TASK_STATUS_FINISH);
		dao.update(task);

		// 记录退完成日志
		log(businessNo, userId, work.getFlowNo(), work.getNodeNo()
				, work.getTaskId(), work.getWorkId()
				, FlowConstants.NODE_STATUS_FINISH, notes);
		
		return null;
	}
	
	/**
	 * 根据环节NO 获取工单信息
	 * @author Xixi Luo
	 * @date 2015-11-11
	 * @param nodeNo
	 * @return
	 */
	private FlowWorkInfo getWork(String taskId,String nodeNo,String businessNo) {
		// 获取环节信息
		FlowWorkInfo work = dao().fetch(
				FlowWorkInfo.class,
				Cnd.where("nodeNo", "=", nodeNo)
						.and("taskId", "=", taskId)
						.and("businessNo", "=", businessNo)
						.and("isValid", "=", FlowConstants.VALID_YES));
		return work;
	}

	/**
	 * 创建 工单
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-3
	 * @param businessNo
	 * @param userId
	 * @param flowNo
	 * @param taksId
	 * @return
	 */
	private FlowWorkInfo saveWork(String businessNo, String userId, String flowNo,
			String taksId, String nodeKey, String nodeCode, String workStatus,
			String from, String to) {

		if (StringUtils.isBlank(businessNo) || StringUtils.isBlank(userId)
				|| StringUtils.isBlank(flowNo) || StringUtils.isBlank(taksId)) {
			throw new FlowException("初始化工单参数异常 !");
		}
		// dao 对象
		Dao dao = dao();
		Integer seqWork = this.getSeq(WORK_SEQ_NAME);
		if (null == seqWork) {
			throw new FlowException("获取工单序列异常 !");
		}

		// 获取环节信息
		FlowNodeConfig nodeConfig = dao.fetch(
				FlowNodeConfig.class,
				Cnd.where("flowNo", "=", flowNo)
						.and("isValid", "=", FlowConstants.VALID_YES)
						.and(nodeKey, "=", nodeCode));

		if (null == nodeConfig || StringUtils.isBlank(nodeConfig.getNodeNo())) {
			throw new FlowException("根据流程NO[ " + flowNo + " ]获取环节信息异常 !");
		}
		// 构造工单信息
		FlowWorkInfo work = new FlowWorkInfo(seqWork + "", taksId, flowNo,
				nodeConfig.getNodeNo(), businessNo, workStatus, userId,
				new Date(), FlowConstants.VALID_YES, from, to);
		// 保存
		dao.insert(work);
		
		return work;
	}

	/**
	 * 初始化 任务
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-3
	 * @param businessNo
	 * @param userId
	 * @param flowNo
	 * @return
	 */
	private String initTask(String businessNo, String userId, String flowNo) {

		if (StringUtils.isBlank(businessNo) || StringUtils.isBlank(userId)
				|| StringUtils.isBlank(flowNo)) {
			throw new FlowException("初始化任务参数异常 !");
		}
		String taksId = null;
		// dao 对象
		Dao dao = dao();

		// 获取序列
		Integer seqTask = this.getSeq(TASK_SEQ_NAME);
		if (null == seqTask) {
			throw new FlowException("获取任务序列异常 !");
		}
		Date nowDate = new Date();

		// 添加任务
		FlowTaskInfo task = new FlowTaskInfo(seqTask + "", flowNo, businessNo,
				nowDate, null, FlowConstants.TASK_STATUS_INIT, userId, null,
				nowDate, FlowConstants.VALID_YES);
		// 保存
		dao.insert(task);

		taksId = task.getTaskId();
		
		return taksId;
	}

	/**
	 * 获取 序列
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-3
	 * @param seqName
	 *            序列名称
	 * @return
	 */
	public Integer getSeq(String seqName) {
		if (StringUtils.isBlank(seqName)) {
			return null;
		}
		Sql sql = Sqls.create("select " + seqName + ".nextval from dual");
		sql.setCallback(Sqls.callback.integer());
		dao().execute(sql);

		return sql.getInt();
	}

	/**
	 * 记录日志
	 * @author Xixi Luo
	 * @date 2015-11-11
	 * @param businessNo
	 * @param userId
	 * @param flowNo
	 * @param nodeNo
	 * @param taskId
	 * @param workId
	 * @param status
	 * @param notes
	 */
	public void log(String businessNo, String userId, String flowNo
,
			String nodeNo, String taskId, String workId, String status,
			String notes) {

		// 参数判断
		if (StringUtils.isBlank(businessNo) ||StringUtils.isBlank(userId) ||
				StringUtils.isBlank(flowNo) ||StringUtils.isBlank(nodeNo) ||
				StringUtils.isBlank(taskId) ||StringUtils.isBlank(workId) ||
				StringUtils.isBlank(status)) {
			throw new FlowException("保存日志信息异常 !");
		}
		Date date = new Date();
		// 序列 ID
		int logId = this.getSeq(LOG_SEQ_NAME);
		FlowWorkLog log = new FlowWorkLog(logId+"",workId, taskId, flowNo, nodeNo,
				businessNo, status, notes, date, userId, date,
				FlowConstants.VALID_YES);
		// 持久化
		dao().insert(log);
	}
}
