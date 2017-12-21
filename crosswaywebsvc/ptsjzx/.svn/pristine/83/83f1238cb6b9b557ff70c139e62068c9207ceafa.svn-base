package com.wonders.dddl;


import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.tiles.authority.entity.Org;
import com.wonders.tiles.authority.entity.User;

/**
 * 单点登录dao.
 */
@IocBean
public class DddlDao {

    @Inject
    Dao dao;

    /**
     * 用户名登录.
     *
     * @param loginName
     * @return
     * @throws Exception
     */
    public User login(String loginName) throws Exception {
        User user = dao.fetch(User.class, Cnd.where("LOGON_NAME", "=", loginName));
        if (user == null) {
            throw new Exception("用户不存在.");
        }
        if ("0".equals(user.getState())) {
            throw new Exception("锁定用户不能登录.");
        }
        if (user.getRoleId() == null || (user.getRoleId().length() <= 1 && Integer.valueOf(user.getRoleId()) < 1)) {
            throw new Exception("用户没有权限.");
        }
        return user;
    }

    /**
     * 部门Id获得部门.
     *
     * @param orgId
     * @return
     */
    public Org getOrgById(String orgId) {
        Org org = dao.fetch(Org.class, Cnd.where("ORG_ID", "=", orgId));
        return org;
    }

    /**
     * 部门code获得部门.
     *
     * @param orgCode
     * @return
     */
    public Org getOrgByCode(String orgCode) {
        Org org = dao.fetch(Org.class, Cnd.where("ORG_CODE", "=", orgCode));
        return org;
    }
}
