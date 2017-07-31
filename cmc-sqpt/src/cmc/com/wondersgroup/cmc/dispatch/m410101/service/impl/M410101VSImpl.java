package com.wondersgroup.cmc.dispatch.m410101.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.IFCheckUtils;
import com.wondersgroup.cmc.dispatch.m410101.model.M410101VO;
import com.wondersgroup.cmc.dispatch.m410101.service.M410101VS;
import com.wondersgroup.cmc.dispatch.message.service.MessageRouterService;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefhis;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.bo.Ifmondef;
import com.wondersgroup.cmc.dispatch.model.bo.Iftransdetail;
import com.wondersgroup.cmc.dispatch.model.bo.Iftranssum;
import com.wondersgroup.cmc.dispatch.model.bo.Ifuser;
import com.wondersgroup.cmc.dispatch.model.bo.Ifuserauth;
import com.wondersgroup.cmc.dispatch.model.bo.Ifuserhis;
import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.cmc.dispatch.model.dto.IfdefineDTO;
import com.wondersgroup.cmc.dispatch.model.dto.IftransdetailDTO;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.utils.Base64Utils;
import com.wondersgroup.cmc.utils.NumberUtils;
import com.wondersgroup.cmc.utils.StringUtils;
import com.wondersgroup.cmc.utils.UserContextUtils;
import com.wondersgroup.cmc.utils.pagequery.BaseQueryVSImpl;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.cmc.utils.pagequery.JdbcDaoLogUtils;
import com.wondersgroup.cmc.utils.pagequery.JdbcDaoUtils;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;
import com.wondersgroup.wssip.util.BeanTools;
import com.wondersgroup.wssip.util.DateTools;
import com.wondersgroup.wssip.util.StringTools;

public class M410101VSImpl extends BaseQueryVSImpl implements M410101VS {
	private MessageRouterService messageRouterService;
	private MessageRouterService jkfwmessageRouterService;
	private static Log log = LogFactory.getLog(M410101VSImpl.class);
	
	public void setMessageRouterService(MessageRouterService messageRouterService) {
		this.messageRouterService = messageRouterService;
	}

	public void setJkfwmessageRouterService(MessageRouterService jkfwmessageRouterService) {
		this.jkfwmessageRouterService = jkfwmessageRouterService;
	}

	/**
	 * 新增接口
	 */
	@Override
	public void insert(M410101VO vo) {
		checkData(vo, "insert");
		Ifdefine formBean = (Ifdefine) VOUtils.getBeanFromJsonData(vo.getJsonData(), Ifdefine.class);	
		// 判断接口代码是否已被使用
		String countSql = new String(
			"select 1 from IFDEFINE t where t.IFDEFCODE='"
				+ formBean.getIfdefcode()+"' and t.VALID=1");
		Long count = JdbcDaoUtils.queryObject(countSql, Long.class);
		if (count != null && count > 0) {
			throw new BusinessException("接口代码已被使用，请修改");
		}
		//保存进IFDEFINE表
		formBean.setIfsubtype(vo.getIfsubtype());
		formBean.setEdtime(new Date());
		formBean.setIfext(vo.getDataext());
		formBean.setUsrname(UserContextUtils.getOperatorName());
		formBean.setUsrid(Long.parseLong(UserContextUtils.getOperatorId()));
		formBean.setValid(DispatchContent.VALID);//有效标志为1
		CommonHibernateDaoUtils.save(formBean);
		//保存进IFDEFINEHIS表
		Ifdefhis formBeanHis = new Ifdefhis();
		BeanTools.copyProperties(formBean, formBeanHis);
		CommonHibernateDaoUtils.save(formBeanHis);
		//条线接口需要额外初始化IFTRANSSUM表
		if(DispatchContent.IFTYPE_IF.equals(formBean.getIftype())){
			Iftranssum iftranssum = new Iftranssum();
			iftranssum.setIfdefineid(formBean.getIfdefineid());
			iftranssum.setTransfail(new Long(0));
			iftranssum.setTranssuc(new Long(0));
			iftranssum.setTranssum(new Long(0));
			CommonHibernateDaoUtils.save(iftranssum);
		}
	}

