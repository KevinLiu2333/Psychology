package com.wonders.jh.at;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.EntityService;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.wonders.Constants;
import com.wonders.jh.constants.FlowConstants;
import com.wonders.jh.entity.serve.FlowTaskInfo;
import com.wonders.jh.entity.serve.FlowWorkInfo;
import com.wonders.jh.entity.serve.FlowWorkLog;
import com.wonders.jh.entity.serve.ServeBusinessFieldDetail;
import com.wonders.jh.entity.serve.ServeBusinessInfo;
import com.wonders.jh.entity.serve.ServeDeptIp;
import com.wonders.jh.exception.FlowException;
import com.wonders.jh.service.FlowService;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.ui.UI;
import com.wonders.tiles.ui.ZymlUI;
import com.wonders.tiles.ui.UI.StatusCode;
import com.wonders.utils.PropertyUtils;
import com.wonders.utils.UserUtils;




@IocBean(fields = "dao")
@At("/serve")
public class ServeAt extends EntityService<Object> {

	@Inject("refer:flowService")
	private FlowService flowService;

	/**
	 * 跳转至文件注册服务页面
	 */
	@At("/toRegServe")
	@Ok("jsp:jsp.jh.serve.reg_serve")
	public Map<String, Object> toRegServe(@Param("serveId") String serveId,
			@Param("workId") String workId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();

		// 修改
		if (StringUtils.isNotBlank(serveId)) {
			ServeBusinessInfo serve = null;
			serve = dao().fetch(ServeBusinessInfo.class, serveId);
			Cnd cnd = Cnd.where("serveId", "=", serveId);
			cnd.asc("fieldOrder");
			List<ServeBusinessFieldDetail> fieldList = dao().query(ServeBusinessFieldDetail.class
					, cnd);
			result.put("serve", serve);
			if (null != fieldList && !fieldList.isEmpty()) {
				result.put("fieldList", fieldList);
				result.put("fieldListSize", fieldList.size());
			}
			result.put("workId", workId);
		}

		return result;
	}

	/**
	 * 跳转至文件注册服务详情页面
	 */
	@At("/viewServe")
	@Ok("jsp:jsp.jh.serve.serve_detail")
	public Map<String, Object> viewServe(@Param("serveId") String serveId,
			@Param("workId") String workId, @Param("showKey") String showKey,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		//
		if (StringUtils.isBlank(serveId)) {
			return result;
		}
		ServeBusinessInfo serve = null;
		serve = dao().fetch(ServeBusinessInfo.class, serveId);
		if (null == serve) {
			return result;
		}
		
		// 获取部门编码
		String deptCode = getDeptCode(request);
		
		// 字段信息
		Cnd cnd = Cnd.where("serveId", "=", serveId);
		cnd.asc("fieldOrder");
		List<ServeBusinessFieldDetail> fieldList = dao().query(
				ServeBusinessFieldDetail.class, cnd);
		if (null != fieldList && !fieldList.isEmpty()) {
			result.put("fieldList", fieldList);
			result.put("fieldListSize", fieldList.size());
		}
		result.put("workId", workId);
		// 授权 key 信息
		if ("1".equals(showKey)) {
			ServeDeptIp serveDept = dao().fetch(
					ServeDeptIp.class,
					Cnd.where("serveId", "=", serveId)
							.and("deptNo", "=", deptCode)
							.and("isPass", "=", FlowConstants.PASS_YES)
							.and("isValid", "=", FlowConstants.VALID_YES));
			if (null != serveDept) {
				result.put("key", serveDept.getClientKey());
			} else {
				result.put("key", "审核通过，方可查看。");
			}
		}
		result.put("serve", serve);
		result.put("deptNo", deptCode);

		// 流程日志信息
		// 工单信息
		if (StringUtils.isBlank(workId)) {
			return result;
		}
		FlowWorkInfo work = dao().fetch(FlowWorkInfo.class, workId);
		if (null == work) {
			return result;
		}		
		
		// 日志信息
		Cnd logCnd = Cnd.where("taskId", "=", work.getTaskId())
				.and("nodeStatusCode", "<>", FlowConstants.NODE_STATUS_INIT)
				.and("nodeStatusCode", "<>", FlowConstants.NODE_STATUS_SIGN);
		cnd.asc("logId");
		List<FlowWorkLog> logs = dao().query(FlowWorkLog.class, logCnd);
		if (null == logs) {
			return result;
		}
		// 转换用户信息
		for (FlowWorkLog flowWorkLog : logs) {
				flowWorkLog.setCreateBy(UserUtils.getName(request));
		}
		result.put("flowLogs", logs);
		result.put("flowNo", work.getFlowNo());
		System.out.println(work.getTaskId());
		return result;
	}

