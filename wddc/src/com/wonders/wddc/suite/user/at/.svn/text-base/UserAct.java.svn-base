package com.wonders.wddc.suite.user.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wddc.suite.user.entity.AuthorityBo;
import com.wonders.wddc.suite.user.entity.UserInfoBo;

@At("/suite/user")
@IocBean(fields = "dao")
public class UserAct {
	private Dao dao;

    /**
     * 单位管理页面.
     */
    @At
    @Ok("jsp:wddc.suite.user.user_list")
    public  Object toUserList(){
        //获取所有标签
    	Map<String,Object>  result= new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        List<UserInfoBo> userInfoList = dao.query(UserInfoBo.class, cri);
        result.put("userInfoList",userInfoList);
        return result;
    }
    
    /**
	 * 进入单位新增修改页面.
	 * @param unitId
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.user.user_edit")
		public Map<String, Object> toUserEdit(String userId){
		Map<String, Object> result = new HashMap<String, Object>();
		List<AuthorityBo> authorities = null;
		if(!Strings.isEmpty(userId)){
			UserInfoBo userInfo = dao.fetch(UserInfoBo.class, userId);
			dao.fetchLinks(userInfo, "authorities");
			authorities = userInfo.getAuthorities();
			result.put("userInfo", userInfo);
		}
		Criteria cri = Cnd.cri();
		List<AuthorityBo> authorityList = dao.query(AuthorityBo.class, cri);
		if(authorities !=null){
			for(AuthorityBo authority : authorityList){
				for(AuthorityBo tempAuthority : authorities){
					if(authority.getNodeId().equals(tempAuthority.getNodeId())){
						authority.setCheckFlag("checked");
					}
				}
			}
		}

		result.put("authorityList", authorityList);
		return result;
	}
	

	/**
	 * 新增，修改功能的保存操作.
	 */
	
	@At
    @Ok("redirect:/suite/user/toUserList")
	public void saveUser(@Param("::userInfo.") UserInfoBo userInfo, String authoritys,HttpServletRequest request){
		if(Strings.isEmpty(userInfo.getUserId())){
			userInfo.setUserId(System.currentTimeMillis()+"");
			dao.insert(userInfo);
		}else{
			UserInfoBo oldUserInfo = dao.fetch(UserInfoBo.class, userInfo.getUserId());
			userInfo.setPassword(oldUserInfo.getPassword());
			dao.update(userInfo);
		}

		dao.clear("suite_user_authority_map", Cnd.where("user_id","=",userInfo.getUserId()));
		if(Strings.isNotBlank(authoritys)){
			Criteria cri = Cnd.cri();
			cri.where().andIn("nodeId",authoritys.split(","));
			List<AuthorityBo> authorityList = dao.query(AuthorityBo.class, cri);
			userInfo.setAuthorities(authorityList);
			dao.insertRelation(userInfo, "authorities");
		}
	}
	
	/**
	 * 验证用户是否存在.
	 */
	@At
	@Ok("json")
	public Map<String, Object> checkUser(String logonName){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "0");
		try{
			if(!Strings.isEmpty(logonName)){
				UserInfoBo user = dao.fetch(UserInfoBo.class, Cnd.where("logonName", "=", logonName));
				if(user != null){
					result.put("result", "1");
				}
			}
		}catch(Exception e){
			result.put("result", "0");
		}
		return result;
	}
	
	/**
	 * 单位的删除功能,ajax调用.
	 */
	@At
    @Ok("redirect:/suite/user/toUserList")
	public void delUser(String userId){
		if(Strings.isNotBlank(userId)){
			UserInfoBo userInfo = dao.fetch(UserInfoBo.class, userId);
			if(userInfo != null){
				dao.delete(userInfo);
			}
		}
	}
	

	/**
	 * 重置密码.
	 */
	@At
	@Ok("json")
	public Map<String, Object> resetPassword(String userId){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "1");
		try{
			if(!Strings.isEmpty(userId)){
				UserInfoBo user = dao.fetch(UserInfoBo.class, userId);
				if(user != null){
					//重新置密码为111111
					user.setPassword("96E79218965EB72C92A549DD5A330112");
					dao.update(user);
				}
			}
		}catch(Exception e){
			result.put("result", "0");
		}
		return result;
	}
	
	
	
}