	/**
	 * 修改接口
	 */
	@Override
	public void update(M410101VO vo) {
		checkData(vo, "update");
		Ifdefine formBean = (Ifdefine) VOUtils.getBeanFromJsonData(vo.getJsonData(), Ifdefine.class);
		//更新IFDEFINE表
		Ifdefine updateBean = CommonHibernateDaoUtils.load(Ifdefine.class, Long.valueOf(formBean.getIfdefineid()));
		updateBean.setIfdefcode(formBean.getIfdefcode());
		updateBean.setIfdefname(formBean.getIfdefname());
		updateBean.setIfuri(formBean.getIfuri());
		updateBean.setIfsubtype(vo.getIfsubtype());
		updateBean.setEdtime(new Date());
		updateBean.setIfext(vo.getDataext());
		updateBean.setUsrname(UserContextUtils.getOperatorName());
		updateBean.setUsrid(Long.parseLong(UserContextUtils.getOperatorId()));
		CommonHibernateDaoUtils.update(updateBean);
		//保存进IFDEFINEHIS表
		Ifdefhis formBeanHis = new Ifdefhis();
		BeanTools.copyProperties(updateBean, formBeanHis);
		CommonHibernateDaoUtils.save(formBeanHis);
	}
	
	/**
	 * 校验入参完整性
	 * @param vo
	 * @param type
	 */
	private void checkData(M410101VO vo,String type) {
		if (StringTools.isEmpty(vo.getJsonData())) {
			throw new BusinessException("无法找到需要保存的数据，请确认");
		}
		Ifdefine formBean = (Ifdefine) VOUtils.getBeanFromJsonData(vo.getJsonData(), Ifdefine.class);
		if(type=="insert"){
			if (NumberUtils.isNotEmpty(formBean.getIfdefineid())) {
				throw new BusinessException("新增时ID必须为空，请确认");
			}
		}else if (type=="update") {
			if (NumberUtils.isEmpty(formBean.getIfdefineid()) || formBean.getIfdefineid() == 0) {
				throw new BusinessException("修改时ID不允许为空，请确认");
			}
		}
		if (StringTools.isEmpty(formBean.getIfdefcode())) {
			throw new BusinessException("接口代码不能为空，请确认");
		}
	}

	/**
	 * 删除接口
	 */
	@Override
	public void delete(M410101VO vo) {
		if (StringTools.isEmpty(vo.getIfdefineid())) {
			throw new BusinessException("删除时ID不能为空，请确认");
		}
		//更新IFDEFINE表，valid置为0
		Ifdefine updateBean = CommonHibernateDaoUtils.load(Ifdefine.class, Long.valueOf(vo.getIfdefineid()));

		Ifmondef ifmondef = CommonHibernateDaoUtils.get(" from Ifmondef t where t.ifdefineid=? AND t.valid = ? ", updateBean.getIfdefineid(),DispatchContent.VALID);
		if(ifmondef!=null){
			throw new BusinessException("请先删除接口所对应的监控");

		}
		
		updateBean.setValid(DispatchContent.UNVALID);
		updateBean.setEdtime(new Date());
		updateBean.setUsrname(UserContextUtils.getOperatorName());
		updateBean.setUsrid(Long.parseLong(UserContextUtils.getOperatorId()));
		
		CommonHibernateDaoUtils.update(updateBean);
		//保存进IFDEFINEHIS表
		Ifdefhis formBeanHis = new Ifdefhis();
		BeanTools.copyProperties(updateBean, formBeanHis);
		CommonHibernateDaoUtils.save(formBeanHis);
	}