	/**
	 * 删除 发布的服务
	 */
	@At
	@Ok("json")
	public Map<String, Object> deleteServe(
			@Param("serveId") final String serveId,
			@Param("workId") final String workId, HttpServletRequest request) {

		// 输入参数不能是空
		if (StringUtils.isBlank(serveId) || StringUtils.isBlank(workId)) {
			return UI.ajaxDone(StatusCode.FAIL);
			// ZymlUI.divSearch(StatusCode.valueOf("参数异常！"),"ajaxDiv",ZymlUI.CallbackType.CLOSE_CURENT);
		}

		// 控制事物
		Trans.exec(new Atom() {
			@Override
			// 事物处理
			public void run() {

				// 删除业务信息
				dao().delete(ServeBusinessInfo.class, serveId);
				dao().clear(ServeBusinessInfo.class,
						Cnd.where("serveId", "=", serveId));

				FlowWorkInfo work = dao().fetch(FlowWorkInfo.class, workId);
				//
				// 将流程信息设置成无效
				FlowTaskInfo task = dao().fetch(FlowTaskInfo.class,
						work.getTaskId());
				task.setIsValid(FlowConstants.VALID_NO);
				dao().update(task);
			}
		});
		return ZymlUI.divSearch(StatusCode.OK, "成功删除！", "ajaxDiv");
	}

