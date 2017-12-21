package com.wonders.yjyb.at;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.yjyb.entity.Bjlb;
import com.wonders.yjyb.entity.Fzlb;

@At("/yjyb")
@IocBean
public class YjybAt {
	@Inject
    private Dao dao;
    
    @At
    @Ok("jsp:jsp.yjyb.fzlist")
    public Object FzszList(){
    	Map<String, Object> result =new HashMap<String, Object>();
    	List<Fzlb> fzlist =dao.query(Fzlb.class, null);
    	result.put("fzlist", fzlist);
		return result;
    	
    }
    
    @At
    @Ok("jsp:jsp.yjyb.toupdate")
    public Object toUpdate(int id){
    	Map<String, Object> result =new HashMap<String, Object>();
    	Fzlb fz =dao.fetch(Fzlb.class,Cnd.where("id", "=", id));
    	result.put("fz", fz);
    	return result;
    }
    
    @At
    @Ok("jsp:jsp.yjyb.success")
    public void toSave(int id,int fzsx,String status,HttpSession session){
    	Fzlb fz=dao.fetch(Fzlb.class,Cnd.where("id", "=", id));
    	fz.setFzsx(fzsx);
    	fz.setStatus(status);
    	fz.setUsername(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
    	fz.setUpdatedate(new Date());
    	dao.update(fz);
    }
    
    @At
    @Ok("jsp:jsp.yjyb.bjlist")
    public Object bjList(HttpServletRequest request,String username, Date bjsj1,Date bjsj2){
    	Map<String, Object> result =new HashMap<String, Object>();
    	Pager pager = ConUtils.makePaginationPager(request);
    	Criteria cri =Cnd.cri();
    	if(!Strings.isEmpty(username)){
    		cri.where().andLike("USERNAME", username);
    	}
    	if(bjsj1!=null&&bjsj2!=null){
    		cri.where().and("to_char(BJTIME,'yyyymmdd')", ">=", new SimpleDateFormat("yyyyMMdd").format(bjsj1));
    		cri.where().and("to_char(BJTIME,'yyyymmdd')", "<=", new SimpleDateFormat("yyyyMMdd").format(bjsj2));	
    	}
    	cri.getOrderBy().desc("BJTIME");
    	List<Bjlb> bjlist =dao.query(Bjlb.class, cri,pager);
    	pager.setRecordCount(dao.count(Bjlb.class,cri));
    	result.put("bj", bjlist);
    	result.put("username", username);
    	result.put("bjsj1", bjsj1);
    	result.put("bjsj2", bjsj2);
    	result.put("pager", pager);
    	return result;
    	
    }
}
