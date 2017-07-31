package com.wonders.yhgl.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.tiles.authority.entity.Authority;
import com.wonders.tiles.authority.entity.User;

@At("/yhgl")
@IocBean(fields = "dao")
public class YhglAt {
	private Dao dao;
	/**
	 * 查询用户列表
	 * @param level 用户管理员权限：1:超级管理员，2 普通管理员，3普通用户
	 * @return
	 */
	@At
	@Ok("jsp:jsp.yhgl.user_list")
	public Map<String,Object> toUserList(String level){
		Map<String, Object> result=new HashMap<String, Object>();
		Criteria cri=Cnd.cri();
		cri.where().and("state","=","1");
		if(level.equals("1"))
		{
			cri.where().and("SUPER_ADMIN","=","1");
		}
		if(level.equals("2"))
		{
			cri.where().and("ADMIN","=","1");
		}
		if(level.equals("3"))
		{
			cri.where().andIsNull("SUPER_ADMIN");
			cri.where().andIsNull("ADMIN");
		}
		List<User> list = dao.query(User.class,cri);
		for(User user : list){
			dao.fetchLinks(user, "authorities");
		}
		result.put("level", level);
		result.put("user", list);
		return result;
	}
	/**
	 * 进入用户新增和修改的功能.
	 * @return
	 */
	@At
	@Ok("jsp:jsp.yhgl.user_edit")
	public Map<String, Object> toUserEdit(String userId,String level,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Authority> authorities = null;
		if(!Strings.isEmpty(userId)){
			User user = dao.fetch(User.class, userId);
			dao.fetchLinks(user, "authorities");
			authorities = user.getAuthorities();
			result.put("user", user);
			result.put("userId", userId);
		}
		Criteria cri = Cnd.cri();
		cri.where().and("nodeType", "=","MENU");
		cri.where().and("nodeLevel", "=","1");
		List<Authority> authorityList = dao.query(Authority.class, cri);
		if(authorities !=null){
			for(Authority authority : authorityList){
				for(Authority tempAuthority : authorities){
					if(authority.getNodeId().equals(tempAuthority.getNodeId())){
						authority.setNodeExternal("checked");
					}
				}
			}
		}
		result.put("level", level);
		result.put("authorityList", authorityList);
		return result;
	}
	/**
	 * 重置密码
	 * @param userId
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> resetPassword(String userId){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "1");
		try{
			if(!Strings.isEmpty(userId)){
				User user = dao.fetch(User.class, userId);
				if(user != null){
					//重新置密码为111111
					user.setPassword("111111");
					dao.update(user);
				}
			}
		}catch(Exception e){
			result.put("result", "0");
		}
		return result;
	}
	/**
	 * 新增，修改功能的保存操作.
	 */
	@At
	@Ok("jsp:jsp.yhgl.success")
	public void saveUser(@Param("::user.") User user,String level, HttpSession session, HttpServletRequest request){
		if(user != null){
			if(Strings.isEmpty(user.getUserId())){
				user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
				user.setDisplayName(user.getLogonName());
				if(level.equals("1"))
				{
					user.setSuperadmin("1");
				}
				if(level.equals("2"))
				{
					user.setAdmin("1");
				}
				user.setState("1");
				dao.insert(user);
			}else{
				User okuser = dao.fetch(User.class, user.getUserId());
				okuser.setLogonName(user.getLogonName());
				okuser.setDisplayName(user.getLogonName());
				okuser.setDept(user.getDept());
				okuser.setPosition(user.getPosition());
				okuser.setState(user.getState());
				dao.update(okuser);
			}
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
				Criteria cri=Cnd.cri();
				cri.where().and("state","=","1");
				cri.where().and("logonName", "=", logonName);
				User user = dao.fetch(User.class, cri);
				if(user != null){
					result.put("result", "1");
				}
			}
		}catch(Exception e){
			result.put("result", "0");
		}
		return result;
	}
}
