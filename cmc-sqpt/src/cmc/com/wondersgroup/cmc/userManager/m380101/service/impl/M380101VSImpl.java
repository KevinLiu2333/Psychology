package com.wondersgroup.cmc.userManager.m380101.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.cmc.model.dto.LeftMenuDTO;
import com.wondersgroup.cmc.model.dto.QueryDTO;
import com.wondersgroup.cmc.userManager.m380101.model.M380101VO;
import com.wondersgroup.cmc.userManager.m380101.service.M380101VS;
import com.wondersgroup.cmc.utils.UserContextUtils;
import com.wondersgroup.cmc.utils.pagequery.BaseQueryVSImpl;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.cmc.utils.pagequery.JdbcDaoLogUtils;
import com.wondersgroup.cmc.utils.pagequery.JdbcDaoUtils;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.wssip.application.SessionConstants;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.model.bo.UaRoleInfo;
import com.wondersgroup.wssip.model.bo.UaUserInfo;
import com.wondersgroup.wssip.util.StringTools;

public class M380101VSImpl extends BaseQueryVSImpl implements M380101VS{
	private static Log log = LogFactory.getLog(M380101VSImpl.class);
	
	@Override
	@SuppressWarnings("rawtypes")
	public DataGrid query(QueryDTO dto) {
		JdbcDaoLogUtils.doLog(log, "[uaUserInfoQuery]QueryDTO = [" +  dto.toString() + "]");
		// (1) 查询语句 from部分
		String fromSQL = new String("SELECT * FROM UAUSERINFO a ");
		// (1.1) 附加默认排序
		String orderBy = " a.userid ";
		// (2) new 查询语句where条件部分和? 参数部分
		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();
		// (3) 添加条件判断
		String query = dto.getParamAsString("queryText");
		if(StringTools.hasText(query)){
			query = "%"+query+"%";
			appendCondition(condition, args, " AND (a.loginname LIKE ? ", query);
			appendCondition(condition, args, " OR a.displayname LIKE ? ) ", query);
		}
		appendCondition(condition, args, " AND a.status = ? ", dto.getParamAsString("status"));
		// (4) 处理完成where和orderby部分
		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, dto);
		// (5)处理完成的SQL语句 , 带?和参数
		String sql = fromSQL + where + orderby;
		Object[] params = args.toArray();
		DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), UaUserInfo.class);
		return dataGrid;
	}

	@Override
	public void insert(M380101VO vo) {
		String roleids = vo.getRoleids();
		String[] roleArr = roleids.split(",");
		List<UaRoleInfo> roleList = new ArrayList<UaRoleInfo>();
		for (String roleid : roleArr){
			UaRoleInfo role = CommonHibernateDaoUtils.load(UaRoleInfo.class, Long.parseLong(roleid));
			if (role != null){
				roleList.add(role);
			}
		}
		UaUserInfo formBean = (UaUserInfo) VOUtils.getBeanFromJsonData(vo.getJsonData(), UaUserInfo.class);
		formBean.setPassword(DigestUtils.shaHex(formBean.getPassword()));
		formBean.setAuthentictype("loginname");
		formBean.setStatus("1");
		formBean.setRoleList(roleList);
		//保存进IFDEFINE表
		CommonHibernateDaoUtils.save(formBean);
	}

	@Override
	public UaUserInfo load(M380101VO vo) {
		UaUserInfo bean = CommonHibernateDaoUtils.load(UaUserInfo.class, vo.getUserid());
		return bean;
	}

	@Override
	public void update(M380101VO vo) {
		String roleids = vo.getRoleids();
		String[] roleArr = roleids.split(",");
		List<UaRoleInfo> roleList = new ArrayList<UaRoleInfo>();
		for (String roleid : roleArr){
			UaRoleInfo role = CommonHibernateDaoUtils.load(UaRoleInfo.class, Long.parseLong(roleid));
			if (role != null){
				roleList.add(role);
			}
		}
		UaUserInfo formBean = (UaUserInfo) VOUtils.getBeanFromJsonData(vo.getJsonData(), UaUserInfo.class);
		UaUserInfo updateBean = CommonHibernateDaoUtils.load(UaUserInfo.class, formBean.getUserid());
		updateBean.setLoginname(formBean.getLoginname());
		updateBean.setDisplayname(formBean.getDisplayname());
		updateBean.setRoleList(roleList);
		if (StringTools.hasText(formBean.getPassword())){
			updateBean.setPassword(DigestUtils.shaHex(formBean.getPassword()));
		}
		CommonHibernateDaoUtils.update(updateBean);
	}

	@Override
	public void delete(M380101VO vo) {
		if (vo.getUserid() == null) {
    	    throw new BusinessException("删除时ID不能为空，请确认");
    	}
    	UaUserInfo updateBean = CommonHibernateDaoUtils.load(UaUserInfo.class, vo.getUserid());
    	updateBean.setStatus("0");
    	CommonHibernateDaoUtils.update(updateBean);
	}

	
	@Override
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> loadRole() {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		// (1) 查询语句 from部分
		String fromSQL = new String("SELECT * FROM UAROLEINFO a ");
		// (1.1) 附加默认排序
		String orderBy = " a.roleid ";
		// (2) new 查询语句where条件部分和? 参数部分
		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();
		// (3) 添加条件判断
		// (4) 处理完成where和orderby部分
		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, new QueryDTO());
		// (5)处理完成的SQL语句 , 带?和参数
		String sql = fromSQL + where + orderby;
		List<UaRoleInfo> roleList = JdbcDaoUtils.query(sql, UaRoleInfo.class);
		if (roleList != null && roleList.size() > 0){
			for (UaRoleInfo role : roleList){
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", role.getRoleid().toString());
				map.put("text", role.getRolename());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String checkLoginName(M380101VO vo) {
		String result = "0";
		// (1) 查询语句 from部分
		String fromSQL = new String("SELECT * FROM UAUSERINFO a ");
		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();
		// (3) 添加条件判断
		appendCondition(condition, args, " AND a.loginname = ? ", vo.getLoginname());
		// (4) 处理完成where和orderby部分
		String where = formatWhere(condition, args);
		// (5)处理完成的SQL语句 , 带?和参数
		String sql = fromSQL + where;
		List<UaUserInfo> userList = JdbcDaoUtils.query(sql, UaUserInfo.class, args);
		if (userList == null || userList.size() == 0){
			result = "1";
		}
		return result;
	}

	@Override
	public String changePsd(M380101VO vo) {
		String result = "0";
		String id = UserContextUtils.getOperatorId();
    	UaUserInfo updateBean = CommonHibernateDaoUtils.load(UaUserInfo.class, Long.parseLong(id));
    	if (DigestUtils.shaHex(vo.getOldPassword()).equals(updateBean.getPassword())){
    		updateBean.setPassword(DigestUtils.shaHex(vo.getPassword()));
    		CommonHibernateDaoUtils.update(updateBean);
    		result = "1";
    	}
    	return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> queryItems(HttpSession session) {
		List<LeftMenuDTO> thirdMenuList = null;
		thirdMenuList = (List<LeftMenuDTO>) session.getAttribute(SessionConstants.THIRDMENULIST);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		if (thirdMenuList != null && thirdMenuList.size() > 0){
			for (LeftMenuDTO role : thirdMenuList){				
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", role.getId().toString());
				map.put("text", role.getName());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public String getExt1() {
    	UaUserInfo userInfo = CommonHibernateDaoUtils.load(UaUserInfo.class, Long.valueOf(UserContextUtils.getOperatorId()));
    	String ext1 = userInfo.getExt1();
		return ext1;

	}

	@Override
	public void saveItems(M380101VO vo) {
    	UaUserInfo userInfo = CommonHibernateDaoUtils.load(UaUserInfo.class, Long.valueOf(UserContextUtils.getOperatorId()));
		userInfo.setExt1(vo.getJsonData());
		CommonHibernateDaoUtils.update(userInfo);
	}
	

	@Override
	public void saveAppids(M380101VO vo) {
    	UaUserInfo userInfo = CommonHibernateDaoUtils.load(UaUserInfo.class, Long.valueOf(UserContextUtils.getOperatorId()));
		userInfo.setExt2(vo.getExt2());
		CommonHibernateDaoUtils.update(userInfo);
	}

}
