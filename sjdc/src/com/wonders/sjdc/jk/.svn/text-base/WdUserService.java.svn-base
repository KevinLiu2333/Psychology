package com.wonders.sjdc.jk;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

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

        return true;
    }

    @Override
    public User getUser(String logonName) {
        User user = new User();
        user.setDisplayName(logonName);
        user.setLogonName(logonName);
        user.setUserType(logonName);
        if("admin".equals(logonName)){
            user.setUserId("23462");
            user.setUnitId("111");
        }else{
            user.setUserId("13463");
            user.setUnitId("222");
        }
        user.setUnitName(" 民政局");
        return user;
    }
}