	/**
	 * 加载接口
	 */
	@Override
	public Ifdefine load(M410101VO vo) {
		Ifdefine bean = CommonHibernateDaoUtils.load(Ifdefine.class, Long.parseLong(vo.getIfdefineid()));
		return bean;
	}

	/**
	 * 分页查询接口
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public DataGrid query(PageQueryDTO dto) {
		JdbcDaoLogUtils.doLog(log, "[ifdefineQuery]QueryDTO = [" + dto.toString() + "]");

		String fromSQL = new String("SELECT a.*, b.transsum, b.transsuc, b.transfail FROM IFDEFINE a left join IFTRANSSUM b on a.ifdefineid = b.ifdefineid ");

		String orderBy = " a.ifdefcode ";

		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();

		String query = dto.getParamAsString("queryText");
		if (StringTools.hasText(query)) {
		  query = "%" + query + "%";
		  appendCondition(condition, args, " AND (a.IFDEFCODE LIKE ? ", query);
		  appendCondition(condition, args, " OR a.IFDEFNAME LIKE ? ) ", query);
		}
		appendCondition(condition, args, " AND a.IFTYPE = ? ", dto.getParamAsString("iftype"));
		appendCondition(condition, args, " AND a.VALID = ? ", dto.getParamAsString("valid"));

		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, dto);

		String sql = fromSQL + where+orderby;
		Object[] params = args.toArray();
		DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), IfdefineDTO.class);
		return dataGrid;
	}

	
	
	/**
	 * 接口测试
	 */
	@Override
	public HttpClientDTO test(M410101VO vo) {
		if(vo.getIfuri()!=null){
			HttpClientDTO status = IFCheckUtils.checkInterface(vo.getIfsubtype(),vo.getIfuri());
			return status;
		}else{
			Ifdefine formBean = CommonHibernateDaoUtils.load(Ifdefine.class, Long.valueOf(vo.getIfdefineid()));
			HttpClientDTO status = IFCheckUtils.checkInterface(formBean.getIfsubtype(),formBean.getIfuri());
			return status;
		}
				
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid querytransdetail(PageQueryDTO dto) throws ParseException {
		JdbcDaoLogUtils.doLog(log, "[ifdefineQuery]QueryDTO = [" +  dto.toString() + "]");
		
		// (1) 查询语句 from部分
		String fromSQL = new String("SELECT a.iftransdetailid,a.transno,a.transsubfunc,a.reqcode,a.requsrname,a.rspcode,a.rspusrname,a.reqtime,a.rsptime,a.transtime,a.transflag1,a.transflag2 FROM IFTRANSDETAIL a");
		// (1.1) 附加默认排序
		String orderBy = " a.reqtime desc ";
		
		// (2) new 查询语句where条件部分和? 参数部分
		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();
		// (3) 添加条件判断
		String reqstartDate = dto.getParamAsString("reqstartDate");
		String reqendDate = dto.getParamAsString("reqendDate");
		if(StringTools.hasText(reqstartDate)){
			reqstartDate = reqstartDate.replace("T", " ");
			condition.append(" AND a.REQTIME >= ? ");
			args.add(DateTools.parseDate(reqstartDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringTools.hasText(reqendDate)){
			reqendDate = reqendDate.replace("T", " ");
			condition.append(" AND a.REQTIME <= ? ");
			args.add(DateTools.parseDate(reqendDate, "yyyy-MM-dd HH:mm:ss"));
		}

		String transno = dto.getParamAsString("transno");
		if(StringTools.hasText(transno)){
			appendCondition(condition, args, " AND a.transno = ? ", transno);
		}
		
		String transsubfunc = dto.getParamAsString("transsubfunc");
		if(StringTools.hasText(transsubfunc)){
			appendCondition(condition, args, " AND a.transsubfunc = ? ", transsubfunc);
		}
		appendCondition(condition, args, " AND a.requsrname = ? ", dto.getParamAsString("requsrname"));
		appendCondition(condition, args, " AND a.ifdefineid = ? ", dto.getParamAsString("ifdefineid"));
		
		// (4) 处理完成where和orderby部分
		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, dto);
		
		// (5)处理完成的SQL语句 , 带?和参数
		String sql = fromSQL + where + orderby;
		Object[] params = args.toArray();
		DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), IftransdetailDTO.class);
		return dataGrid;
	}

