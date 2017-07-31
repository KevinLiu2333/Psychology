package com.wonders.home.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.Constants;
import com.wonders.db.entity.Xdb_files;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.extend.filter.SessionFilter;
import com.wonders.utils.CookieUtils;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgx.entity.ResourceApply;

/**
 * 准备基础数据到首页显示.
 */
@IocBean
public class QueryBaseInfo {
	@Inject
	private Dao dao;

	/**
	 * 获取基础数据.
	 * @param request
	 * @return
	 */
	public Map<String, Object> getBaseInfo(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = dao.createPager(1, 4);
		Criteria jcCri = Cnd.cri();
		jcCri.getOrderBy().asc("updateDate");
		jcCri.where().andIn("resourceType", Constants.R_TYPE_PEOPLE,Constants.R_TYPE_CORP,Constants.R_TYPE_HOUSE);
		Criteria topicCri = Cnd.cri();
		topicCri.getOrderBy().asc("updateDate");
		topicCri.where().andEquals("resourceType","r_ztl");
		//资源目录-基础类
		List<Resource> jcResourceList = dao.query(Resource.class, jcCri, pager);
		//资源目录-行业
		List<Resource> topicResourceList = dao.query(Resource.class, topicCri, pager);
		result.put("jcResourceList", jcResourceList);
		result.put("topicResourceList", topicResourceList);
		//已开通服务数量
		Criteria applyCri = Cnd.cri();
		applyCri.where().andEquals("isSubmit", "1");
		List<ResourceApply> resourceApplyList = dao.query(ResourceApply.class, applyCri, pager);
		for(ResourceApply apply : resourceApplyList){
			if(!Strings.isEmpty(apply.getApplyTopic())){
				apply.setApplyTopic(apply.getApplyTopic().length() > 24 ? apply.getApplyTopic().substring(0, 24) + "......" : apply.getApplyTopic());
			}
		}
		
		//判断是否登录
		String userName = CookieUtils.getCookie(request, SessionFilter.LOGON_NAME);
		User user = dao.fetch(User.class, Cnd.where("logon_name","=",userName));
		
		//当前用户已经申请的数据服务(走马灯显示)
		Criteria ownCri = Cnd.cri();
		if(user != null){
			ownCri.where().andEquals("userId", user.getUserId());
		}
		List<ResourceApply> ownApplyList = dao.query(ResourceApply.class, ownCri);
		
		result.put("ownApplyList", ownApplyList);
		result.put("resourceApplyList", resourceApplyList);
		result.put("openedCount", resourceApplyList.size());
		result.put("resourceApplyList", resourceApplyList);
		result.put("user", user);
		
		
		Criteria docCri1 = Cnd.cri();
		docCri1.where().and("isdeleted", "=", "0");
		docCri1.where().and("postcomplete", "=", "1");
		docCri1.getOrderBy().desc("POSTEDTIME");
		docCri1.where().and("fileType","=","政策法规");
		
		List<Xdb_files> docList1 = dao.query(Xdb_files.class, docCri1, pager);
		for(Xdb_files file : docList1){
			if(!Strings.isEmpty(file.getFilenamelocal())){ 
				file.setFilenamelocal(file.getFilenamelocal().length() > 20 ? file.getFilenamelocal().substring(0, 20) + "......" : file.getFilenamelocal());
			}
		}
		result.put("docList1", docList1);
		
		//标准规范
		Criteria docCri = Cnd.cri();
		docCri.where().and("isdeleted", "=", "0");
		docCri.where().and("postcomplete", "=", "1");
		docCri.getOrderBy().desc("POSTEDTIME");
		//docCri.where().and("fileType","=","政策法规");
		
		List<Xdb_files> docList = dao.query(Xdb_files.class, docCri, pager);
		for(Xdb_files file : docList){
			if(!Strings.isEmpty(file.getFilenamelocal())){ 
				file.setFilenamelocal(file.getFilenamelocal().length() > 20 ? file.getFilenamelocal().substring(0, 20) + "......" : file.getFilenamelocal());
			}
		}
		result.put("docList", docList);
		
		/**
		 * 资源目录编制
		 * **/
		Criteria kwCri = Cnd.cri();
		kwCri.where().and("resourceType", "=", "r_ztl");
		kwCri.where().and("provideDepId", "=", "068001");
		kwCri.where().and("status", "=",Constants.DIC_MUYFB);
		int kwCount= dao.count(Resource.class, kwCri);
		Criteria kwCri1= Cnd.cri();
		kwCri1.where().and("resourceType", "=", "r_ztl");
		kwCri1.where().and("resourceProvider", "=", "068001");
		int kwfu=dao.count(ResourceApply.class,kwCri1);
		result.put("kwfu", kwfu);
		result.put("kwCount", kwCount);
		
		Criteria mzCri = Cnd.cri();
		mzCri.where().and("resourceType", "=", "r_ztl");
		mzCri.where().and("provideDepId", "=", "050001");
		mzCri.where().and("status", "=",Constants.DIC_MUYFB);
		int mzCount= dao.count(Resource.class, mzCri);
		Criteria mzCri1 = Cnd.cri();
		mzCri1.where().and("resourceType", "=", "r_ztl");
		mzCri1.where().and("resourceProvider", "=", "050001");
		int mzfu=dao.count(ResourceApply.class,mzCri1);
		result.put("mzfu", mzfu);
		result.put("mzCount", mzCount);
		
		
		Criteria szbCri = Cnd.cri();
		szbCri.where().and("resourceType", "=", "r_ztl");
		szbCri.where().and("provideDepId", "=", "X07060");
		szbCri.where().and("status", "=",Constants.DIC_MUYFB);
		int szbCount= dao.count(Resource.class, szbCri);
		
		Criteria szbCri1 = Cnd.cri();
		szbCri1.where().and("resourceType", "=", "r_ztl");
		szbCri1.where().and("resourceProvider", "=", "X07060");
		int szbfu=dao.count(ResourceApply.class,szbCri1);
		result.put("szbfu", szbfu);
		result.put("szbCount", szbCount);
		
		Criteria gaCri = Cnd.cri();
		gaCri.where().and("resourceType", "=", "r_ztl");
		gaCri.where().and("provideDepId", "=", "030001");
		
		Criteria gaCri1 = Cnd.cri();
		gaCri1.where().and("resourceType", "=", "r_ztl");
		gaCri1.where().and("resourceProvider", "=", "030001");
		gaCri1.where().and("status", "=",Constants.DIC_MUYFB);
		int gaCount= dao.count(Resource.class, gaCri);
		int gafu=dao.count(ResourceApply.class,gaCri1);
		result.put("gafu", gafu);
		result.put("gaCount", gaCount);
		
	
		return result;
	}
	
}
