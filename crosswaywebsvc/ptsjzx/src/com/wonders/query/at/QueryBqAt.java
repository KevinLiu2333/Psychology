package com.wonders.query.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.query.entity.CorpInfo;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.ws.receive.bean.CommunityAcceptanceAffairsApplyBean;
import com.wonders.ws.receive.bean.PtMzjYiliaoJiuzhuBean;
import com.wonders.ws.receive.bean.PtQfbWangtingBean;

@At("/querybq")
@IocBean
public class QueryBqAt {
	@Inject
	private Dao dao;
	
	@At
	@Ok("jsp:jsp.query.sqsw_list")
	public Map<String, Object> toSqsw(String zjhm,HttpServletRequest request){
		Map<String, Object> result=new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		List<CommunityAcceptanceAffairsApplyBean> list=dao.query(CommunityAcceptanceAffairsApplyBean.class, Cnd.where("IDNO", "=", zjhm),pager);
		pager.setRecordCount(dao.count(CommunityAcceptanceAffairsApplyBean.class, Cnd.where("IDNO", "=", zjhm)));
		result.put("list", list);
		result.put("pager", pager);
		result.put("zjhm", zjhm);
		return result;
		
	}
	
	@At
	@Ok("jsp:jsp.query.sqsw_view")
	public Object toSqswData(String dataid){
		CommunityAcceptanceAffairsApplyBean longname=dao.fetch(CommunityAcceptanceAffairsApplyBean.class,Cnd.where("ID","=",dataid));
		return longname;
		
	}
	
	@At
	@Ok("jsp:jsp.query.wssp_list")
	public Map<String, Object> toWssp(String id,HttpServletRequest request){
		Map<String, Object> result =new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		CorpInfo corpinfo =dao.fetch(CorpInfo.class,Cnd.where("CORP_INFO_ID", "=", id));
		List<PtQfbWangtingBean> list=null;
		if(!Strings.isEmpty(corpinfo.getCorpname())){
			list=dao.query(PtQfbWangtingBean.class, Cnd.where("APPLICANT","=",corpinfo.getCorpname()),pager);
			pager.setRecordCount(dao.count(PtQfbWangtingBean.class, Cnd.where("APPLICANT","=",corpinfo.getCorpname())));
		}
		if(list==null&&!Strings.isEmpty(corpinfo.getOrgancode())){
			list=dao.query(PtQfbWangtingBean.class, Cnd.where("CERTIFICATE_NO","=",corpinfo.getOrgancode()),pager);
			pager.setRecordCount(dao.count(PtQfbWangtingBean.class, Cnd.where("CERTIFICATE_NO","=",corpinfo.getOrgancode())));
		}
		result.put("list", list);
		result.put("pager", pager);
		result.put("id", id);
		return result;
		
	}
	
	@At
	@Ok("jsp:jsp.query.wssp_view")
	public Object toWsspData(String dataid){
		PtQfbWangtingBean wt=dao.fetch(PtQfbWangtingBean.class,Cnd.where("ID","=",dataid));
		return wt;
		
	}
	@At
	@Ok("json")
	public String toYljz(String zjhm){
		int yljz=0;
		if(!Strings.isEmpty(zjhm)){
			yljz=dao.count(PtMzjYiliaoJiuzhuBean.class,Cnd.where("SFZH", "=", zjhm));
		}
		return yljz+"";
	}
	
	/**
	 * 医疗救助
	 * @param zjhm
	 * @return
	 */
	@At
	@Ok("jsp:jsp.query.yljz_list")
	public Map<String, Object> toYljzList(String zjhm){
		Map<String, Object> result= new HashMap<String, Object>();
		List<PtMzjYiliaoJiuzhuBean> list=dao.query(PtMzjYiliaoJiuzhuBean.class, Cnd.where("SFZH", "=", zjhm));
		result.put("list", list);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.query.yljz_view")
	public Object toYljzView(String dataid){
		PtMzjYiliaoJiuzhuBean yljz=dao.fetch(PtMzjYiliaoJiuzhuBean.class, Cnd.where("ID", "=", dataid));
		if(!Strings.isEmpty(yljz.getZjmzsj())){
			yljz.setZjmzsj(yljz.getZjmzsj().substring(0, 8));
		}
		if(!Strings.isEmpty(yljz.getZjzysj())){
			yljz.setZjzysj(yljz.getZjzysj().substring(0, 8));
		}
		return yljz;
	}


}