	@Override
	public Iftransdetail loaderr(M410101VO vo) {
		Iftransdetail bean = CommonHibernateDaoUtils.load(Iftransdetail.class, vo.getIftransdetailid());
		return bean;
	}

	@Override
	public Iftransdetail loadxml(M410101VO vo) {
		Iftransdetail bean = CommonHibernateDaoUtils.load(Iftransdetail.class, vo.getIftransdetailid());
		return bean;
	}

	/**
	 * 重新调用接口
	 */
	@Override
	public void resendxml(M410101VO vo) {
		Iftransdetail iftransdetail = this.loadxml(vo);
		
		if(iftransdetail == null){
			throw new BusinessException("交易明细查询有误。");
		}
		vo.setIfdefineid(iftransdetail.getIfdefineid().toString());
		Ifdefine ifdefine = this.load(vo);
		Object[] params = null;
		if(StringTools.hasText(iftransdetail.getTransfunc()) && iftransdetail.getTransfunc().indexOf("__JKFW__")>=0){
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("userid", iftransdetail.getReqcode());
			hashMap.put("token", iftransdetail.getRequsrid());
			hashMap.put("data", JSONObject.fromObject(iftransdetail.getReqxml()));
			params = new Object[]{VOUtils.getJsonData(hashMap)};
			jkfwmessageRouterService.route(ifdefine.getIfdefcode(), null, params);
		} else {
			if(StringTools.isEmpty(iftransdetail.getReqxml())){
				Byte flag = ("0".equals(iftransdetail.getReqflag()))?(new Byte(((byte) 0x00))):(new Byte(((byte) 0x01)));
				params = new Object[]{flag,iftransdetail.getReqenxml()==null?null:Base64Utils.decodeBytes(iftransdetail.getReqenxml())};
			} else {
				Byte flag = new Byte((byte) 0x00);
				params = new Object[]{flag,StringUtils.createBytes(iftransdetail.getReqxml())};
			}
			messageRouterService.route(ifdefine.getIfdefcode(), iftransdetail.getTransfunc(), params);
		}
	}

	/**
	 * 新增接口用户
	 * @return
	 */
	@Override
	public void insertuser(M410101VO vo) {
		checkUserData(vo, "insert");
		Ifuser formBean = (Ifuser) VOUtils.getBeanFromJsonData(vo.getJsonData(), Ifuser.class);	
		// 判断用户代码是否已被使用
		String countSql = new String("select 1 from ifuser t where t.ifusercode=? and t.valid='1' ");
		Long count = JdbcDaoUtils.queryObject(countSql, Long.class,formBean.getIfusercode());
		if (count != null && count > 0) {
			throw new BusinessException("用户代码已被使用，请修改");
		}
		//保存进ifuser表
		formBean.setEdtime(new Date());
		formBean.setUsrname(UserContextUtils.getOperatorName());
		formBean.setUsrid(Long.parseLong(UserContextUtils.getOperatorId()));
		formBean.setValid(DispatchContent.VALID);//有效标志为1
		CommonHibernateDaoUtils.save(formBean);
		//保存进ifuserhis表
		Ifuserhis formBeanHis = new Ifuserhis();
		BeanTools.copyProperties(formBean, formBeanHis);
		CommonHibernateDaoUtils.save(formBeanHis);
	}

