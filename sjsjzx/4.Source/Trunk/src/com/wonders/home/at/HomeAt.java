package com.wonders.home.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
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
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.wonders.Constants;
import com.wonders.home.entity.MonitorDisk;
import com.wonders.home.entity.TuserPhone;
import com.wonders.home.entity.TvmIp;
import com.wonders.home.service.QueryBaseInfo;
import com.wonders.query.entity.CorpGQ;
import com.wonders.query.entity.CorpLHRH;
import com.wonders.query.entity.CorpXJR;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.entity.DicDept;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.tiles.extend.filter.SessionFilter;
import com.wonders.util.HttpClientUtil;
import com.wonders.utils.CookieUtils;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgl.entity.ResourceDetails;
import com.wonders.zymlgx.entity.ResourceApply;
import com.wonders.zymlgx.entity.ResourceApplyDetails;

@IocBean
@At("/home")
public class HomeAt {

	@Inject
	private Dao dao;
	
	@Inject
	private Dao dao2;
	
	@Inject
	private Dao dao3;

	
	@Inject
	private QueryBaseInfo queryBaseInfo;

	@At
	@Ok("jsp:jsp.db.index")
	public Map<String, Object> toDb(HttpSession session) {
		return null;

	}

	@At
	@Ok("jsp:jsp.jh.index")
	public Map<String, Object> toJh(HttpSession session, String id,String byid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("byid", byid);
		return map;

	}

	@At
	@Ok("jsp:jsp.zx.index")
	public Map<String, Object> toZx(HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		result.put("user", user);
		return result;

	}

	@At
	@Ok("jsp:jsp.jk.index")
	public Map<String, Object> toJk(HttpSession session, String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", id);
		return result;

	}

	@At
	@Ok("jsp:jsp.gis.index")
	public Map<String, Object> toGis(HttpSession session) {
		return null;

	}

	/**
	 * 进入首页.
	 */
	@At
	@Ok("jsp:index_normal")
	@Filters
	public Object toIndex(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String loginName, String password) {
		String userName = CookieUtils.getCookie(request, SessionFilter.LOGON_NAME);
		User user = dao.fetch(User.class, Cnd.where("logon_name", "=", userName));
		if(user == null){
			// 清理session
			if (session != null) {
				Enumeration<?> enu = session.getAttributeNames();
				while (enu.hasMoreElements()) {
					session.removeAttribute((String) enu.nextElement());
				}
			}
			CookieUtils.cancleCookie(request, response, SessionFilter.LOGON_NAME, null);
			CookieUtils.cancleCookie(request, response, SessionFilter.DISPLAY_NAME,	null);
		}
		
		return queryBaseInfo.getBaseInfo(request);
	}

