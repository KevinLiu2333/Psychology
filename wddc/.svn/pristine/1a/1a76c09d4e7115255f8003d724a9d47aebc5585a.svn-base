package com.wonders.wdac.jk;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;

import com.wonders.wddc.suite.user.entity.UserInfoBo;
import com.wonders.wddc.suite.user.entity.UserUnitBo;
import com.wonders.wddc.tiles.jk.UserService;
import com.wonders.wddc.tiles.jk.entity.User;

/**
 * Created by Administrator on 2016/5/6.
 */
public class WdUserService implements UserService {

	 @Inject
	    private Dao dao;

	    @Override
	    public boolean login(String logonName, String password) {
	    	Criteria cri = Cnd.cri();
	    	cri.where().and("logonName","=",logonName);
	    	UserInfoBo userInfo = dao.fetch(UserInfoBo.class,cri);
	    	if(userInfo != null&&userInfo.getPassword().equals(password)){
		        return true;
	    	}else{
	    		return false;
	    	}
	    }

	    @Override
	    public User getUser(String logonName) {
	    	Criteria cri = Cnd.cri();
	    	cri.where().and("logonName","=",logonName);
	    	UserInfoBo userInfo = dao.fetch(UserInfoBo.class,cri);
	        User user = new User();
	        user.setDisplayName(userInfo.getDisplayName());
	        user.setLogonName(userInfo.getLogonName());
	        user.setUserId(userInfo.getUserId());
	        user.setUserType(userInfo.getUserType());
	        user.setUnitId(userInfo.getUnitId());
	        Criteria cri1 = Cnd.cri();
	    	cri1.where().and("unitId","=",userInfo.getUnitId());
	        UserUnitBo userUnit = dao.fetch(UserUnitBo.class,cri1);
	        user.setUnitName(userUnit.getUnitName());
	        return user;
	    }
}