	/**
	 * 校验入参完整性
	 * @param vo
	 * @param type
	 */
	private void checkUserData(M410101VO vo, String type) {
		if (StringTools.isEmpty(vo.getJsonData())) {
			throw new BusinessException("无法找到需要保存的数据，请确认");
		}
		Ifuser formBean = (Ifuser) VOUtils.getBeanFromJsonData(vo.getJsonData(), Ifuser.class);	
		if(type=="insert"){
			if (NumberUtils.isNotEmpty(formBean.getIfuserid())) {
				throw new BusinessException("新增时ID必须为空，请确认");
			}
		}else if (type=="update") {
			if (NumberUtils.isEmpty(formBean.getIfuserid()) || formBean.getIfuserid() == 0) {
				throw new BusinessException("修改时ID不允许为空，请确认");
			}
		}
		if (StringTools.isEmpty(formBean.getIfusercode())) {
			throw new BusinessException("用户代码不能为空，请确认");
		}
		
		if (StringTools.isEmpty(formBean.getIfusername())) {
			throw new BusinessException("用户姓名不能为空，请确认");
		}
		
		if (StringTools.isEmpty(formBean.getIfusertoken())) {
			throw new BusinessException("用户token不能为空，请确认");
		}
		
		if (StringTools.isEmpty(formBean.getIfuseraeskey())) {
			throw new BusinessException("用户aeskey不能为空，请确认");
		}
	}

	/**
	 * 修改接口用户
	 * @return
	 */
	@Override
	public void updateuser(M410101VO vo) {
		checkUserData(vo, "update");
		Ifuser formBean = (Ifuser) VOUtils.getBeanFromJsonData(vo.getJsonData(), Ifuser.class);
		//更新ifuser表
		Ifuser updateBean = CommonHibernateDaoUtils.load(Ifuser.class, Long.valueOf(formBean.getIfuserid()));
		updateBean.setIfusercode(formBean.getIfusercode());
		updateBean.setIfusername(formBean.getIfusername());
		updateBean.setIfusertoken(formBean.getIfusertoken());
		updateBean.setIfuseraeskey(formBean.getIfuseraeskey());
		updateBean.setEdtime(new Date());
		updateBean.setUsrname(UserContextUtils.getOperatorName());
		updateBean.setUsrid(Long.parseLong(UserContextUtils.getOperatorId()));
		CommonHibernateDaoUtils.update(updateBean);
		//保存进ifuserhis表
		Ifuserhis formBeanHis = new Ifuserhis();
		BeanTools.copyProperties(updateBean, formBeanHis);
		CommonHibernateDaoUtils.save(formBeanHis);
	}

	/**
	 * 删除接口用户
	 * @return
	 */
	@Override
	public void deleteuser(M410101VO vo) {
		if (NumberUtils.isEmpty(vo.getIfuserid())) {
			throw new BusinessException("删除时ID不能为空，请确认");
		}
		//更新IFDEFINE表，valid置为0
		Ifuser updateBean = CommonHibernateDaoUtils.load(Ifuser.class, vo.getIfuserid());

		updateBean.setValid(DispatchContent.UNVALID);
		updateBean.setEdtime(new Date());
		updateBean.setUsrname(UserContextUtils.getOperatorName());
		updateBean.setUsrid(Long.parseLong(UserContextUtils.getOperatorId()));
		
		CommonHibernateDaoUtils.update(updateBean);
		//保存进IFDEFINEHIS表
		Ifuserhis formBeanHis = new Ifuserhis();
		BeanTools.copyProperties(updateBean, formBeanHis);
		CommonHibernateDaoUtils.save(formBeanHis);
	}
	
	/**
	 * 加载接口用户
	 * @return
	 */
	@Override
	public Ifuser loaduser(M410101VO vo) {
		Ifuser bean = CommonHibernateDaoUtils.load(Ifuser.class, vo.getIfuserid());
		return bean;
	}