	/**
	 * 获取资源目录占比.
	 */
	@At
	@Ok("json")
	@Filters
	public List<Map<String, String>> getResourceRatio() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> rklMap = new HashMap<String, String>();
		Map<String, String> frlMap = new HashMap<String, String>();
		Map<String, String> jjlMap = new HashMap<String, String>();
		Criteria cri1 = Cnd.cri();
		cri1.where().andEquals("resourceType", Constants.R_TYPE_PEOPLE);
		Criteria cri2 = Cnd.cri();
		cri2.where().andEquals("resourceType", Constants.R_TYPE_CORP);
		Criteria cri3 = Cnd.cri();
		cri3.where().andEquals("resourceType", Constants.R_TYPE_HOUSE);
		List<Resource> rklResource = dao.query(Resource.class, cri1);
		List<Resource> frlResource = dao.query(Resource.class, cri2);
		List<Resource> jjlResource = dao.query(Resource.class, cri3);
		rklMap.put("rkl", String.valueOf(rklResource.size()));
		frlMap.put("frl", String.valueOf(frlResource.size()));
		jjlMap.put("fwl", String.valueOf(jjlResource.size()));
		list.add(rklMap);
		list.add(frlMap);
		list.add(jjlMap);
		return list;
	}
	
	@At
	@Ok("json")
	@Filters
	public Object get1() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM T_GA_RJBXX");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("市公安", sql.getResult());
		
		sql = Sqls.create("select count(1) from CORP_INFO");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("市经信委", sql.getResult());
		Criteria cri1 = Cnd.cri();
		int count1=dao.count(CorpXJR.class,cri1);
		int count2=dao.count(CorpGQ.class,cri1);
		int count3=dao.count(CorpLHRH.class,cri1);
		result.put("科委", count1+count2+count3);
		return  result;
	}
	
	@At
	@Ok("json")
	@Filters
	public Map<String, Object> to12(HttpServletRequest request,HttpSession session, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Criteria ci=Cnd.cri();
		ci.where().andIn("resourceType", "r_ztl","r_rkl","r_frl","r_fwl");
		int countML=dao.count(Resource.class,ci);
		
		map.put("countML", countML);
		
		
		Criteria c = Cnd.cri();
		c.where().andEquals("isSubmit", "1");
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		c.getOrderBy().desc("applyDate");
		//如果是各委办领导后科委用户
		if("2".equals(user.getType())){
			List<User> userList = dao.query(User.class, Cnd.where("dept", "=", user.getDept()));
			String[] userIds = new String[userList.size()];
			int index = 0;
			for(User u : userList){
				userIds[index] = u.getUserId();
				index ++;
			}
			c.where().andIn("userId", userIds);
		}else{
			c.where().andEquals("userId", user.getUserId());
		}
		int count=dao.count(ResourceApply.class, c);
		map.put("countSQ", count);
		
		return map;

	}
	/*@At
	@Ok("json")
	@Filters
	public Map<String, Object> iii1()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String sq="select * from CORP_XJR";
		Sql sql=Sqls.create(sq);
		sql.setCallback((Sqls.callback.entities()));
		sql.setEntity(dao.getEntity(CorpXJR.class));
		dao.execute(sql);
		List<CorpXJR> li=sql.getList(CorpXJR.class);
		map.put("put",li);
		return map;
		
	}
	*/
	@At
	@Ok("json")
	@Filters
	public Map<String, Object> to11(HttpSession session, String ip,String drive) {
		Map<String, Object> map = new HashMap<String, Object>();
		 Criteria cri =Cnd.cri();
		 Criteria cri1 =Cnd.cri();
		 Criteria cri2 =Cnd.cri();
		 List<MonitorDisk>dsk = null;
		 String hxk = null;
		 if(ip==null)
		 {
			hxk="192.168.104.2";
			cri1.where().and("IP","=",hxk);
			
			TvmIp t=dao.fetch(TvmIp.class, cri1);
			//System.out.println(t.getDrive());
			 cri2.where().and("OBJECT_IP","=",hxk);
			 cri2.getOrderBy().desc("SERVER_TIME");
			 //cri2.where().andIn("DISK_NAME",t.getDrive());
			 String[] sourceStrArray = t.getDrive().split(",");
			dsk=dao3.query(MonitorDisk.class,cri2,dao3.createPager(1,sourceStrArray.length ) );
			map.put("dsk",dsk );
		 }else{
			 hxk=ip;
			 cri2.where().and("OBJECT_IP","=",hxk);
			 cri2.getOrderBy().desc("SERVER_TIME");
			 //cri2.where().andIn("DISK_NAME",t.getDrive());
			 String[] sourceStrArray = drive.split(",");
			dsk=dao3.query(MonitorDisk.class,cri2,dao3.createPager(1,sourceStrArray.length ) );
			map.put("dsk",dsk );
		 }
		 List<TvmIp> tp=dao.query(TvmIp.class, cri);
		 map.put("bt", tp);
		
		 
		/* for(int i=0;i<tp.size();i++)
		 {
			 System.out.println("ip="+tp.get(i).getIp());
			 System.out.println("pf="+tp.get(i).getDrive());
			 cri1.where().and("OBJECT_IP","=",tp.get(i).getIp());
			 cri1.getOrderBy().desc("SERVER_TIME");
			  String[] sourceStrArray = tp.get(i).getDrive().split(",");
			  for (int j = 0; j < sourceStrArray.length; j++) {
				  System.out.println("123="+sourceStrArray.length);
		            System.out.println("'"+sourceStrArray[j]+"'");
		            cri1.where().and("DISK_NAME","=" ,sourceStrArray[j]);
		            dsk=dao2.query(MonitorDisk.class, cri1,dao2.createPager(1,sourceStrArray.length ));
		           
			  }
			  dsk=dao2.query(MonitorDisk.class, cri1,dao2.createPager(1,tp.get(i).getDrive().length() ));
			 
			  map.put("pf",dsk);
		 }*/
			User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		
		Sql sql = Sqls.create("select USED_PERCENT from TB_CM_MONITOR_CPU  where OBJECT_IP= '"+hxk+ "' order by SERVER_TIME desc");
		sql.setCallback(new getOneStringCallBack());
		dao3.execute(sql);
		map.put("cpu",sql.getResult());
		
		map.put("user", user);
		
		sql = Sqls.create("select USED_PERCENT from TB_CM_MONITOR_MEMORY  where OBJECT_IP= '"+hxk+ "' order by SERVER_TIME desc");
		sql.setCallback(new getOneStringCallBack());
		dao3.execute(sql);
		map.put("nc",sql.getResult());
		
		return map;

	}
	
	
	@At
	@Ok("jsp:jsp.main")
	public Map<String, Object> to111(HttpSession session, String ip,String drive) {
		Map<String, Object> map = new HashMap<String, Object>();
		 Criteria cri =Cnd.cri();
		 Criteria cri1 =Cnd.cri();
		 Criteria cri2 =Cnd.cri();
		 List<MonitorDisk>dsk = null;
		 String hxk = null;
		 if(ip==null)
		 {
			hxk="192.168.104.2";
			cri1.where().and("IP","=",hxk);
			
			TvmIp t=dao.fetch(TvmIp.class, cri1);
			System.out.println(t.getDrive());
			 cri2.where().and("OBJECT_IP","=",hxk);
			 cri2.getOrderBy().desc("SERVER_TIME");
			 //cri2.where().andIn("DISK_NAME",t.getDrive());
			 String[] sourceStrArray = t.getDrive().split(",");
			dsk=dao3.query(MonitorDisk.class,cri2,dao3.createPager(1,sourceStrArray.length ) );
			map.put("dsk",dsk );
		 }else{
			 hxk=ip;
			 cri2.where().and("OBJECT_IP","=",hxk);
			 cri2.getOrderBy().desc("SERVER_TIME");
			 //cri2.where().andIn("DISK_NAME",t.getDrive());
			 String[] sourceStrArray = drive.split(",");
			dsk=dao3.query(MonitorDisk.class,cri2,dao3.createPager(1,sourceStrArray.length ) );
			map.put("dsk",dsk );
		 }
		 List<TvmIp> tp=dao.query(TvmIp.class, cri);
		 map.put("bt", tp);
		
		Sql sql = Sqls.create("select USED_PERCENT from TB_CM_MONITOR_CPU  where OBJECT_IP= '"+hxk+ "' order by SERVER_TIME desc");
		sql.setCallback(new getOneStringCallBack());
		dao3.execute(sql);
		map.put("cpu",sql.getResult());
		
		
		
		sql = Sqls.create("select USED_PERCENT from TB_CM_MONITOR_MEMORY  where OBJECT_IP= '"+hxk+ "' order by SERVER_TIME desc");
		sql.setCallback(new getOneStringCallBack());
		dao3.execute(sql);
		map.put("nc",sql.getResult());
		
		return map;

	}
	
	
	
	private class getOneStringCallBack implements SqlCallback {
		@Override
		public Object invoke(Connection conn, ResultSet rs, Sql sql)
				throws SQLException {
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
		}

	}

	/**
	 * 首页-数据服务更多.
	 */
	@At
	@Ok("jsp:jsp.sy.apply-list")
	@Filters
	public Object moreApplyList(HttpServletRequest request,HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andEquals("isSubmit", "1");
		cri.getOrderBy().desc("applyDate");
		Pager pager = ConUtils.makePaginationPager(request);
		List<ResourceApply> resourceApplyList = dao.query(ResourceApply.class,
				cri, pager);
		 User user = (User) session
			.getAttribute(SystemConstants.SYSTEM_USER);
		pager.setRecordCount(dao.count(ResourceApply.class, cri));
		result.put("resourceApplyList", resourceApplyList);
		result.put("pager", pager);
		result.put("user", user);
		return result;
	}

	/**
	 * 首页-查看申请信息.
	 */
	@At
	@Ok("jsp:jsp.sy.apply-view")
	@Filters
	public Object singleApply(String applyId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(applyId)) {
			ResourceApply resourceApply = dao.fetch(ResourceApply.class,
					applyId);
			List<String> resourceNameList = new ArrayList<String>();
			List<ResourceApplyDetails> resourceApplyDetailsList = dao.query(
					ResourceApplyDetails.class,
					Cnd.where("applyId", "=", applyId));
			for (ResourceApplyDetails resourceApplyDetails : resourceApplyDetailsList) {
				String resourceDetailsId = resourceApplyDetails
						.getResourceDetailsId();
				List<ResourceDetails> resourceDetailsList = dao.query(
						ResourceDetails.class,
						Cnd.where("detailsId", "=", resourceDetailsId));
				for (ResourceDetails resourceDetails : resourceDetailsList) {
					resourceNameList.add(resourceDetails.getDataItemName());
				}
			}
			User user = dao.fetch(User.class, resourceApply.getUserId());
			DicDept dept = dao.fetch(DicDept.class, user.getDept());
			result.put("deptName", dept.getDeptName());
			result.put("resourceApply", resourceApply);
			result.put("resourceNameList", resourceNameList);
		}
		return result;
	}

	@At
	@Ok("json")
	@Filters
	public String validateUser(String loginName, String password) {
		String valiCode = "";
		Criteria cri = Cnd.where("logonName", "=", loginName);
		cri.where().andEquals("password", password);
		List<User> userList = dao.query(User.class, cri);
		if (userList.size() > 0) {
			valiCode = "1";
			
		} else {
			valiCode = "0";
		}
		return valiCode;
	}
	
	@At
	@Ok("json")
	@Filters
	public String phoneUser(String userId) {
		String pu = "";
		Criteria cri = Cnd.where("USER_ID", "=", userId);
		List<TuserPhone> userList = dao.query(TuserPhone.class, cri);
		if (userList.size() > 0) {
			pu = "1";
		} else {
			pu = "0";
		}
		return pu;
	}
	
	/***
	 * 发送短信接口
	 * **/
	private static final  String   URL_SMS="http://messagecenter.songjiang.gov.cn/MessageCenterII/Service/MessageService.asmx/SendMessages";
	@At
	@Ok("json")
	@Filters
	public  String getJcwByLL(String appcode, String password, String sendModes, String title, String content, Integer needReply,
			String bizObjId, String senderUserID, String  receiveUserIds, String receiveMobileNos, Date sendTime, String expiredTime) throws Exception{
			Map<String, Object> params = new HashMap<String, Object>();
			String result=null;
			try {
				params.put("appcode","sjqzwsjzxptSMS");
				params.put("password","689P^iJmSr");
				params.put("sendModes", "");
				params.put("title","短信接口");
				params.put("content", "松江数据中心");
				params.put("needReply", 0);
				params.put("bizObjId", "888888");
				params.put("senderUserID","zwsjzxsms");
				params.put("receiveUserIds","松江数据中心");
				params.put("receiveMobileNos", "18516587960");
				params.put("sendTime", new Date());
				params.put("sendTime", new Date());
				result= HttpClientUtil.get(URL_SMS, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	/*@At
	@Ok("json")
	@Filters
	public  String getJcwByLL()
	{
		 try {  
			  
	            String endpoint = "http://messagecenter.songjiang.gov.cn/MessageCenterII/Service/MessageService.asmx?wsdl";  
	  
	            Service service = new Service();  
	            Call call = (Call) service.createCall();  
	            call.setTargetEndpointAddress(endpoint);  
	            // WSDL里面描述的接口名称(要调用的方法)  
	            call.setOperationName("SendMessages");  
	            // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)  
	            call.addParameter("appcode", XMLType.XSD_STRING, ParameterMode.IN);  
	            call.addParameter("password", XMLType.XSD_STRING, ParameterMode.IN);  
	            call.addParameter("sendModes", XMLType.XSD_STRING, ParameterMode.IN);  
	            call.addParameter("title", XMLType.XSD_STRING, ParameterMode.IN);  
	            call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("needReply", XMLType.XSD_INT, ParameterMode.IN); 
	            call.addParameter("bizObjId", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("senderUserID", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("receiveUserIds", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("receiveMobileNos", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("sendTime", XMLType.XSD_DATETIME, ParameterMode.IN); 
	            call.addParameter("expiredTime", XMLType.XSD_DATETIME, ParameterMode.IN); 
	            // 设置被调用方法的返回值类型  
	            call.setReturnType(XMLType.XSD_STRING);  
	            //设置方法中参数的值  
	            Object[] paramValues = new Object[] {"sjqzwsjzxptSMS","iJmSr","消息","短信接口","松江数据中心",0,"888888","zwsjzxsms"
						,"松江数据中心","18516587960",new Date(),new Date()};  
	            // 给方法传递参数，并且调用方法  
	            String result = (String) call.invoke(paramValues);    
	      
	            System.out.println("result is " + result);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		return null;
		
	}
	*/
	public static void main(String[] args) throws ServiceException, AxisFault {
	
			/*Call call2 = (Call)new Service().createCall();  
			call2.setTargetEndpointAddress("http://messagecenter.songjiang.gov.cn/MessageCenterII/Service/MessageService.asmx?wsdl");
			Object obj2 = call2.invoke("SendMessages", new Object[]{"sjqzwsjzxptSMS","iJmSr","消息","短信接口","松江数据中心",0,"888888","zwsjzxsms"
					,"松江数据中心","18516587960",new Date(),new Date()});  
			System.out.println(obj2);*/
		 try {  
			  
	            String endpoint = "http://messagecenter.songjiang.gov.cn/MessageCenterII/Service/MessageService.asmx?wsdl";  
	  
	            Service service = new Service();  
	            Call call = (Call) service.createCall();  
	            call.setTargetEndpointAddress(endpoint);  
	            // WSDL里面描述的接口名称(要调用的方法)  
	            call.setOperationName("SendMessages");  
	            // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)  
	            call.addParameter("appcode", XMLType.XSD_STRING, ParameterMode.IN);  
	            call.addParameter("password", XMLType.XSD_STRING, ParameterMode.IN);  
	            call.addParameter("sendModes", XMLType.XSD_STRING, ParameterMode.IN);  
	            call.addParameter("title", XMLType.XSD_STRING, ParameterMode.IN);  
	            call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("needReply", XMLType.XSD_INT, ParameterMode.IN); 
	            call.addParameter("bizObjId", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("senderUserID", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("receiveUserIds", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("receiveMobileNos", XMLType.XSD_STRING, ParameterMode.IN); 
	            call.addParameter("sendTime", XMLType.XSD_DATETIME, ParameterMode.IN); 
	            call.addParameter("expiredTime", XMLType.XSD_DATETIME, ParameterMode.IN); 
	            // 设置被调用方法的返回值类型  
	            call.setReturnType(XMLType.XSD_STRING);  
	            //设置方法中参数的值  
	            Object[] paramValues = new Object[] {"sjqzwsjzxptSMS","iJmSr","消息","短信接口","松江数据中心",0,"888888","zwsjzxsms"
						,"松江数据中心","18516587960",new Date(),new Date()};  
	            // 给方法传递参数，并且调用方法  
	            String result = (String) call.invoke(paramValues);    
	      
	            System.out.println("result is " + result);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
			

	}
	
}