	/**
	 * 注册 服务
	 */
	@At("/regServe")
	@Ok("json")
	public Map<String, Object> regServe(
			@Param("::serve.") final ServeBusinessInfo serve,
			HttpServletRequest request) {

		// 输入参数不能是空
		if (null == serve) {
			return UI.ajaxDone(StatusCode.FAIL);
			// ZymlUI.divSearch(StatusCode.valueOf("参数异常！"),"ajaxDiv",ZymlUI.CallbackType.CLOSE_CURENT);
		}

		// 获取部门 与用户信息
		if (StringUtils.isBlank(UserUtils.getOrganId(request))) {
			return UI.ajaxDone(StatusCode.FAIL);
		}
		String organId = UserUtils.getOrganId(request);
		// 根据用户获取部门代码
		final String deptCode = DicDataUtils.getInstance().getDicData(
				Constants.DIC_TEAM_ID, organId);

		final String userId = UserUtils.getUserId(request);
		if (StringUtils.isBlank(deptCode)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		// 获取字段明细信息
		final List<ServeBusinessFieldDetail> detailList = getDetail(request);
		// 获取删除的字段明细ID
		String ids = request.getParameter("delFieldids");
		// 需要删除的id集合
		final List<String> delFieldids = new ArrayList<String>();
		if (StringUtils.isNotBlank(ids)){
			String[] array = null;
			array = ids.split(",");
			for (String id : array) {
				delFieldids.add(id);
			}
		}
		
		// 控制事物
		Trans.exec(new Atom() {
			@Override
			// 事物处理
			public void run() {
				// 标记 是新增还是修改
				boolean isAdd = false;
				String id = serve.getServeId();
				// 保存 服务
				ServeBusinessInfo newServe = null;
				// 修改
				if (StringUtils.isNotBlank(id)) {
					newServe = dao().fetch(ServeBusinessInfo.class, id);
				}
				// 若没有找到可修改的对象 则保存
				if (null == newServe) {
					newServe = new ServeBusinessInfo();
					int seq = flowService.getSeq("SEQ_SERVE_BUSINESS_INFO");
					id = deptCode + "_" + seq;
					newServe.setServeId(id);
					isAdd = true;
					newServe.setServePublic(FlowConstants.PUBLIC_NO);// 未发布状态
				}

				newServe.setServeDept(deptCode);
				newServe.setServeDb(serve.getServeDb());
				newServe.setServeIntMethod(serve.getServeIntMethod());
				newServe.setServeBusinessMethod(serve.getServeBusinessMethod());
				newServe.setServeIntParamIn(serve.getServeIntParamIn());
				newServe.setServeIntParamOut(serve.getServeIntParamOut());
				newServe.setServeIntType(serve.getServeIntType());
				newServe.setServeIntUrl(serve.getServeIntUrl());
				newServe.setServeName(serve.getServeName());
				newServe.setServeNotes(serve.getServeNotes());
				newServe.setServePublic(serve.getServePublic());
				newServe.setServeRetunType(serve.getServeRetunType());
				newServe.setServeSwitch(serve.getServeSwitch());
				newServe.setServeType(serve.getServeType());
				newServe.setServeEncrypt(serve.getServeEncrypt());
				
				// 添加字段明细信息
				if (null != detailList) {
					for (ServeBusinessFieldDetail detail : detailList) {
						if (null == detail) {
							continue;
						}
						// id 不存在则新增 否则修改
						detail.setServeId(newServe.getServeId());
						if (StringUtils.isBlank(detail.getFieldId())) {
							int seq = flowService.getSeq("SEQ_SERVE_B_FIELD_DETAIL");
							detail.setFieldId(seq+"");
							dao().insert(detail);
						} else {
							dao().update(detail);
						}
					}
				}
				// 删除字段明细信息
				for (String detailId : delFieldids) {
					dao().delete(ServeBusinessFieldDetail.class, detailId);
				}
				
				// 初始化流程 修改时不需要初始
				if (isAdd) {
					dao().insert(newServe);
					// 获取服务发布审核部门
					String checkDept = PropertyUtils
							.getProperty("serve.public.dept");

					FlowWorkInfo work = flowService.saveTaskInfo(newServe.getServeId(), userId,FlowConstants.FLOW_PUBLISH_SERVE, deptCode,checkDept);
				} else {
					dao().update(newServe);
				}
				// 提交流程
				// flowService.submitTask(serve.getServeId(), userId, workId);
			}
		});

		return ZymlUI.divSearch(StatusCode.OK, "ajaxDiv",
				ZymlUI.CallbackType.CLOSE_CURENT);
	}

	/**
	 * 获取明细信息
	 * @author Xixi Luo
	 * @date 2015-11-25
	 * @param request
	 * @return
	 */
	private List<ServeBusinessFieldDetail> getDetail(HttpServletRequest request) {
		// 获取字段信息
		String maxIndex = request.getParameter("optionMaxIndex");
		if (StringUtils.isBlank(maxIndex)) {
			return null;
		}
		int max = Integer.parseInt(maxIndex);
		
		List<ServeBusinessFieldDetail> detailList = new ArrayList<ServeBusinessFieldDetail>();
		for (int i = 0; i <= max; i++) {
			String fieldId = request.getParameter("fieldId_" + i);
			String fieldCode = request.getParameter("fieldCode_" + i);
			String fieldName = request.getParameter("fieldName_" + i);
			String fieldDesc = request.getParameter("fieldDesc_" + i);
			String fieldType = request.getParameter("fieldType_" + i);
			String fieldLength = request.getParameter("fieldLength_" + i);
			String fieldOrder = request.getParameter("fieldOrder_" + i);

			if (StringUtils.isBlank(fieldCode)) {
				continue;
			}
			Long leng = null;
			Long order = null;
			try {
				leng = Long.parseLong(fieldLength); // 长度
			} catch (NumberFormatException e) {
			}
			try {
				order = Long.parseLong(fieldOrder); // 循序
			} catch (NumberFormatException e) {
			}
			ServeBusinessFieldDetail detail = new ServeBusinessFieldDetail(
					fieldId, null, fieldCode, fieldName, fieldDesc, fieldType,
					leng, order);
			detailList.add(detail);
		}
		return detailList;
	}

	/**
	 * 提交 注册服务
	 */
	@At("/sbmServe")
	@Ok("json")
	public Map<String, Object> sbmServe(@Param("serveId") final String serveId,
			@Param("workId") final String workId,
			@Param("notes") final String notes, HttpServletRequest request) {

		// 输入参数不能是空
		if (StringUtils.isBlank(serveId) || StringUtils.isBlank(workId)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		// 获取部门 与用户信息
		if (StringUtils.isBlank(UserUtils.getOrganId(request))) {
			return UI.ajaxDone(StatusCode.FAIL);
		}
		String organId = UserUtils.getOrganId(request);
		// 根据用户获取部门代码
		final String deptCode = DicDataUtils.getInstance().getDicData(
				Constants.DIC_TEAM_ID, organId);

		final String userId = UserUtils.getUserId(request);
		if (StringUtils.isBlank(deptCode)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		// 控制事物
		Trans.exec(new Atom() {
			@Override
			public void run() {
				// 事物处理
				// 提交流程
				flowService.submitTask(serveId, userId, workId, notes);
			}
		});

		return ZymlUI.divSearch(StatusCode.OK, "成功提交！", "ajaxDiv",
				ZymlUI.CallbackType.CLOSE_CURENT);
	}

	/**
	 * 注册的服务列表页面
	 */
	@At("/regServeList")
	@Ok("jsp:jsp.jh.serve.reg_serve_list")
	public Map<String, Object> regServeList(Criteria cri, Pager pager,
			HttpServletRequest request) {
		//System.out.println(cri);
		// 获取部门编码
		String deptCode = getDeptCode(request);
		//if (StringUtils.isBlank(deptCode)) {
		//	return null;
		//}
		
		
		// 查询注册的服务 数据
		StringBuffer datasql = new StringBuffer("SELECT B.*,W.WORK_ID,W.NODE_STATUS_CODE,T.TASK_STATUS "
				+ "  FROM SERVE_BUSINESS_INFO B, FLOW_WORK_INFO W , FLOW_TASK_INFO T"
				+ " WHERE W.BUSINESS_NO = B.SERVE_ID AND W.TASK_ID = T.TASK_ID "
				+ "   AND W.FLOW_NO = '"
				+ FlowConstants.FLOW_PUBLISH_SERVE
				+ "' "
				+ "   AND W.NODE_NO = '"
				+ FlowConstants.NODE_PUBLISH_SERVE_1
				+ "' "
				+ "	 AND W.IS_VALID= '"
				+ FlowConstants.VALID_YES
				+ "'"
				+ "	 AND T.IS_VALID= '"
				+ FlowConstants.VALID_YES
				+ "'"
				+ "	 AND W.WORK_FROM = '" + deptCode + "'"
				+ " order by b.create_date desc"
				);
		
		
		String serveName = request.getParameter("filter_str_serveName_like");
		
		String serveDept = request.getParameter("filter_str_serveDept_eq");
		
		String serveDb = request.getParameter("filter_str_serveDb_eq");
		
		String status = request.getParameter("filter_str_status_eq");
		
		if (!StringUtils.isBlank(serveDept)) {
			datasql.append(" AND B.SERVE_DEPT = '" +serveDept +"'");
		}
		if (!StringUtils.isBlank(serveDb)) {
			datasql.append(" AND B.SERVE_DB = '" +serveDb +"'");
		}
		if (!StringUtils.isBlank(status)) {
			datasql.append(" AND W.NODE_STATUS_CODE = '" +status +"'");
		}
		// 获取 查询结果信息
		Map<String, Object> result = getResultBySql(pager, datasql.toString(),serveName);
		return result;
	}

	/**
	 * 审核并发布服务
	 */
	@At("/pulishServe")
	@Ok("json")
	public Map<String, Object> pulishServe(
			@Param("serveId") final String serveId,
			@Param("workId") final String workId,
			@Param("notes") final String notes, Criteria cri, Pager pager,
			HttpServletRequest request) {

		// 输入参数不能是空
		if (StringUtils.isBlank(serveId) || StringUtils.isBlank(workId)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		// 获取部门 与用户信息
		if (StringUtils.isBlank(UserUtils.getUserId(request))) {
			return UI.ajaxDone(StatusCode.FAIL);
		}
		final String userId = UserUtils.getUserId(request);
		// 控制事物
		Trans.exec(new Atom() {
			@Override
			// 事物处理
			public void run() {

				// 修改业务信息 状态 已发布
				ServeBusinessInfo serve = dao().fetch(ServeBusinessInfo.class,
						serveId);
				serve.setServePublic(FlowConstants.PUBLIC_YES);
				serve.setServePublicDate(new Date());
				dao().update(serve);

				// 提交流程
				flowService.checkServe(serveId, userId, workId,
						FlowConstants.FLOW_PUBLISH_SERVE, notes);
			}
		});

		return ZymlUI.divSearch(StatusCode.OK, "成功通过！", "ajaxDiv");
	}

	/**
	 * 发布的服务列表
	 */
	@At("/pulishServeList")
	@Ok("jsp:jsp.jh.serve.pulish_serve_list")
	public Map<String, Object> pulishServeList(Criteria cri, Pager pager,
			HttpServletRequest request) {

		// 获取部门编码
		String deptCode = getDeptCode(request);

		// 查询审核的服务 数据
		StringBuffer datasql = new StringBuffer("SELECT B.*,W.WORK_ID,W.NODE_STATUS_CODE ,T.TASK_STATUS  "
				+ "  FROM SERVE_BUSINESS_INFO B, FLOW_WORK_INFO W,FLOW_TASK_INFO T "
				+ " WHERE W.BUSINESS_NO = B.SERVE_ID "
				+ "   AND T.BUSINESS_NO = B.SERVE_ID"
				+ "   AND W.FLOW_NO = T.FLOW_NO "
				+ "   AND T.FLOW_NO = '"
				+ FlowConstants.FLOW_PUBLISH_SERVE
				+ "' "
				+ "   AND W.NODE_NO = '"
				+ FlowConstants.NODE_PUBLISH_SERVE_2
				+ "' "
				+ "	 AND W.IS_VALID= '"
				+ FlowConstants.VALID_YES
				+ "'"
				+ "	 AND T.IS_VALID= '"
				+ FlowConstants.VALID_YES
				+ "'"
				+ "	 AND W.WORK_TO = '" + deptCode + "'"
				+ " order by b.create_date desc"
				);

		String serveName = request.getParameter("filter_str_serveName_like");
		
		String serveDept = request.getParameter("filter_str_serveDept_eq");
		
		String serveDb = request.getParameter("filter_str_serveDb_eq");
		
		String status = request.getParameter("filter_str_status_eq");
		
		if (!StringUtils.isBlank(serveDept)) {
			datasql.append(" AND B.SERVE_DEPT = '" +serveDept +"'");
		}
		if (!StringUtils.isBlank(serveDb)) {
			datasql.append(" AND B.SERVE_DB = '" +serveDb +"'");
		}
		if (!StringUtils.isBlank(status)) {
			if ("0".equals(status)) {  //  已发布 
				datasql.append(" AND W.NODE_STATUS_CODE is null ");
			} else {
				datasql.append(" AND W.NODE_STATUS_CODE = '" +status +"'");
			}
		}
		
		// 获取 查询结果信息
		Map<String, Object> result = getResultBySql(pager, datasql.toString(),serveName);

		return result;
	}
	
	/**
	 * 发布的服务列表
	 */
	@At("/pulishServeListByDept")
	@Ok("jsp:jsp.jh.serve.pulish_serve_list_by_dept")
	public Map<String, Object> pulishServeListByDpet(Criteria cri, Pager pager,
			HttpServletRequest request) {
		
		// 获取部门编码
		//String deptCode = getDeptCode(request);
		
		// 查询审核的服务 数据
		StringBuffer datasql = new StringBuffer("SELECT B.*,W.WORK_ID,W.NODE_STATUS_CODE ,T.TASK_STATUS  "
				+ "  FROM SERVE_BUSINESS_INFO B, FLOW_WORK_INFO W,FLOW_TASK_INFO T "
				+ " WHERE W.BUSINESS_NO = B.SERVE_ID "
				+ "   AND T.BUSINESS_NO = B.SERVE_ID"
				+ "   AND W.FLOW_NO = T.FLOW_NO "
				+ "   AND T.FLOW_NO = '"
				+ FlowConstants.FLOW_PUBLISH_SERVE
				+ "' "
				+ "   AND W.NODE_NO = '"
				+ FlowConstants.NODE_PUBLISH_SERVE_2
				+ "' "
				+ "	 AND W.IS_VALID= '"
				+ FlowConstants.VALID_YES
				+ "'"
				+ "	 AND T.IS_VALID= '"
				+ FlowConstants.VALID_YES
				+ "'" 
				//+ "	 AND W.WORK_TO = '" + deptCode + "'"
				//+ 
				);
		
		String serveName = request.getParameter("filter_str_serveName_like");
		
		String serveDept = request.getParameter("filter_str_serveDept_eq");
		
		if (!StringUtils.isBlank(serveDept)) {
			datasql.append(" AND B.SERVE_DEPT = '" +serveDept +"'");
		}
		datasql.append(" order by b.SERVE_PUBLIC_DATE desc");
		// 获取 查询结果信息
		Map<String, Object> result = getResultBySql(pager, datasql.toString(),serveName);
		
		return result;
	}

	/**
	 * 申请服务
	 */
	@At("/applyServe")
	@Ok("json")
	public Map<String, Object> applyServe(
			@Param("serveId") final String serveId,
			@Param("serveIp") final String serveIp,
			@Param("workId") final String workId,
			@Param("notes") final String notes, HttpServletRequest request) {

		// 输入参数不能是空
		if (StringUtils.isBlank(serveId)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		// 获取部门 与用户信息
		if (StringUtils.isBlank(UserUtils.getOrganId(request))) {
			return UI.ajaxDone(StatusCode.FAIL);
		}
		String organId = UserUtils.getOrganId(request);
		// 根据用户获取部门代码
		final String deptCode = DicDataUtils.getInstance().getDicData(
				Constants.DIC_TEAM_ID, organId);

		final String userId = UserUtils.getUserId(request);
		if (StringUtils.isBlank(deptCode)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}
		

		// 控制事物
		Trans.exec(new Atom() {
			@Override
			// 事物处理
			public void run() {

				int seq = flowService.getSeq("seq_serve_dept_ip");
				
				// 保存客户端IP申请信息
				ServeDeptIp serveDept = new ServeDeptIp(seq + "",deptCode, serveId,
						serveIp, FlowConstants.PASS_NO, FlowConstants.VALID_YES,null,null,new Date(),null);
				dao().insert(serveDept);
				
				// 获取业务信息
				ServeBusinessInfo serve = dao().fetch(ServeBusinessInfo.class,
						serveId);
				// 提交 工单，
				flowService.submitWork(serve.getServeId(), userId, workId,
						FlowConstants.FLOW_APPLY_SERVE, deptCode,
						serve.getServeDept(), notes);
			}
		});

		return ZymlUI.divSearch(StatusCode.OK, "成功申请！", "ajaxDiv");
	}

	/**
	 * 申请 服务列表
	 */
	@At("/applyServeList")
	@Ok("jsp:jsp.jh.serve.apply_serve_list")
	public Map<String, Object> applyServeList(Criteria cri, Pager pager,
			HttpServletRequest request) {
		// 获取部门编码
		String deptCode = getDeptCode(request);

		// 查询发布成功的服务 数据
		StringBuffer datasql = new StringBuffer("SELECT B.*,T.WORK_ID, T.TASK_STATUS, T.NODE_STATUS_CODE "
				+ "  FROM SERVE_BUSINESS_INFO B "
				+ "  LEFT JOIN (SELECT T1.business_NO ,T1.TASK_STATUS, W1.NODE_STATUS_CODE,W1.WORK_ID" // 逻辑需要什么字段就加什么字段
				+ "               FROM FLOW_TASK_INFO T1,FLOW_WORK_INFO W1"
				+ "              WHERE T1.task_id = w1.task_id "
				+ "				  AND w1.NODE_NO = '"
				+ FlowConstants.NODE_APPLY_SERVE_1
				+ "' "
				+ "				  AND t1.FLOW_NO = '"
				+ FlowConstants.FLOW_APPLY_SERVE
				+ "' "
				+ "                AND T1.IS_VALID = '"
				+ FlowConstants.VALID_YES
				+ "'  "
				+ "                AND W1.WORK_FROM = '"
				+ deptCode
				+ "'  "
				+ "                AND W1.IS_VALID = '"
				+ FlowConstants.VALID_YES
				+ "') T "
				+ "    ON B.SERVE_ID = T.BUSINESS_NO "
				+ " WHERE B.SERVE_PUBLIC = '"
				+ FlowConstants.PUBLIC_YES
				+ "' "
				+ "  AND B.SERVE_DEPT <> '" + deptCode + "'"); // 不是自己发布的服务


		String serveName = request.getParameter("filter_str_serveName_like");
		
		String serveDept = request.getParameter("filter_str_serveDept_eq");
		
		String serveDb = request.getParameter("filter_str_serveDb_eq");
		
		String status = request.getParameter("filter_str_status_eq");
		
		if (!StringUtils.isBlank(serveDept)) {
			datasql.append(" AND B.SERVE_DEPT = '" +serveDept +"'");
		}
		if (!StringUtils.isBlank(serveDb)) {
			datasql.append(" AND B.SERVE_DB = '" +serveDb +"'");
		}
		if (!StringUtils.isBlank(status)) {
			datasql.append(" AND T.NODE_STATUS_CODE = '" +status +"'");
		}
		
		// 获取 查询结果信息
		Map<String, Object> result = getResultBySql(pager, datasql.toString(),serveName);

		return result;

	}

	/**
	 * 审核 申请服务
	 */
	@At("/checkServe")
	@Ok("json")
	public Map<String, Object> checkServe(
			@Param("serveId") final String serveId,
			@Param("workId") final String workId,
			@Param("notes") final String notes, HttpServletRequest request) {

		// 输入参数不能是空
		if (StringUtils.isBlank(serveId) || StringUtils.isBlank(workId)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		// 获取部门 与用户信息
		if (StringUtils.isBlank(UserUtils.getOrganId(request))) {
			return UI.ajaxDone(StatusCode.FAIL);
		}
		final String userId = UserUtils.getUserId(request);

		// 生产 部门授权KEY 

		UUID uuid  =  UUID.randomUUID();
		final String key = uuid.toString().toUpperCase();
		
		// 控制事物
		Trans.exec(new Atom() {
			@Override
			// 事物处理
			public void run() {
				FlowWorkInfo work = dao().fetch(FlowWorkInfo.class, workId);
				if (null == work) {
					throw new FlowException("获取流程信息失败！");
				}
				// 修改 中间表 
				ServeDeptIp serveDept = dao().fetch(
						ServeDeptIp.class,
						Cnd.where("serveId", "=", serveId)
								.and("deptNo", "=", work.getWorkFrom())
								.and("isValid", "=", FlowConstants.VALID_YES));
				if (null == serveDept) {
					throw new FlowException("获取服务与部门信息失败！");
				}
				// 审核状态 通过
				serveDept.setIsPass(FlowConstants.PASS_YES);
				serveDept.setCheckDate(new Date());
				serveDept.setCheckDept(work.getWorkTo());
				serveDept.setClientKey(key);
				dao().update(serveDept);
				// 提交流程
				flowService.checkServe(serveId, userId, workId,
						FlowConstants.FLOW_APPLY_SERVE, notes);
			}
		});

		return ZymlUI.divSearch(StatusCode.OK, "成功审核！", "ajaxDiv");
	}

	/**
	 * 审核 服务列表
	 */
	@At("/checkServeList")
	@Ok("jsp:jsp.jh.serve.check_serve_list")
	public Map<String, Object> checkServeList(Criteria cri, Pager pager,
			HttpServletRequest request) {

		// 获取部门编码
		String deptCode = getDeptCode(request);

		// 查询审核的服务 数据
		StringBuffer datasql = new StringBuffer("SELECT B.*,W.WORK_FROM,W.WORK_ID,W.NODE_STATUS_CODE, T.TASK_STATUS "
				+ "  FROM SERVE_BUSINESS_INFO B, FLOW_WORK_INFO W ,FLOW_TASK_INFO T"
				+ " WHERE W.BUSINESS_NO = B.SERVE_ID "
				+ "   AND W.FLOW_NO = '"
				+ FlowConstants.FLOW_APPLY_SERVE
				+ "' "
				+ "   AND T.BUSINESS_NO = B.SERVE_ID "
				+ "   AND T.FLOW_NO = W.FLOW_NO "
				+ "   AND T.TASK_ID = W.TASK_ID "
				+ "   AND W.NODE_NO = '"
				+ FlowConstants.NODE_APPLY_SERVE_2
				+ "' "
				+ "	 AND W.IS_VALID= '"
				+ FlowConstants.VALID_YES
				+ "'"
				+ "	 AND T.IS_VALID= '"
				+ FlowConstants.VALID_YES
				+ "'"
				+ "	 AND W.WORK_TO = '" + deptCode + "'"
				+ " order by b.create_date desc"
				);
		String serveName = request.getParameter("filter_str_serveName_like");
		
		String serveDept = request.getParameter("filter_str_serveDept_eq");
		
		String serveDb = request.getParameter("filter_str_serveDb_eq");
		
		String status = request.getParameter("filter_str_status_eq");
		
		if (!StringUtils.isBlank(serveDept)) {
			datasql.append(" AND B.SERVE_DEPT = '" +serveDept +"'");
		}
		if (!StringUtils.isBlank(serveDb)) {
			datasql.append(" AND B.SERVE_DB = '" +serveDb +"'");
		}
		if (!StringUtils.isBlank(status)) {
			datasql.append(" AND W.NODE_STATUS_CODE = '" +status +"'");
		}
		System.out.println(datasql);
		// 获取 查询结果信息
		Map<String, Object> result = getResultBySql(pager, datasql.toString(),serveName);

		return result;

	}

	/**
	 * 退回
	 */
	@At("/backServe")
	@Ok("json")
	public Map<String, Object> backServe(
			@Param("serveId") final String serveId,
			@Param("workId") final String workId,
			@Param("notes") final String notes, HttpServletRequest request) {

		// 输入参数不能是空
		if (StringUtils.isBlank(serveId) || StringUtils.isBlank(workId)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		// 获取部门 与用户信息
		if (StringUtils.isBlank(UserUtils.getOrganId(request))) {
			return UI.ajaxDone(StatusCode.FAIL);
		}
		String organId = UserUtils.getOrganId(request);
		// 根据用户获取部门代码
		final String deptCode = DicDataUtils.getInstance().getDicData(
				Constants.DIC_TEAM_ID, organId);

		final String userId = UserUtils.getUserId(request);
		if (StringUtils.isBlank(deptCode)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		// 控制事物
		Trans.exec(new Atom() {
			@Override
			// 事物处理
			public void run() {
				// 提交 工单，
				flowService.rollbackTask(serveId, userId, workId, notes);
			}
		});

		return ZymlUI.divSearch(StatusCode.OK, "成功退回！", "ajaxDiv");
	}

	@At("/alertCheck")
	@Ok("jsp:jsp.jh.serve.check")
	public Map<String, Object> alertCheck(@Param("serveId") String serveId,
			@Param("workId") String workId, @Param("url") String url,
			@Param("isApplyServe") String isApplyServe,
			@Param("type") String type,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("serveId", serveId);
		result.put("workId", workId);
		result.put("url", url);
		result.put("type", type);
		result.put("isApplyServe", isApplyServe);

		if (StringUtils.isEmpty(workId)) {
			return result;
		}
		FlowWorkInfo work = dao().fetch(FlowWorkInfo.class, workId);
		if (null == work) {
			return result;
		}
		result.put("status", work.getNodeStatusCode());
		// 查询tree数据
		// -- 上环节的提交意见
		Sql dsql = Sqls
				.create("SELECT L.HANDLE_NOTES NOTES "
						+"  FROM FLOW_WORK_LOG l "
						+" WHERE LOG_ID = (SELECT LOG_ID "
						+"                   FROM (SELECT T.LOG_ID "
						+"                           FROM FLOW_WORK_LOG T "
						+"                          WHERE T.TASK_ID = '" + work.getTaskId() + "' "
						+"                            AND T.NODE_NO <> '" + work.getNodeNo() + "' "
						+"                          ORDER BY T.LOG_ID DESC) "
						+"                  WHERE ROWNUM = 1)");
		dsql.setCallback(Sqls.callback.record());
		dao().execute(dsql);
		System.out.println(dsql);
		Record record =  (Record) dsql.getResult();
		if (null != record) { // 上一环节 处理信息
			result.put("notes", record.get("NOTES"));
		}
		return result;
	}
	

	@At("/pulish")
	@Ok("jsp:jsp.jh.serve.treeList")
	public Map<String, Object> pulishTree(@Param("serveId")String serveId, HttpSession session) {
		List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
		// 查询tree数据
		Sql dsql = Sqls.create(
					"SELECT 'TOP' NO,'全部' NAME,NULL PNO,'dept' TYPE FROM dual "
					+ " UNION ALL"
					+ " SELECT T.ST_DEPT_CODE AS NO, "
					+ "       T.ST_DEPT_NAME AS NAME, "
					+ "       'TOP' AS PNO, "
					+ "     'dept' AS TYPE "
					+ "   FROM DIC_MUNICIPAL_DEPT T WHERE t.ST_IS_VALID='"+FlowConstants.VALID_YES+"' "
					+ "   UNION ALL "
					+ "   SELECT B.SERVE_ID AS NO, "
					+ "       B.SERVE_NAME AS NAME, "
					+ "       B.SERVE_DEPT AS PNO, "
					+ "       'serve' AS TYPE "
					+ "   FROM (select * from SERVE_BUSINESS_INFO   "
					+ "   WHERE SERVE_PUBLIC = '"+FlowConstants.PUBLIC_YES+"' " 
					+ " order by SERVE_PUBLIC_DATE desc) b");
		// sql.setCondition(cri);
		dsql.setCallback(Sqls.callback.records());
		// sql.setEntity(dao().getEntity(Map.class));
		System.out.println(dsql);
		dao().execute(dsql);
		List<Record> list = dsql.getList(Record.class);
		String treeJson = Json.toJson(list);
		
		// 服务接口明细信息
		ServeBusinessInfo serve = new ServeBusinessInfo();
		List<ServeBusinessFieldDetail> fieldList = null ;
		if (!StringUtils.isBlank(serveId)) {
			serve = dao().fetch(ServeBusinessInfo.class, serveId);
			fieldList = dao().query(ServeBusinessFieldDetail.class
					, Cnd.where("serveId", "=", serveId).orderBy().asc("fieldOrder"));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("treeJson", treeJson);
		map.put("serve", serve);
		map.put("fieldList", fieldList);
		return map;
	}

	@At("/myApply")
	@Ok("jsp:jsp.jh.serve.my_apply")
	public Map<String, Object> myApply(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
	}
	
	/**
	 * 退回
	 */
	@At("/serveSwitch")
	@Ok("json")
	public Map<String, Object> serveSwitch(
			@Param("serveId") String serveId,
			@Param("struts") String struts,
			@Param("notes") String notes, HttpServletRequest request) {
		
		// 输入参数不能是空
		if (StringUtils.isBlank(serveId) || StringUtils.isBlank(struts)) {
			return UI.ajaxDone(StatusCode.FAIL);
		}

		ServeBusinessInfo serve = dao().fetch(ServeBusinessInfo.class, serveId);
		// 开启
		if (FlowConstants.SERVER_OPEN.equals(struts)) {
			serve.setServeSwitch(struts);
		} else {
			serve.setServeSwitch(FlowConstants.SERVER_CLOSE);
		}
		dao().update(serve);
		return ZymlUI.divSearch(StatusCode.OK, "成功操作！", "ajaxDiv");
	}
	
	
	
	
	/**
	 * JDBC 分页查询数据
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-6
	 * @param pager
	 * @param dataSql
	 * @return
	 */
	private Map<String, Object> getResultBySql(Pager pager, String dataSql,String serveName) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean hasName = !StringUtils.isBlank(serveName);
		dataSql = "select * from (" + dataSql + ")" ;
		if (hasName) {
			dataSql += " $condition ";
		}
		// 查询数据
		Sql dsql = Sqls.create(getPageSql(dataSql, pager.getPageNumber(),
				pager.getPageSize()));
		// sql.setCondition(cri);
		dsql.setCallback(Sqls.callback.records());

		Cnd cnd = Cnd.where("1", "=", "1");
		// 根据名称查找
		if(hasName){
			cnd = Cnd.where("serve_Name", "like", serveName);
			//dsql.params().set("serveName", serveName);
		}
		cnd.desc("CREATE_DATE");
		dsql.setCondition(cnd);
		// sql.setEntity(dao().getEntity(Map.class));
		System.out.println(dsql);
		dao().execute(dsql);
		List<Record> list = dsql.getList(Record.class);
		// 查询分页信息
		Sql psql = Sqls.create("select count(*) from (" + dataSql + ")");
		if(hasName){
			psql.setCondition(Cnd.where("serve_Name", "like", serveName));
			//psql.params().set("serveName", " %" + serveName + "% ");
		}
		// sql.setCondition(cri);
		psql.setCallback(Sqls.callback.integer());
		// sql.setEntity(dao().getEntity(Map.class));
		dao().execute(psql);
		int total = psql.getInt();
		pager.setRecordCount(total);
		
		result.put("serveList", list);
		result.put("pager", pager);
		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param index
	 * @param size
	 * @return
	 */
	private String getPageSql(String sql, int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 0;
		}
		String querySql = " select * from ( select rownum page1_r, page1_.* from ("
				+ sql
				+ ") page1_ where rownum <= "
				+ ((pageNo) * pageSize)
				+ ") page2_ where page2_.page1_r > "
				+ ((pageNo - 1) * pageSize);
		// System.out.println(querySql);
		return querySql;
	}

	/**
	 * 获取部门编号
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-6
	 * @param request
	 * @return
	 */
	public String getDeptCode(HttpServletRequest request) {
		
		if (StringUtils.isBlank(UserUtils.getUserId(request))) {
			return null;
		}
		String organId = UserUtils.getUserId(request);
		// 根据用户获取部门代码
		String deptCode = DicDataUtils.getInstance().getDicData(
				Constants.DIC_TEAM_ID, organId);

		return deptCode;
	}
	
}