	/**
	 * 分页查询接口用户
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public DataGrid queryusers(PageQueryDTO dto) {
		JdbcDaoLogUtils.doLog(log, "[ifuserQuery]QueryDTO = [" + dto.toString() + "]");

		String fromSQL = new String("SELECT a.* FROM IFUSER a ");
		
		String orderBy = " a.ifusercode ";
		
		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();
		
		String query = dto.getParamAsString("queryText");
		if (StringTools.hasText(query)) {
		  query = "%" + query + "%";
		  appendCondition(condition, args, " AND (a.ifusercode LIKE ? ", query);
		  appendCondition(condition, args, " OR a.ifusername LIKE ? ) ", query);
		}
		appendCondition(condition, args, " AND a.VALID = ? ", dto.getParamAsString("valid"));
		
		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, dto);
		
		String sql = fromSQL + where+orderby;
		Object[] params = args.toArray();
		DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), Ifuser.class);
		return dataGrid;
	}

	/**
	 * 查询接口用户授权/未授权列表
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public DataGrid listblindingif(PageQueryDTO dto) {
		JdbcDaoLogUtils.doLog(log, "[blindingifQuery]QueryDTO = [" + dto.toString() + "]");

		String fromSQL = new String("SELECT a.* FROM IFDEFINE a ");

		String orderBy = " a.ifdefcode ";

		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();

		String blind = dto.getParamAsString("blind");
		String ifuserid = dto.getParamAsString("ifuserid");
		if(StringTools.hasText(ifuserid)){
			if (StringTools.hasText(blind) && "true".equals(blind)) {
				appendCondition(condition, args, " AND EXISTS (SELECT 1 FROM ifuserauth b where a.ifdefineid = b.ifdefineid and b.ifuserid = ?) ",ifuserid);
				
			} else {
				appendCondition(condition, args, " AND NOT EXISTS (SELECT 1 FROM ifuserauth b where a.ifdefineid = b.ifdefineid and b.ifuserid = ?) ",ifuserid);
			}
		} else {
			condition.append(" AND 1<>1 ");
		}
		appendCondition(condition, args, " AND a.IFTYPE = ? ", dto.getParamAsString("iftype"));
		appendCondition(condition, args, " AND a.VALID = ? ", dto.getParamAsString("valid"));

		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, dto);

		String sql = fromSQL + where+orderby;
		Object[] params = args.toArray();
		DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), IfdefineDTO.class);
		return dataGrid;
	}
	
	/**
	 * 接口用户授权
	 * @param vo
	 */
	@Override
	public void addifuserauth(M410101VO vo) {
		if(vo == null || NumberUtils.isEmpty(vo.getIfuserid()) || StringTools.isEmpty(vo.getIfdefineids())){
			return;
		}
		String[] ifdefineids = vo.getIfdefineids().split(",");
		
		for(String id : ifdefineids){
			Long ifdefineid = null;
			try {
				ifdefineid = Long.parseLong(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(NumberUtils.isNotEmpty(ifdefineid)){
				//保存授权信息
				Ifuserauth ifuserauth = new Ifuserauth();
				ifuserauth.setIfuserid(vo.getIfuserid());
				ifuserauth.setIfdefineid(ifdefineid);
				CommonHibernateDaoUtils.save(ifuserauth);
			}
		}
	}

	@Override
	public void removeifuserauth(M410101VO vo) {
		if(vo == null || NumberUtils.isEmpty(vo.getIfuserid()) || StringTools.isEmpty(vo.getIfdefineids())){
			return;
		}
		String[] ifdefineids = vo.getIfdefineids().split(",");
		
		for(String id : ifdefineids){
			Long ifdefineid = null;
			try {
				ifdefineid = Long.parseLong(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(NumberUtils.isNotEmpty(ifdefineid)){
				String sql = "delete from ifuserauth where ifuserid = ? and ifdefineid = ? ";
				CommonJdbcDaoUtils.executeUpdate(sql, vo.getIfuserid(), ifdefineid);
			}
		}
	}

}
